pragma solidity ^0.4.24;
import "./Receiver.sol";
contract Lend is Receiver { // Receiver import to see borrowers and loan request

  struct Lender {
    string name;
    address lenderId;
    string linkedInURL;
    uint256 amt_have;
  }

  Lender[] public lenders;

  mapping (address => uint256) public LendAddtoId;

  event LenderCreated(uint256 _id);

  function createLender(string _name,string _linkedInURL, uint256 amt_have) public returns (bool success) {
    uint256 id = lenders.push(Lender(_name ,msg.sender , _linkedInURL, amt_have)) - 1;
    emit LenderCreated(id);
    LendAddtoId[msg.sender] = id;
    return true;
  }

// to get one lender -- using its address;--- called by buyer
  function getLender(address lender) public view
    returns(address,string,string, uint256) {
      uint256 _id = LendAddtoId[lender];
      return (
        lenders[_id].lenderId,
        lenders[_id].name,
        lenders[_id].linkedInURL,
        lenders[_id].amt_have
      );
}

function getLenderviaId(uint256 _id) public view
  returns(address,string,string, uint256) {
    return (
      lenders[_id].lenderId,
      lenders[_id].name,
      lenders[_id].linkedInURL,
      lenders[_id].amt_have
    );
}
// see loan request -- from borrower's id -- but only see who is requesting loan

function get_borrowers_count() public view returns(uint256) {

  return borrowers.length;
}

function get_lenders_count() public view returns(uint256) {

  return lenders.length;
}

function get_loaners_count() public view returns(uint256) {

  return loanersCount;
}

function seeLoanRequest(uint256 _id) public view returns(uint256) {

    return LoanRequest[_id]._value;
}
// approve request -- and transfer tokens
function setLoanTerms(uint256 _id, uint256 period, uint256 Rinterest) public  returns(bool success) {
  LoanRequest[_id].Lender = msg.sender;
  LoanRequest[_id]._period = period;
  LoanRequest[_id]._Rinterest = Rinterest;
  /* uint256 borrowId = BuyAddtoId[borrower]; */
  uint256 lenderId = LendAddtoId[msg.sender];

  uint256 valueToBeSent = LoanRequest[_id]._value;

  lenders[lenderId].amt_have -= valueToBeSent;
  borrowers[_id].amt_have += valueToBeSent;
  return true;
}
}
