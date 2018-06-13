
var PackageManager = artifacts.require('../contracts/PackageManager.sol');

contract('PackageManager', function ([account0,account1,account2,account3]) {
  it("should assert true", function(done) {
    var isDep=PackageManager.isDeployed();
      assert.isTrue(isDep);

    done();
  });
  it('has an owner',  function () {

    PackageManager.deployed().then(function(instance) {return instance.getOwner();}).then(function(result) {
      assert.equal(account0,result)

    })

  });
});
