


var Package = artifacts.require('../contracts/Package.sol');

contract('Package', function ([seller,carrier,buyer,disputeResolver]) {
  it("should assert true", function(done) {
      var isDep=Package.isDeployed();
      assert.isTrue(isDep);
      Package.deployed().then(function(instance) {return instance.getBuyer();}).then(function(result) {
      assert.equal(buyer,result)})
      done();
  });
  it('state is waitingForStakesIn',  function () {

    Package.deployed().then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(0,result.toNumber())
    })
  });

  it('changeState to shipped correctly',  function () {

    var pkg =Package.deployed()
    var pkgAddress=pkg.address


    pkg.then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(0,result.toNumber())
    })
    pkg.then(function(instance) {return instance.sendTransaction({from: buyer , value: 100})})

    pkg.then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(0,result.toNumber())
    })
    pkg.then(function(instance) {return instance.getAmmountBuyer();}).then(function(result) {
    assert.equal(100,result.toNumber())
    })


  });
});
