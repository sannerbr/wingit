# wingit
Online airplane retailer

To run this application, you need to have a mysql docker container running, as well as nodejs installed on your machine. 

To setup the database, run both the production and test ddl scripts from the sql folder in server/sql/

To start the application:
Inside a terminal window, run `npm install` in the client folder.
Start the back-end server by running the App class in /server/src/main/java/learn/wingit
Then, run `npm start` in the terminal window while still in the client folder

To create an admin account, register the first user account, then run the first-user-to-admin-dml.sql (found in server/sql) in mysql workbench.

We decided to comment out some code in the controllers for use in future functionalities yet to be built.
