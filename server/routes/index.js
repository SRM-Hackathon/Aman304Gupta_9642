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

const web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));

const LendBytecode = require('../../BlockChain/build/contracts/Lend.json')
const Buyer = LendBytecode.bytecode;
const LenderABI = LendBytecode.abi;

var fromAccount = web3.eth.accounts[0];
const Lend_contract_address = "0x0047280aef87728e0cfa203eb5dd18f84e1fe5b6"

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

router.post('/signup',(req,res) => { // working

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

router.post('/borrower/create',(req,res) => { // working

  LendContract.methods.createBorrower(
     req.body.name,
     req.body.StartUpName, 
     req.body.StartUpIdea, 
     req.body.LinkedInUrl, 
     req.body.amt).send({from: fromAccount,gasPrice: '20000000000',gas: 1500000})
    .then(function(receipt){

      if(!receipt) {
        return res.send({message: 'No receipt generate',success: false})
      }

      console.log(receipt);
      res.send({success: true})           
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });

})

router.post('/borrower/get/:id',(req,res) => { // working
  LendContract.methods.getBorrower(req.params.id).call({from: fromAccount})
          .then(function(v){
            
            console.log(JSON.stringify(v))
            res.send({success: true,data: v})
    }).catch(function(err){
      res.send({success: false})
      console.error(err)
    })

})

router.post('/borrower/count',(req,res) => { // working
  
   LendContract.methods.get_borrowers_count().call({from: fromAccount})
    .then(function(receipt){
       
       res.send({success: true,count: receipt})     
        
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });
})

router.post('/loan/create',(req,res) => { // working

  LendContract.methods.createLoanRequest(
     req.body.id,req.body.value).send({from: fromAccount,gasPrice: '20000000000',gas: 1500000})
    .then(function(receipt){

      if(!receipt) {
        return res.send({message: 'No receipt generate',success: false})
      }

      console.log(receipt);
      res.send({success: true})           
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });  

})

router.post('/seeLoanRequest/get/:id',(req,res) => {

  LendContract.methods.seeLoanRequest(
    req.params.id).call({from: fromAccount})
    .then(function(receipt){

      if(!receipt) {
        return res.send({message: 'No receipt generate',success: false})
      }

      console.log(receipt);
      res.send({success: true,data: receipt})           
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });

})

router.post('/loan/count',(req,res) => { // working
  
   LendContract.methods.get_loaners_count().call({from: fromAccount})
    .then(function(receipt){
       
       res.send({success: true,count: receipt})     
        
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });
})

router.post('/lender/count',(req,res) => { // working
  
   LendContract.methods.get_lenders_count().call({from: fromAccount})
    .then(function(receipt){
       
       res.send({success: true,count: receipt})     
        
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });
})

router.post('/lender/create',(req,res) => { // working

  LendContract.methods.createLender(
    req.body.name,
    req.body.linkedInURL, 
    req.body.amount).send({from: fromAccount,gasPrice: '20000000000',gas: 1500000})
  .then(function(receipt){

    if(!receipt) {
      return res.send({message: 'No receipt generate',success: false})
    }

    console.log(receipt);
    res.send({success: true})           
     
  }).catch(function(err){
    console.log(err)
     res.send({success: false})
  });

})

router.post('/lender/get/:id',(req,res) => {

	LendContract.methods.getLenderviaId(
		req.params.id).call({from: fromAccount})
    .then(function(receipt){

    	if(!receipt) {
    		return res.send({message: 'No receipt generate',success: false})
    	}

    	console.log(receipt);
    	res.send({success: true,data: receipt})           
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });

})

router.post('/check',(req,res) => {
	console.log(LendContract)
	res.send({success: true})
})

router.post('/test',(req,res) => {

  LendContract.methods.get_borrowers_count().call({from: fromAccount})
    .then(function(receipt){
       
       res.send({success: true,count: receipt})     
        
       
    }).catch(function(err){
      console.log(err)
       res.send({success: false})
    });

})

router.post('*',(req,res) => {
  res.status(403).send({success: false,message: 'Does not exist'})
});

router.delete('*',(req,res) => {
  res.status(403).send({success: false,message: 'Does not exist'})
});

router.put('*',(req,res) => {
  res.status(403).send({success: false,message: 'Does not exist'})
});

router.get('*',(req,res) => {
  res.status(403).send({success: false,message: 'Does not exist'})
});


module.exports = router;

/*

ipfs
automation

*/
 