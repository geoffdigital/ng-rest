Let's have some fun!

REST API
========

Create Student  
POST http://localhost/ng-rest/students, 201 (Created) if Student is successfully created, in addition to a Location header that contains the link to the newly created Student resource; 406 (Not Acceptable) if the format of the incoming data is not valid  

Read All Students  
GET, http://localhost/ng-rest/students, 200 (Ok, Success) returns a list of all Student resources  

Read One Student  
GET, http://localhost/ng-rest/students/{Student Id}, Asks for representation of a specific Student resource  

Update Student  
PUT http://localhost/ng-rest/students/{Student Id}, 201 (Created), 406 (Not Acceptable) if there is a problem with the format of the incoming data on the new payment details; 404 (Not Found) if Student resource not found  

Delete Student  
DELETE http://localhost/ng-rest/students/{Student Id}, 200 (Ok), 404 (Not Found) if Student resource not found  
