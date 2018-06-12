
var PackageManager = artifacts.require("PackageManager");
var Package = artifacts.require("Package");
var AddressSet = artifacts.require("AddressSet")

module.exports = function(deployer) {
  deployer.deploy(AddressSet);
  deployer.link(AddressSet,PackageManager)
  deployer.deploy(PackageManager);
  deployer.deploy(Package,0x627306090abab3a6e1400e9345bc60c78a8bef57,0x627306090abab3a6e1400e9345bc60c78a8bef57,0xf17f52151ebef6c7334fad080c5704d77216b732,0xc5fdf4076b8f3a5357c5e395ab970b5b54098fef,0xFCc107495c1D4D4f0B2Bd7c2621A37A0614B8879,50000000,10000000,10,2);
};
