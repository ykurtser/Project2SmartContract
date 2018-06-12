
var PackageManager = artifacts.require('../contracts/PackageManager.sol');

contract('PackageManager', function ([account0]) {
  it("should assert true", function(done) {
    var packageManager = PackageManager.deployed();
    assert.isTrue(true);
    done();
  });
  //it('has an owner', async function () {
  //  assert.equal(await PackageManager.getOwner(), account0)
//})
});
