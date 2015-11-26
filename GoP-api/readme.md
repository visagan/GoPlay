1) Git clone the repo 

2) Install mysql latest and start the mysql server 

3) Create a Database goplay 

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

Go to the GoP-api  Folder and then

$ mvn spring-boot:run
