pragma solidity ^0.4.21;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/PackageManager.sol";

contract TestPackageManager {
  PackageManager pkg = PackageManager(DeployedAddresses.PackageManager());

function testAddress() public {
  Assert.equal(pkg.getOwner(), 0x627306090abab3a6e1400e9345bc60c78a8bef57, "owners address");
}
}
