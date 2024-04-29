const express = require('express');
var Dao = require('../ExpressDAO/loginDAO');
var dao = new Dao();
const app = express();
var cors = require('cors');
app.use(cors());
// respond with "hello world" when a GET request is made to the homepage
app.post('/login', express.json(), function (req, res) {
    dao.login(req.body, function(err, result){
        if (err) {
            res.status(406).send(err);
        }
        else {
            res.send(result);
        }
    });
});

const URL_PUT = '/updateCustomer';  // final in java // final C#
app.put(URL_PUT, express.json(), function (req, res) {
    dao.updateCustomer(req.body, function(err, result){
        console.log("inside API");
        res.send(result);
    });
})

const URL_POST = '/createCustomer';  // final in java // final C#
app.post(URL_POST,express.json(), function (req, res) {
    dao.createCustomer(req.body, function(err, result){
        res.send(result);
    });    
})
// delete implementation here 
app.delete('/deleteCustomer',express.json(), function (req, res) {
    dao.deleteCustomer(req.body, function(err, result){
        res.send(result);
    });
})

// craete server here  to expose the Express code
var server = app.listen(3001, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Example app listening at http://%s:%s", host, port)
})





