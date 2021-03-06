var Lend = artifacts.require("./Lend.sol");
//
var lenderInstance;
var amtL = 100; /// 100 eth
var amtB = 100;
//
contract("Lender", function(accounts) {
//
  it("creates a new lender", function() {
    return Lend.deployed().then(function(instance) {
      lenderInstance = instance;
      return lenderInstance.createLender.call("Vinyak", "def",amtL, {from :accounts[1]});
    }).then(function(success) {
      assert.equal(success, true, "set to true");
      return lenderInstance.createLender("Vinyak", "def",amtL, {from :accounts[1]});
    }).then(function(receipt) {
            assert.equal(receipt.logs.length, 1, "triggers 1 event");
            assert.equal(receipt.logs[0].args._id, 0, "id is correct");
      return lenderInstance.lenders(0);
    }).then(function(lender) {
            assert.equal(lender[0],"Vinyak","set as Vinayak");
            assert.equal(lender[1],accounts[1],"account set");
            assert.equal(lender[2],"def","linkedin id set");
            assert.equal(lender[3].toNumber(),100,"amt is set");
      return lenderInstance.LendAddtoId(accounts[1]);
    }).then(function(id) {
      assert.equal(id, 0, "id generated");
    })
    });


  it("displays lender", function() {
     return Lend.deployed().then(function(instance) {
       lenderInstance = instance;
       return lenderInstance.getLender(accounts[1]);
     }).then(function(receipt) {
       console.log(receipt);
       assert.equal(receipt[0], accounts[1], "address equal");
       assert.equal(receipt[1], "Vinyak", "Vinayak set");
       assert.equal(receipt[2], "def", "address equal");
       assert.equal(receipt[3].toNumber(), 100 , "address equal");
     });
});

it("displays lender", function() {
   return Lend.deployed().then(function(instance) {
     lenderInstance = instance;
     return lenderInstance.getLenderviaId(0);
   }).then(function(receipt) {
     console.log(receipt);
     assert.equal(receipt[0], accounts[1], "address equal");
     assert.equal(receipt[1], "Vinyak", "Vinayak set");
     assert.equal(receipt[2], "def", "address equal");
     assert.equal(receipt[3].toNumber(), 100 , "address equal");
   });
});

it("borrowers count", function() {
  return Lend.deployed().then(function(instance) {
    lenderInstance = instance;
    return lenderInstance.createBorrower("Aman","WebArch","BlockPal","abc",amtB,{from:accounts[0]});
  }).then(function(receipt) {
    return lenderInstance.get_borrowers_count();
  }).then(function(count) {
    console.log(count);
    assert.equal(count, 1);
  })
})

it("lenders count", function() {
  return Lend.deployed().then(function(instance) {
    lenderInstance = instance;
    return lenderInstance.createBorrower("Aman","WebArch","BlockPal","abc",amtB,{from:accounts[0]});
  }).then(function(receipt) {
    return lenderInstance.get_lenders_count();
  }).then(function(count) {
    console.log(count);
    assert.equal(count, 1);
  })
})

it("loaners count", function() {
  return Lend.deployed().then(function(instance) {
    lenderInstance = instance;
    return lenderInstance.createLoanRequest(0,10,{from:accounts[0]});
  }).then(function(receipt) {
    return lenderInstance.get_loaners_count();
  }).then(function(count) {
    console.log(count);
    assert.equal(count, 1);
  })
})

it("see loan request ", function() {
  return Lend.deployed().then(function(instance) {
    lenderInstance = instance;
    return lenderInstance.createLoanRequest(0,10,{from:accounts[0]});
 }).then(function(receipt) {
   return lenderInstance.seeLoanRequest(0);
 }).then(function(receipt) {
   console.log(receipt);
   assert.equal(receipt.toNumber(), 10)
 })
});

it("see loan terms ", function() {
  return Lend.deployed().then(function(instance) {
    lenderInstance = instance;
  return lenderInstance.setLoanTerms.call(0, 1, 5, {from:accounts[1]})
}).then(function(success) {
  assert.equal(success, true, "trie set");
  return lenderInstance.setLoanTerms(0, 1, 5, {from:accounts[1]})
}).then(function(receipt) {
  return lenderInstance.LoanRequest(0);
}).then(function(loan) {
  assert.equal(loan[0], accounts[0],"addres of borrower");
  assert.equal(loan[1], accounts[1], "no lender assigned yet");
  assert.equal(loan[2].toNumber(), 10)
  assert.equal(loan[3].toNumber(), 1)
  assert.equal(loan[4].toNumber(), 5)
  return lenderInstance.lenders(0);
}).then(function(lender) {
  console.log(lender);
  assert.equal(lender[3].toNumber(), 90, "value checke");
  return lenderInstance.borrowers(0);
}).then(function(borrower) {
  console.log(borrower);
  assert.equal(borrower[5], 110, "value checked");
})
})


})
