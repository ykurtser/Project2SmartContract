


var Package = artifacts.require('../contracts/Package.sol');

contract('Package', function ([seller,carrier,buyer,disputeResolver]) {
  it("should assert true", function(done) {
      var isDep=Package.isDeployed();
      assert.isTrue(isDep);
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
    web3.eth.sendTransaction({from: seller,to: pkgAddress,value: 100000000})

    pkg.then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(0,result.toNumber())
    })
    web3.eth.sendTransaction({from: carrier,to: pkgAddress,value: 100000000})

    pkg.then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(0,result.toNumber())
    })
    web3.eth.sendTransaction({from: buyer,to: pkgAddress,value: 100000000})

    pkg.then(function(instance) {return instance.getState();}).then(function(result) {
    assert.equal(1,result.toNumber())
    })
  });
});
