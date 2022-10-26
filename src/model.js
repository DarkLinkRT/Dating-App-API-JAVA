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

class Match extends Sequelize.Model {}
Match.init(
  {
    created_at: {
      type: Sequelize.DATEONLY,
      defaultValue: Sequelize.fn('now'),
      allowNull: false
    }
  },
  {
    sequelize,
    modelName: 'Match'
  }
)

module.exports = {
  sequelize,
  Users,
  Match,
  db
};



