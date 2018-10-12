pragma solidity ^0.4.24;

contract Lend {

struct Person {
  string name;
  uint256 id; // mngodb id
  uint256 balance;
}
/* mapping from name to mongoDb id */
mapping(address => Person) private addresstoPerson;
//
  function createStart(string _name,uint256 _id,uint256 _balance) public returns(bool) {
    addresstoPerson[msg.sender] = Person(_name,_id,_balance);
    return true;
  }

  function createLender(string _name, uint256 _id,uint256 _balance) public returns(bool) {
    addresstoPerson[msg.sender] = Person(_name,_id,_balance);
    return true;
  }

// this fucntion will deposit ether to startup
  function deposit(address startup,uint256 _amt) payable public returns(bool) {
    addresstoPerson[msg.sender].balance -= _amt;
    addresstoPerson[startup].balance += _amt;
    return true;
  }

  function getBalance() public view returns(uint256) {
      return addresstoPerson[msg.sender].balance;
  }
}
