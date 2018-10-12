const express = require('express');
const router = express.Router();
const User = require("../models/user");
const mongoose = require("mongoose");
const bodyParser = require('body-parser');
const cors = require('cors');
const Web3 = require('web3'); 

router.use(bodyParser.urlencoded({ extended: true }))
router.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header('Access-Control-Allow-Origin', 'http://localhost:7000');
    res.header('Access-Control-Allow-Origin', 'http://localhost:7545');
    res.header('Access-Control-Allow-Credentials', true);
    next();
});

router.use(cors());

const web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));

const LendBytecode = require('../../BlockChain/build/contracts/Lend.json')
const Buyer = LendBytecode.bytecode;
const LenderABI = LendBytecode.abi;

var fromAccount = web3.eth.accounts[0];
const Lend_contract_address = "0x115d647dd4886949e183fa541bccd78f736a60d1"

const LendContract = new web3.eth.Contract(
    LenderABI, 
    Lend_contract_address, {
      from: fromAccount,
      gasPrice: '20000000000', // default gas price in wei, 20 gwei in this case
      gas: 1891234
  });

LendContract.options.data = LendBytecode;

web3.eth.getAccounts().then(accounts => {
      
      fromAccount = accounts[0];
      console.log("success")
   
})

router.post('/signup',(req,res) => {

  web3.eth.getAccounts().then(accounts => {
      
      console.log(accounts);

      User.countDocuments({}).then(function(count){
        console.log(count)
        User.create({
          username: req.body.username,
          email: req.body.email,
          walletAddress: accounts[count+1]
        }).then(function(data){
          res.send({walletAddress:data.walletAddress , success: true})
        }).catch(function(err){ 
             res.send({success: false})
        })
            
      })
   
   }).catch(function(err){
   	console.log(err)
   	res.send({success: false})
   })

})

router.post('/borrower/create',(req,res) => {

  web3.eth.getAccounts().then(accounts => {
      
      console.log(accounts);

      db.User_Details.countDocuments({}).then(function(count){
        console.log(count)
        db.User_Details.create({
          username: req.body.username,
          email: req.body.email,
          meterId: req.body.meterId,
          walletAddress: accounts[count+1]
        }).then(function(data){
          res.send({walletAddress:data.walletAddress , success: true})
        }).catch(function(err){ 
             res.send({success: false})
        })
            
      })
   
   })

})

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
	
});

router.post('/test',(req,res) => {

  LendContract.methods.get_borrowers_count().call({from: fromAccount})
    .then(function(receipt){
       
       res.send({success: true,count: receipt})     
        
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });

})

module.exports = router;
 