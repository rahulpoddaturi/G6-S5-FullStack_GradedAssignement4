We are going to demo the Graded Assignemt 4  with the help of postman 

Pre-setup. 
 I have added the following in the database 
	roles - Added an ADMIN role 
	Users - added a user with Username as admin and password as admin 
	users-roles - added a row with maping user with id 1(admin) to role 1(ADMIN) 

1. I will use postman to add a role to the database.
	I have successfully added a role to the database.
2. I have used postman to add the user. 
3. Now i will try to add the employees using the user login
	We saw that if we are using non admin account we are not able to add the user to the database 
	we saw that if we are using admin account we are able to add the user to the database. 
	Now I will add few more employees using admin account

4. Now we will list all employees using admin account 
  Now we will list all employees using User account. 

5. Fetch a single employee

6.Update an existing record

7. Delete an employee can only be done by admin 
   if we use a USER account the operation fails
   if we use a ADMIN account the employee should be deleted. 
  	
8. Search an employee by firstname 
9.Sort using ascending/descending order.
	we were able to sort the employees using both ascending and descending order. 

Thanks

