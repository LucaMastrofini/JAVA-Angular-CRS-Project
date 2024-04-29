const { json } = require('express');

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/crslogin";

class mongoDAO {




    constructor() {
    }

    createStudent(body, callBack) {
        console.log("body: " + JSON.stringify(body));
        MongoClient.connect(url, function (err, db){
            var query = { username: body.username }
            if (err) throw err;
            var dbo = db.db("crslogin");
            dbo.collection("users").find(query).toArray(function (err, result) {
                console.log("result --> " + JSON.stringify(result));
                if (result.length != 0){
                    callBack(new Error("Username already used."), null);
                }
                else {
                    console.log("inserting body: " + JSON.stringify(body));
                    dbo.collection("users").insertOne(body);
                }
            })
        })
    }

    login(body, callBack) {

        MongoClient.connect(url, function (err, db) {
            var query = { username: body.username, password: body.password, role: parseInt(body.role) }
            console.log("query -> " + JSON.stringify(query));
            if (err) throw err;
            var dbo = db.db("crslogin");
            dbo.collection("users").find(query).toArray(function (err, result) {
                console.log("result ->" + JSON.stringify(result));
                console.log("result length ->" + result.length);
                if (err) throw err;
                if (result.length == 0) {
                    console.log("the credentials are wrong")
                    callBack(new Error('Wrong credentials'), null);

                }
                else if (body.role == 3) {
                    if (!result[0].registrationApproved) {
                        console.log("Registration is not approved");
                        console.log(result[0].registrationApproved);
                        return callBack(new Error('Your registration is not approved'), null);
                    }
                    else {
                        return callBack(null, result[0]);
                    }
                    
                }
                else {
                    console.log("works");
                    return callBack(null, result[0]);
                }
            })
        })
    }
}

module.exports = mongoDAO;

