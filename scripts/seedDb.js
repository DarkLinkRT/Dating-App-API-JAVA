const { Users , Matches } = require('../src/model');

/* WARNING THIS WILL DROP THE CURRENT DATABASE */
seed();

async function seed() {
  // create tables
  await Users.sync({ force: true });
  await Matches.sync({ force: false });
  //insert data
  await Promise.all([
    Users.create({
      id: 1,
      firstName: 'Harry',
      lastName: 'Potter',
      email: 'harry.potter@gmail.com',
      gender: 'male',
      sexualOrientation: 'heterosexual',
      hobbies: ['sing','run']
    }),
    Users.create({
      id: 2,
      firstName: 'Jodelle',
      lastName: 'Ferland',
      email: 'jodelle.ferland@gmail.com',
      gender: 'female',
      sexualOrientation: 'heterosexual',
      hobbies: ['sing','run']
    }),
    Users.create({
      id: 3,
      firstName: 'Max',
      lastName: 'Sunderland',
      email: 'max.sunder@gmail.com',
      gender: 'male',
      sexualOrientation: 'homosexual',
      hobbies: ['swim','dance']
    }),
    Users.create({
      id: 4,
      firstName: 'James',
      lastName: 'Mason',
      email: 'max.ty@gmail.com',
      gender: 'male',
      sexualOrientation: 'homosexual',
      hobbies: ['swim','dance']
    }),
    Users.create({
      id: 5,
      firstName: 'Heather',
      lastName: 'Mason',
      email: 'heather.mason@gmail.com',
      gender: 'female',
      sexualOrientation: 'bisexual',
      hobbies: ['watch series']
    })
  ]);
}
