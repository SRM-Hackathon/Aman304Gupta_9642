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
    
})

module.exports = router;
 