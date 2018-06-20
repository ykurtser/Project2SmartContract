
const PackageManager = artifacts.require('../contracts/PackageManager.sol');

contract('PackageManager', function (accounts) {
  var pkgManager;
  var ammount=web3.toWei(0.001,'ether');


  beforeEach('setup contract for each test', async function(){
      pkgManager = await PackageManager.new();
      console.log("new pkgManager created at address: " + pkgManager.address);
  })


  it('owner assigned', async function () {
    let _owner =await pkgManager.getOwner()
    assert.equal(_owner,accounts[0])
  })
  it('collect money', async function () {
    console.log("account 7 balance: "+await web3.eth.getBalance(accounts[6]).toNumber())
    console.log("packageManger  balance: "+await web3.eth.getBalance(pkgManager.address).toNumber())
    console.log("value for transaction: "+ammount)
    web3.eth.sendTransaction({from: accounts[6], to: pkgManager.address, value: ammount});
    console.log("after transferring " +ammount+ " from account 7  balance is" +await web3.eth.getBalance(accounts[6]).toNumber())
    console.log("PackageManager Balance: " +await web3.eth.getBalance(pkgManager.address).toNumber()+"to PackageManager")
    console.log("account 0 balance: "+await web3.eth.getBalance(accounts[0]).toNumber())
    await pkgManager.claimExcessEth()
    console.log("after collecting, PackageManager Balance: " +await web3.eth.getBalance(pkgManager.address).toNumber()+"to PackageManager")
    console.log("account 0 balance: "+await web3.eth.getBalance(accounts[0]).toNumber())
  })

});
