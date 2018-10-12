var express = require('express');
var router = express.Router();
const User = require("../models/user");
var mongoose = require("mongoose");

router.post('/user/create',(req,res) => {

      User.create({
        
        username: req.body.username,
	    startup: req.body.startup,
	    idea: req.body.idea,
	    linkdIn: req.body.linkdIn,
	    contract_addr: req.body.contract_addr,
	    amount_needed: req.body.amount_needed
      
    }).then(function(data) {
      console.log("Success")
      res.send({success: true})
    }).catch(function(err){
      console.log("Error: " + err)
      res.send({success: false})
    })
    
});

router.post('/user/get/:id?',(req,res) => {

	 if (!req.params.id) {

        User.find({}, (err, item) => {
            if (err) {
                res.send({ success: false });
                return console.error(err);
            } else {
             res.send({ success: true, data: item });
            }
        });

    }

    else {

       User.findOne({ _id: req.params.id }, (err, item) => {
        if (!item) {
            res.status(404).send({ success: false, message: 'User Id does not exists' });
            console.log("success");
        } else if(err) {
            console.log("error");
            res.status(404).send({ success: false });
            return console.error(err);
        } else {
            res.send({ success: true, data: item });
            console.log("success");
        }
    });

   }
	
})

module.exports = router;
 