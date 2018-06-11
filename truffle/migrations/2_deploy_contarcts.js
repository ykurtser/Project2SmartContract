var packageManager = artifacts.require("packageManager");
var AddressSet = artifacts.require("AddressSet")



module.exports = function(deployer) {
  //deployer.deploy(package(0xd51d1b97A1Dab01E0eEd629A31B09D58E3ccB642,0xd51d1b97A1Dab01E0eEd629A31B09D58E3ccB642,0xA895A52FD9b319559c17b6FeE7Cbcd10A7efC994,0x3987A210447e3bff7EC9F6Ef98AbC07849ba50AC,0xFCc107495c1D4D4f0B2Bd7c2621A37A0614B8879,50000000,10000000, 10 , 2 ));
  deployer.deploy(AddressSet);
  deployer.link(AddressSet,packageManager)
  deployer.deploy(packageManager);
};
