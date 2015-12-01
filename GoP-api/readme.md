1) Git clone the repo 

2) Install mysql latest and start the mysql server (http://blog.joefallon.net/2013/10/install-mysql-on-mac-osx-using-homebrew/)
   Note: 
      mysql_secure_installation    for the user name root set password as password. 
      If there are persmission errors,  do 
         $ chmod -R 777 /usr/local/var/mysql
         $ chown -R _mysql:_mysql /usr/local/var/mysql
    Should fix it.
    
    mysql -uroot -p 
    Type in password 
    
    Should take to the mysql console. 
    
    Alternatively install mysql workbench UI.
     (http://dev.mysql.com/get/Downloads/MySQLGUITools/mysql-workbench-community-6.3.5-osx-x86_64.dmg)

3) Create a Database goplay 
```
   CREATE DATABASE IF NOT EXISTS goplay; 
```

4) Create a Table users 

```
CREATE TABLE Persons
(
  id int NOT NULL AUTO_INCREMENT,
  email varchar(255),
  name varchar(255),
  PRIMARY KEY (ID)
)
```

5) Go to the GoP-api  Folder and then

$ mvn spring-boot:run

This will start the servers. From Browser we can issue URL to test the service. 

Command Line Test:  (We will be automating the unit test cases and also the curl test cases, Once the UI is ready we will be having integration test cases.)

Create User: 
```
   curl -H "Content-Type: application/json" -X POST -d '{"name":"GOPLAY!","email":"rest@goplay.com"}' http://localhost:8080/users/
```

Delete User: 
  Before deleting check if the id '2' is present either by checking the DB or by searching by id. 

```
 curl  -X DELETE  http://localhost:8080/users/2
```

Find User: 

```
curl  -X GET  http://localhost:8080/users/1
```
