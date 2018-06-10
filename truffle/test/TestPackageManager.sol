pragma solidity ^0.4.21;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Package.sol";

contract TestPackageManager {
  packageManager pkg = packageManager(DeployedAddresses.packageManager());
	function testUserCanAdoptPet() public {

  uint expected = 12;
  pkg.set(expected);
  Assert.equal(uint(pkg.get()), expected, "12 should be recorded.");
}

}
