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

  struct Loan {
    address borrower;
    address Lender;
    uint256 _value;
    uint256 _period;
    uint256 _Rinterest;
  }

  event BorrowerFormed(
    string _name,
    uint256 _id
    );

// address of buyer to id;
mapping(address => uint256) public BuyAddtoId;
    // address of borrower to each loan
  mapping(uint256 => Loan) public LoanRequest;
  Borrower[] public borrowers; // Array for storing all borrowers
  uint256 public loanersCount;

  // create a borrower
  function createBorrower(string _name, string _StartUpName, string _StartUpIdea, string _LinkedInUrl, uint256 _amt) public returns(bool success) {
    // array index
    uint256 id = borrowers.push(Borrower(_name, _StartUpName, _StartUpIdea, _LinkedInUrl, msg.sender, _amt)) - 1;
    emit BorrowerFormed(_name, id); // event emitted for user
    BuyAddtoId[msg.sender] = id;
    return true;
  }

  // get borrower from array index
  function getBorrower(uint256 _id) public view returns(string, string, string, string, address, uint256) {
    /* uint256 _id = BuyAddtoId[buyer]; */
    return (borrowers[_id].name, borrowers[_id].StartUpName, borrowers[_id].StartUpIdea, borrowers[_id].LinkedInUrl, borrowers[_id].BorroweAddress,borrowers[_id].amt_have);
  }

  // create a loan request --
  // called by a borrower
  function createLoanRequest(uint256 _id,uint256 _value) public payable returns( bool success) {
    /* uint256 _id = BuyAddtoId[msg.sender]; */
    LoanRequest[_id] = Loan(msg.sender,address(0),_value,0,0);
    loanersCount += 1;
    return true;
  }

}
