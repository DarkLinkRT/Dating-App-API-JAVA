const Sequelize = require('sequelize');

const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: './database.sqlite3'
});


const db = {};

db.sequelize = sequelize;

class Users extends Sequelize.Model {}

Users.init(
  {
    firstName: {
      type: Sequelize.STRING,
      allowNull: false
    },
    lastName: {
      type: Sequelize.STRING,
      allowNull: false
    },
    email: {
      type: Sequelize.STRING,
      allowNull: false
    },
    gender: {
      type:Sequelize.ENUM('female ', 'male'),
      allowNull: false
    },
    sexualOrientation: {
      type: Sequelize.ENUM('heterosexual', 'bisexual', 'homosexual', 'other')
    },
    hobbies: {
      type: Sequelize.ENUM(['sing', 'dance', 'run', 'swim', 'watch series'])
    }
  },
  {
    sequelize,
    modelName: 'Users'
  }
)

class Matches extends Sequelize.Model {}
Matches.init(
  {
    createdAt: {
      type: Sequelize.DATEONLY,
      defaultValue: Sequelize.literal('CURRENT_TIMESTAMP'),
      allowNull: false
    }
  },
  {
    sequelize,
    modelName: 'Matches'
  }
)

//Relationship
Users.hasMany(Matches, { as: 'relationUserOne', foreignKey: 'userOne' })
Matches.belongsTo(Users, { as: 'relationUserOne' })
Users.hasMany(Matches, { as: 'relationUserTwo', foreignKey: 'userTwo' })
Matches.belongsTo(Users, { as: 'relationUserTwo' })

module.exports = {
  sequelize,
  Users,
  Matches,
  db
};



