var Receiver = artifacts.require("./Receiver.sol");

var receiverInstance;
var amt = 100; // 100 eth

contract("Receiver", function(accounts) {

  it("creates new borrowe", function() {
    return Receiver.deployed().then(function(instance) {
      receiverInstance = instance;
      return receiverInstance.createBorrower.call("Aman","WebArch","BlockPal","abc",amt, {from:accounts[0]});
    }).then(function(success) {
      assert.equal(success,true, "it 0");
      return receiverInstance.createBorrower("Aman","WebArch","BlockPal","abc",amt, {from:accounts[0]});
    }).then(function(receipt) {
      // console.log(receipt.logs[0].args._id)
      assert.equal(receipt.logs.length,1,"triggers 1 one event");
      assert.equal(receipt.logs[0].args._id,0,"id asc");
      return receiverInstance.borrowers(0);
    }).then(function(borrower) {
      // console.log(borrower.name);
      assert.equal(borrower[0],"Aman","set as Aman");
      assert.equal(borrower[1],"WebArch","set startuo");
      assert.equal(borrower[2],"BlockPal","project Name");
      assert.equal(borrower[3],"abc","linkedin");
      assert.equal(borrower[4],accounts[0],"account set");
      assert.equal(borrower[5],100,"amt generated");
      return receiverInstance.BuyAddtoId(accounts[0]);
    }).then(function(id) {
      assert.equal(id, 0, "address is correct");
    })
  })

  it("displays buyer", function() {
     return Receiver.deployed().then(function(instance) {
       receiverInstance = instance;
       return receiverInstance.getBorrower(0);
     }).then(function(receipt) {
       console.log(receipt);
       assert.equal(receipt[0],"Aman","set as Aman");
       assert.equal(receipt[1],"WebArch","set startuo");
       assert.equal(receipt[2],"BlockPal","project Name");
       assert.equal(receipt[3],"abc","linkedin");
       assert.equal(receipt[4],accounts[0],"account set");
       assert.equal(receipt[5],100,"amt displayed");
     });
});
//   //
  it("creates Loan Request", function() {
    return Receiver.deployed().then(function(instance) {
      receiverInstance = instance;
      return receiverInstance.createLoanRequest.call(0,10);
    }).then(function(success) {
      assert.equal(success, true,"sets as true");
      return receiverInstance.createLoanRequest(0,10);
    }).then(function(receipt) {
      return receiverInstance.LoanRequest(0);
    }).then(function(loan) {
      // console.log(loan);
      assert.equal(loan[0], accounts[0],"addres of borrower");
      assert.equal(loan[1], 0x0, "no lender assigned yet");
      assert.equal(loan[2].toNumber(), 10)
      assert.equal(loan[3].toNumber(), 0)
      assert.equal(loan[4].toNumber(), 0)

    })
  })


})
