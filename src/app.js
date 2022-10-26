const express = require('express');
const bodyParser = require('body-parser');
const {sequelize, db} = require('./model')
const app = express();
app.use(bodyParser.json());
app.set('sequelize', sequelize)
app.set('models', sequelize.models)

/*** GET USERS */
app.get('/users' ,async (req, res) =>{
    const {Users} = req.app.get('models')
    const users = await Users.findAll()
    if(!users) return res.status(404).end()
    res.json(users)
})
/** REGISTER USERS */
app.get('/users/register' ,async (req, res) =>{
    const {Users} = req.app.get('models')
    const users = await Users.add()
    if(!users) return res.status(404).end()
    res.json(true)
})
module.exports = app;