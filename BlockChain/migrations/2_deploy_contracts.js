var Receiver = artifacts.require('./Receiver.sol')
var Lend = artifacts.require('./Lend.sol')

module.exports = function (deployer) {
  deployer.deploy(Receiver).then(function() {
    return deployer.deploy(Lend);
  }).catch(function(err) {
    console.log(err)
  });
}
