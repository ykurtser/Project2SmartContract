pragma solidity ^0.4.21;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Package.sol";

contract TestPackageManager {
  packageManager pkg = packageManager(DeployedAddresses.packageManager());
	function testVal() public {

  uint expected = 12;
  pkg.set(expected);
  //address expectedAddress=pkg.getOwner();
  Assert.equal(uint(pkg.get()), expected, "12 should be recorded.");
  //Assert.equal((this), expectedAddress, "owners address");
}
function testAddress() public {

  address expectedAddress=this;
  Assert.equal(this, this, "owners address");
}
}
