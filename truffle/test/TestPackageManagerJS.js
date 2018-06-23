
const PackageManager = artifacts.require('../contracts/PackageManager.sol');
const Package = artifacts.require('../contracts/Package.sol');
const Carrier = artifacts.require('../contracts/Carrier.sol');

contract('PackageManager', function (accounts) {
  var pkgManager;
  var ammount=web3.toWei(1,'ether');


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
    console.log("PackageManager Balance: " +await web3.eth.getBalance(pkgManager.address).toNumber())
    console.log("account 0 balance: "+await web3.eth.getBalance(accounts[0]).toNumber())
    await pkgManager.claimExcessEth()
    console.log("after collecting, PackageManager Balance: " +await web3.eth.getBalance(pkgManager.address).toNumber())
    console.log("account 0 balance: "+await web3.eth.getBalance(accounts[0]).toNumber())
  })
  it('create carrier, create package, carrier sign package succesfully', async function () {
    let merchValue=web3.toWei(10,'ether');
    let shippingFee=web3.toWei(2,'ether');
    let arrivalTO=6;
    let waitingForStakesInTO=6;
    let newCarrierAddress =await pkgManager.createCarrier({from: accounts[1]})
    let newPkgAddress = await pkgManager.createPackage(accounts[0],accounts[1],accounts[2],accounts[3],merchValue,shippingFee,waitingForStakesInTO,arrivalTO)
    let newPkg = Package.deployed()
    console.log(newPkg.address)
    console.log(newPkgAddress)
    let state = await newPkg.getState();
    assert.equal(state,0);
    web3.eth.sendTransaction({from: accounts[0], to: newPkg.address, value: 14});
    web3.eth.sendTransaction({from: accounts[0], to: newPkg.address, value: 14});
    web3.eth.sendTransaction({from: accounts[0], to: newPkg.address, value: 14});
    state = await newPkg.getState();
    assert.equal(state,1);
    newPkg.signPackage("signed ",{from:newCarrier})
    console.log(newPkg.getTrajectory())
    newCarrier.addDeliveryStation(accounts[7])
    newCarrier.signPackage(newPkg.address,"signed proxy",{from: accounts7})
    console.log(newPkg.getTrajectory())
  })

});
