pragma solidity ^0.4.24;

contract Receiver {

  struct Borrower {
    string name;
    string StartUpName;
    string StartUpIdea;
    string LinkedInUrl;
    address BorroweAddress; // for sending and receivinf of ether
    uint256 amt_have; // pass this from ganache
  }

  event BorrowerFormed(
    string _name,
    uint256 _id
    );

// address of buyer to id;
mapping(address => uint256) public BuyAddtoId;
    // address of borrower to each loan
  /* mapping(address => Loan) public LoanRequest; */
  Borrower[] public borrowers; // Array for storing all borrowers

  // create a borrower
  function createBorrower(string _name, string _StartUpName, string _StartUpIdea, string _LinkedInUrl, uint256 _amt) public returns(bool success) {
    // array index
    uint256 id = borrowers.push(Borrower(_name, _StartUpName, _StartUpIdea, _LinkedInUrl, msg.sender, _amt)) - 1;
    emit BorrowerFormed(_name, id); // event emitted for user
    BuyAddtoId[msg.sender] = id;
    return true;
  }


}
