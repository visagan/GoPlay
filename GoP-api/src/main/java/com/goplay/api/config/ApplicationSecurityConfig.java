package com.goplay.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.goplay.api.models.UserLogin;
import com.goplay.api.models.UserLoginDao;


@EnableWebSecurity
@Configuration
class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
 
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .requestCache()
      .requestCache(new NullRequestCache())
      .and()
      .httpBasic();
  }
  
  @Autowired
  UserLoginDao userLogin;

  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }
 
  
  @Autowired 
  UserDetailsService userDetailsService;
  
  
  @Bean
  protected UserDetailsService userDetailsService() {
    return new UserDetailsService() {
 
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	  
        UserLogin user = userLogin.findByUsername(username);
        if(user != null) {
	        return new User(user.getUsername(), 
	        				user.getPassword(), 
	        				true, 
	        				true, 
	        				true, 
	        				true,
	        				AuthorityUtils.createAuthorityList("USER")
	        		);
	        	
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }
      
    };
  }
  
 
}