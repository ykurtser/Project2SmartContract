
var PackageManager = artifacts.require("PackageManager");
//var Package= artifacts.require("Package");
var AddressSet = artifacts.require("AddressSet")
var Carrier = artifacts.require("Carrier")
var accounts = web3.eth.accounts;

module.exports = function(deployer) {
  deployer.deploy(AddressSet);
  deployer.link(AddressSet,Carrier)
  deployer.link(AddressSet,PackageManager)
  deployer.deploy(PackageManager);
  //deployer.deploy(Package,accounts[0],accounts[0],accounts[1],accounts[2],accounts[3],50000000,10000000,10,2);
};
