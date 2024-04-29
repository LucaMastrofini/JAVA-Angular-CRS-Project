var mysql = require('mysql');  // External module for Node/Express
const dbCon = require('../config/db.js')
// code to test connection with NOde 
class loginDAO {




    constructor() {
    }

    login(body, callBack) {


        const USER_PASSWORD = "SELECT userID, username , password, role FROM User WHERE username = ? AND password = ? AND role=?";
        dbCon.query(USER_PASSWORD, [body.username, body.password, body.role], function (err, result) {
            console.log("User found-->" + JSON.stringify(result));
            if (err) throw err;
            if (result.length == 0) callBack(new Error('Wrong credentials'), null) ;
            if (body.role == 3) {
                console.log("User is a student");
                var STUDENT_JOIN = "SELECT User.userID, User.username, User.role, Student.studentID, Student.name, Student.registrationApproved FROM Student INNER JOIN User ON User.userID=Student.studentID WHERE User.username=?;";
                dbCon.query(STUDENT_JOIN, [body.username], function (err, student) {
                    if (err) throw err;
                    console.log("Student found-->" + JSON.stringify(student));
                    console.log("student is approved? " + student[0].registrationApproved);
                    if (student[0].registrationApproved == 1) {
                        console.log("Student is registered");
                        return callBack(null, student[0]);
                    }
                    else {
                        return callBack(new Error('Your registration is not approved'), null);
                    }
                })
            }
            else if (body.role == 2) {
                var PROFESSOR_JOIN = "SELECT User.userID, User.username, User.role, Professor.professorID, Professor.name FROM Professor INNER JOIN User ON User.userID=Professor.professorID WHERE User.username=?;";
                dbCon.query(PROFESSOR_JOIN, [body.username], function (err, professor) {
                    if (err) throw err;
                    return callBack(null, professor[0]);
                })
            }
            else if (body.role == 1) {
                var ADMIN_JOIN = "SELECT User.userID, User.username, User.role, Admin.adminID FROM Admin INNER JOIN User ON User.userID=Admin.adminID WHERE User.username=?;";
                dbCon.query(ADMIN_JOIN, [body.username], function (err, admin) {
                    if (err) throw err;
                    return callBack(null, admin[0]);
                })
            }
        })



    }

}

module.exports = loginDAO;

