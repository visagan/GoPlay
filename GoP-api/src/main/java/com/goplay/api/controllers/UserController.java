package com.goplay.api.controllers;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

import com.goplay.api.models.User;
import com.goplay.api.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @RequestMapping(method = RequestMethod.POST)
  public Map<String, Object> create(@RequestBody Map<String, Object> userMap) {
     User user = null;
      Map<String, Object> response = new LinkedHashMap<String, Object>();
     
      try {
          user = new User(userMap.get("name").toString(),
              userMap.get("email").toString()
          );
          userDao.save(user);
      } catch (Exception ex) {
          response.put("message", "Error creating the user: " + ex.toString());
          return response;
      }

      response.put("message", "User created successfully");
      response.put("user", user);
      return response;
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/{id}")
  public User getUserById(@PathVariable("id") long id) {
      User user = null;
      try{
         user =  userDao.findById(id);
      } catch (Exception ex) {
          return null;
      } 
      return user; 
  }
  
  @RequestMapping("/by-email")
  @ResponseBody
  public User getUserByEmail(@RequestParam(value = "email") String email) {
      User user = null;
      try{
         user =  userDao.findByEmail(email);
      } catch (Exception ex) {
          return null;
      } 
      return user; 
  }

  @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
  public Map<String, String> deleteUser(@PathVariable("id") long id) {
    User user = null;
    Map<String, String> response = new HashMap<String, String>();

    try {
      user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      response.put("message", "Error deleting the user:" + ex.toString());
      return response;
    }
    response.put("message", "User succesfully deleted!");
    return response;
  }
 
  // TODO
  //Needs to be changed to PUT method and type checked for the fields and NULL placeholders  
  
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userDao.findOne(id);
      user.setEmail(email);
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  
  @Autowired
  private UserDao userDao;
  
} 
