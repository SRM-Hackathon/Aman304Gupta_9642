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

router.post('/user/get/:id?',(req,res) =>{
	if(!req.params._id){
		User.find({}, function(err,user) =>{
			if(err){
				res.send({success:false});
				console.log(err);
			}
			else{
				res.send({success:true}, user:user)
			}
		})
	}
	else{
		User.findbyId(req.params._id, function(err,user) =>{
			if(err){
				res.send({success:false});
				console.log(err);
			}
			else{
				res.send({success:true, user:user})
			}
	})
	}
	
})

module.exports = router;
 