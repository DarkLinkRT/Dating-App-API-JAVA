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
app.post('/users/register' ,async (req, res) =>{
    const { QueryTypes } = require('sequelize');
    const {Users} = req.app.get('models')
    const genders = ["female","male"];
    const sexualOrientations = ["heterosexual","bisexual","homosexual","other"];
    const hobbies = ["sing","dance","run","swim","watch series"];
    var hobbiesReq = req.body.hobbies.split(",");
    var validado = true;
    var messageField = "";
    var userRegister = false;
    //Start validation
    if(req.body.firstName == "" || req.body.firstName == null){
        validado = false;
        messageField = messageField + " <firstName> "
    }
    if(req.body.lastName == "" || req.body.lastName == null){
        validado = false;
        messageField = messageField + " <lastName> "
    }
    if(!genders.includes(req.body.gender)){
        validado = false;
        messageField = messageField + " <gender - opciones: [female][male] > "
    }
    if(!sexualOrientations.includes(req.body.sexualOrientation)){
        validado = false;
        messageField = messageField + " <sexualOrientation - opciones: [heterosexual][bisexual][homosexual][other] > "
    }
    for(i = 0 ; i < hobbiesReq.length ; i++){
        if(!hobbies.includes(hobbiesReq[i])){
            validado = false;
            messageField = messageField + " <hobbies - opciones: [sing][dance][run][swim][watch series] > "
        }   
    }
    if(req.body.email == "" || req.body.email == null){
        validado = false;
        messageField = messageField + " <email> "
    } else{
        //Validar usuario registrado
        const Query = 'SELECT * FROM Users WHERE email = "'+req.body.email+'"'
        const response = await sequelize.query(Query, { type: QueryTypes.SELECT });
        if(response){
            validado = false
            userRegister = true;
            messageField = messageField + " [EL EMAIL INGRESADO YA SE ENCUENTRA REGISTRADO] "
        }
    }
    if (validado){
        const users = await Users.create({
            firstName           : req.body.firstName,
            lastName            : req.body.lastName,
            email               : req.body.email,
            gender              : req.body.gender,
            sexualOrientation   : req.body.sexualOrientation,
            hobbies             : req.body.hobbies
        })
        if(!users) return res.status(404).end()
        res.json(true)
    } else{
        if(userRegister){
            res.json( messageField)
        } else{
            res.json( messageField +  " no pueden ir vacios")
        }
        
    }
})
//**** FIND MATCHES PER USER */
app.get('/matches/search/user/:id' ,async (req, res) => {
    const { QueryTypes } = require('sequelize');
    if(req.params.id == "" || req.params.id == null){
        res.json( "La ID del usuario no debe ir vacia" )
    } else{
        const Query = 'SELECT * FROM Users WHERE (sexualOrientation = (SELECT sexualOrientation FROM Users WHERE id = '+req.params.id+') OR hobbies = (SELECT hobbies FROM Users WHERE id = '+req.params.id+')) AND id != '+req.params.id
        const users = await sequelize.query(Query, { type: QueryTypes.SELECT });
        if(!users) return res.status(404).end()
        res.json(users)
    }
})
//********* SAVE MATCHES ********* */
app.post('/matches/create' ,async (req, res) => {
    const {Matches} = req.app.get('models')
    var validado = true;
    var messageField = "";
    if(!Number.isInteger(req.body.userOne)){
        messageField = messageField + " [El ID del usuario uno no es valido] ";
        validado = false;
    }
    if(!Number.isInteger(req.body.userTwo)){
        messageField = messageField + " [El ID del usuario dos no es valido] ";
        validado = false;
    }
    if(validado){
        const matches = await Matches.create({
            userOne             : req.body.userOne,
            userTwo             : req.body.userTwo
        })
        if(!matches) return res.status(404).end()
        res.json(true)
    } else{
        res.json( messageField +  " no pueden ir vacios")
    }
})
module.exports = app;
