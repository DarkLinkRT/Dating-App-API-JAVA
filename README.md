# Everst BACKEND TASK

  

💫 Welcome! 🎉


This backend exercise involves building a Node.js/Express.js app that will serve a REST API. You should spend around 3 hours on this task.

## Data Models

> **All models are defined in src/model.js**

You will simulate a match between users on a dating platform. Users will be matched based on their hobbies.



## Getting Set Up

  
The exercise requires [Node.js](https://nodejs.org/en/) to be installed. We recommend using the LTS version.



1. In the repo root directory, run `npm install` to gather all dependencies.

  

2. Next, `npm run seed` will seed the local SQLite database. **Warning: This will drop the database if it exists**. The database lives in a local file `database.sqlite3`.

  

3. Then run `npm start` which should start both the server and the React client.
  

## Technical Notes
 

- The server is running with [nodemon](https://nodemon.io/) which will automatically restart for you when you modify and save a file.

- The database provider is SQLite, which will store data in a file local to your repository called `database.sqlite3`. The ORM [Sequelize](http://docs.sequelizejs.com/) is on top of it. You should only have to interact with Sequelize - **please spend some time reading sequelize documentation before starting the exercise.**

- The server is running on port 3001.

  

## APIs To Implement 
  
1. Endpoint to register users.
2. Update User's model hobbies property to be an array enum.
3. Validate the body data when registering a user.
4. Validate that the user is not registered.
5. Add error handling.
6. Review and fix the error when making a request to sqlite.
7. Validate match model so that it allows saving the match between two users.
8. Endpoint per user and be able to consult the POSSIBLE matches that they have from hobbies and their sexual Orientation.
9.  Make a match between two users.
10. Make an endpoint of the matches that a user has made.
11. Perform service to check the match percentage between two users.
12. Add relationships between Match and Users.

## Going Above and Beyond the Requirements

Given the time expectations of this exercise, we don't expect anyone to submit anything super fancy, but if you find yourself with extra time, any extra credit item(s) that showcase your unique strengths would be awesome! 🙌

It would be great for example if you'd write some unit test / simple frontend demostrating calls to your fresh APIs.

  

## Submitting the Assignment

When you have finished the assignment, create a github repository and send us the link.

  

Thank you and good luck! 🙏
