pragma solidity ^0.4.21;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/PackageManager.sol";

contract TestPackageManager {
  PackageManager pkg = PackageManager(DeployedAddresses.PackageManager());

function testAddress() public {


  Assert.equal(pkg.getOwner(), 0xd51d1b97A1Dab01E0eEd629A31B09D58E3ccB642, "owners address");
}
}
