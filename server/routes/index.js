var express = require('express');
var router = express.Router();
const User = require("../models/user");

router.post('/user/create',(req,res) => {

      User.create({
        
        username: String,
	    startup: String,
	    idea: String,
	    linkdIn: String,
	    contract_addr: String,
	    amount_needed: String
      
    }).then(function(data) {
      console.log("Success")
      res.send({success: true})
    }).catch(function(err){
      console.log("Error: " + err)
      res.send({success: false})
    })
    
})

module.exports = router;
 