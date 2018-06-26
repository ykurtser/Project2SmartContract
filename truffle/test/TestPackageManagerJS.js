
const PackageManager = artifacts.require('../contracts/PackageManager.sol');
const Package = artifacts.require("Package");
const Carrier = artifacts.require("Carrier");

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
    let merchValue=web3.toWei(2,'ether');
    let shippingFee=web3.toWei(1,'ether');
    let arrivalTO=6;
    let waitingForStakesInTO=6;
    let newCarrierTX =await pkgManager.createCarrier({from: accounts[1]})
    let newCarrierAddress=newCarrierTX.logs[0].args.addr
    let newPkgTX=await pkgManager.createPackage(accounts[0],newCarrierAddress,accounts[2],accounts[3],merchValue,shippingFee,waitingForStakesInTO,arrivalTO)
    let newPkgAddress=newPkgTX.logs[0].args.addr

    console.log(newCarrierAddress)
  //  console.log(Package.abi)
    var packageAbi = web3.eth.contract(Package.abi);
    var newPkg = packageAbi.at(newPkgAddress);
    var carrierAbi = web3.eth.contract(Carrier.abi);
    var newCarrier = carrierAbi.at(newCarrierAddress);

    //console.log(newPkg.getMerchVal().toNumber())
    //console.log("new pacakge balance: "+(await web3.eth.getBalance(newPkgAddress).toNumber()))
    //web3.eth.sendTransaction({from: accounts[0], to: newPkgAddress, value: ammount});
    //console.log("new pacakge balance: "+(await web3.eth.getBalance(newPkgAddress).toNumber()))
    //console.log(newPkg.getAmmountSeller().toNumber())

    let state = await newPkg.getState();
    assert.equal(state,0);

    let sellerStake =await newPkg.getSellerStake().toNumber();
    let buyerStake =await newPkg.getBuyerStake().toNumber();
    let carrierStake =await newPkg.getCarrierStake().toNumber();

    web3.eth.sendTransaction({from: accounts[0], to: newPkgAddress, value: sellerStake});
    assert.equal(newCarrier.getOwner(),accounts[1]);
    assert.equal(newPkg.getCarrier(),newCarrierAddress);
    console.log(web3.eth.getBalance(newCarrierAddress))
    web3.eth.sendTransaction({from: accounts[1], to: newCarrierAddress, value: carrierStake});
    console.log(web3.eth.getBalance(newCarrierAddress))
    newCarrier.addDeliveryStation.sendTransaction(accounts[7],{from: accounts[1]})
    console.log(newCarrier.containsStation(accounts[7]))
    //web3.eth.sendTransaction({from: newCarrierAddress, to: newPkgAddress, value: carrierStake});

    await newCarrier.sendFundsToPackage.sendTransaction(accounts[8],carrierStake,{from: accounts[1]},function(error, result){
    if(!error) {
        console.log("#" + result + "#")
    } else {
        console.error("#" + error + "#");
    }
    })

    web3.eth.sendTransaction({from: accounts[2], to: newPkgAddress, value: buyerStake});
    state = await newPkg.getState();
    assert.equal(state,1);




    await newPkg.signPackage("signed ",{from: newCarrierAddress})
    console.log(newPkg.getTrajectory())
    /*
    newCarrier.addDeliveryStation(accounts[7])
    newCarrier.signPackage(newPkg.address,"signed proxy",{from: accounts7})
    console.log(newPkg.getTrajectory())
    */
  })

});
