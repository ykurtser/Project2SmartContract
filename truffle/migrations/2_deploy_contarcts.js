
var PackageManager = artifacts.require("P2PackageManager");
//var Package= artifacts.require("Package");
var AddressSet = artifacts.require("P2AddressSet")
var Carrier = artifacts.require("P2Carrier")

module.exports = function(deployer) {
   deployer.deploy(AddressSet);
   deployer.link(AddressSet,Carrier)

   deployer.link(AddressSet,PackageManager)
   deployer.deploy(PackageManager);

  //deployer.deploy(Package,accounts[0],accounts[0],accounts[1],accounts[2],accounts[3],50000000,10000000,10,2);
};
