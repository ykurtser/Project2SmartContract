


const Package = artifacts.require('../contracts/Package.sol');

contract('Package', function ([seller,carrier,buyer,disputeResolver,stranger]) {

    let pkg;
    var merchValue=web3.toWei(10,'ether');
    var shippingFee=web3.toWei(2,'ether');
    var arrivalTO=6;
    var waitingForStakesInTO=6;

    function toEther(numInWei)
    {
      return web3.fromWei(numInWei, 'ether')
    }

    async function toShippedState(){
        let sellerStake     =await pkg.getSellerStake.call()
        let buyerStake      =await pkg.getBuyerStake.call()
        let carrierStake    =await pkg.getCarrierStake.call()
        assert.equal((sellerStake,2*shippingFee).toNumber)
        assert.equal((sellerStake,merchValue+shippingFee).toNumber)
        assert.equal((merchValue+shippingFee).toNumber)
        console.log("gonna pay to pkg, from buyer:" + web3.fromWei(buyerStake,"ether") +
        ", from seller:" + web3.fromWei(sellerStake,"ether") +
        ", from carrier:" + web3.fromWei(carrierStake,"ether"))
        console.log(await web3.eth.getBalance(pkg.address).toNumber())
        web3.eth.sendTransaction({from: buyer, to: pkg.address, value: buyerStake});
        console.log(await web3.eth.getBalance(pkg.address).toNumber())
        web3.eth.sendTransaction({from: seller, to: pkg.address, value: sellerStake});
        console.log(await web3.eth.getBalance(pkg.address).toNumber())
        web3.eth.sendTransaction({from: carrier, to: pkg.address, value: carrierStake});
        console.log(await web3.eth.getBalance(pkg.address).toNumber())

    }

    async function fromShippedToDone(){
        await pkg.signPackage("Station 1",{from:carrier})
        await pkg.signPackage("Station 2",{from:carrier})
        await pkg.signPackage("Station 3",{from:carrier})
        await pkg.signPackage("Got it, Thanks",{from:buyer})
    }

    async function sign3stations(){
        await pkg.signPackage("Station 1",{from:carrier})
        await pkg.signPackage("Station 2",{from:carrier})
        await pkg.signPackage("Station 3",{from:carrier})

    }
    async function printBalances(){
      console.log("seller balance: "+toEther(await web3.eth.getBalance(seller).toNumber()))
      console.log("buyer balance: "+toEther(await web3.eth.getBalance(buyer).toNumber()))
      console.log("carrier balance: "+toEther(await web3.eth.getBalance(carrier).toNumber()))
    }

    beforeEach('setup contract for each test', async function(){
        pkg = await Package.new(stranger,seller,carrier,buyer,disputeResolver,merchValue,shippingFee,arrivalTO,waitingForStakesInTO);
        console.log("new package created at address: " + pkg.address);
    })

    it("all fields assigned properly", async function() {
        let _buyer                  = await pkg.getBuyer();
        let _seller                 = await pkg.getSeller();
        let _carrier                = await pkg.getCarrier();
        let _disputeResolver        = await pkg.getDisputeResolver();
        let _merchVal               = await pkg.getMerchVal();
        let _shippingFee            = await pkg.getShippingFee();
        let _arrivalTO              = await pkg.getarrivalTO();
        let _waitingForStakesInTO   = await pkg.getStakesInTo();
        assert.equal(_buyer,buyer);
        assert.equal(_seller,seller);
        assert.equal(_carrier,carrier);
        assert.equal(_disputeResolver,disputeResolver);
        assert.equal(_merchVal,merchValue);
        assert.equal(_shippingFee,shippingFee);
        assert.equal(_arrivalTO,arrivalTO);
        assert.equal(_waitingForStakesInTO,waitingForStakesInTO);
    })
    it("contructor Handles ether sent from buyer/seller/Carrier/stranger on creation", async function() {
        let _val=500
        //creator is a different party for each contract
        let pkg_buyer = await Package.new(buyer,seller,carrier,buyer,disputeResolver,merchValue,shippingFee,arrivalTO,waitingForStakesInTO,{value:_val});
        let pkg_seller = await Package.new(seller,seller,carrier,buyer,disputeResolver,merchValue,shippingFee,arrivalTO,waitingForStakesInTO,{value:_val});
        let pkg_carrier = await Package.new(carrier,seller,carrier,buyer,disputeResolver,merchValue,shippingFee,arrivalTO,waitingForStakesInTO,{value:_val});
        let pkg_none = await Package.new(web3.eth.accounts[8],seller,carrier,buyer,disputeResolver,merchValue,shippingFee,arrivalTO,waitingForStakesInTO,{value:_val});

        //contracts balances
        let _pkg_buyer_Balance = await web3.eth.getBalance(pkg_buyer.address);
        let _pkg_seller_Balance = await web3.eth.getBalance(pkg_seller.address);
        let _pkg_carrier_Balance = await web3.eth.getBalance(pkg_carrier.address);
        let _pkg_none_Balance = await web3.eth.getBalance(pkg_none.address);

        //get paid ammounts for each party on all contracts
        let _pkg_buyer_ammountBuyer = await pkg_buyer.getAmmountBuyer();
        let _pkg_buyer_ammountSeller = await pkg_buyer.getAmmountSeller();
        let _pkg_buyer_ammountCarrier = await pkg_buyer.getAmmountCarrier();

        let _pkg_seller_ammountBuyer = await pkg_seller.getAmmountBuyer();
        let _pkg_seller_ammountSeller = await pkg_seller.getAmmountSeller();
        let _pkg_seller_ammountCarrier = await pkg_seller.getAmmountCarrier();

        let _pkg_carrier_ammountBuyer = await pkg_carrier.getAmmountBuyer();
        let _pkg_carrier_ammountSeller = await pkg_carrier.getAmmountSeller();
        let _pkg_carrier_ammountCarrier = await pkg_carrier.getAmmountCarrier();

        let _pkg_none_ammountBuyer = await pkg_none.getAmmountBuyer();
        let _pkg_none_ammountSeller = await pkg_none.getAmmountSeller();
        let _pkg_none_ammountCarrier = await pkg_none.getAmmountCarrier();

        //balance for all should be the ammount sent
        assert.equal(_pkg_buyer_Balance,_val);
        assert.equal(_pkg_seller_Balance,_val);
        assert.equal(_pkg_carrier_Balance,_val);
        assert.equal(_pkg_none_Balance,_val);

        assert.equal(_pkg_buyer_ammountBuyer,_val);
        assert.equal(_pkg_seller_ammountSeller,_val);
        assert.equal(_pkg_carrier_ammountCarrier,_val);

        assert.equal(_pkg_buyer_ammountSeller,0);
        assert.equal(_pkg_buyer_ammountCarrier,0);
        assert.equal(_pkg_seller_ammountBuyer,0);
        assert.equal(_pkg_seller_ammountCarrier,0);
        assert.equal(_pkg_carrier_ammountBuyer,0);
        assert.equal(_pkg_carrier_ammountSeller,0);
        assert.equal(_pkg_none_ammountCarrier,0);
        assert.equal(_pkg_none_ammountSeller,0);
        assert.equal(_pkg_none_ammountBuyer,0);
    })
    it("default payable, check resilving payment of each stakeholder/stranger, check shift state", async function() {
        let _val=web3.toWei(10,'ether');

        //use pakage created before, no funds tranfer on creation

        //contracts balances
        let _buyer_Balance = await pkg.getAmmountBuyer();
        let _seller_Balance = await pkg.getAmmountSeller();
        let _carrier_Balance = await pkg.getAmmountCarrier();
        let _none_Balance = await web3.eth.getBalance(pkg.address);

        //all should be 0
        assert.equal(_seller_Balance,0);
        assert.equal(_buyer_Balance,0);
        assert.equal(_carrier_Balance,0);
        assert.equal(_none_Balance,0);

        let buyerStake=(await pkg.getBuyerStake()).toNumber()
        //transfer 2 ether from each party and stranger
        web3.eth.sendTransaction({from: buyer, to: pkg.address, value: buyerStake});
        web3.eth.sendTransaction({from: seller, to: pkg.address, value: _val});
        web3.eth.sendTransaction({from: carrier, to: pkg.address, value: _val});
        web3.eth.sendTransaction({from: stranger, to: pkg.address, value: _val});
        console.log("seller paid: "+await pkg.getAmmountSeller())
        console.log("buyer paid: "+await pkg.getAmmountBuyer())
        console.log("carrier paid: "+await pkg.getAmmountCarrier())
        console.log("seller stake: "+await pkg.getSellerStake())
        console.log("buyer stake: "+await pkg.getBuyerStake())
        console.log("carrier stake: "+await pkg.getCarrierStake())

        //check ammount paid
        _buyer_Balance = await pkg.getAmmountBuyer();
        _seller_Balance = await pkg.getAmmountSeller();
        _carrier_Balance = await pkg.getAmmountCarrier();
        _none_Balance = await web3.eth.getBalance(pkg.address);
        assert.equal(_seller_Balance.toNumber(),_val);
        assert.equal(_buyer_Balance.toNumber(), buyerStake);
        assert.equal(_carrier_Balance.toNumber(),_val);
        //assert.equal(_none_Balance.toNumber(),_val);

        //state should be 0, insufficient ammountSeller
        let state = await pkg.getState();
        assert.equal(state,0);

        //tranfer another 2 ether from seller,carrier, check state after each transaction
        web3.eth.sendTransaction({from: seller, to: pkg.address, value: _val});
        state = await pkg.getState();
        assert.equal(state,0);
        web3.eth.sendTransaction({from: carrier, to: pkg.address, value: _val});
        console.log("seller paid: "+await pkg.getAmmountSeller())
        console.log("buyer paid: "+await pkg.getAmmountBuyer())
        console.log("carrier paid: "+await pkg.getAmmountCarrier())


        state = await pkg.getState();
        assert.equal(state.toNumber(),1);

        //balance for all should be the ammount sent
        _buyer_Balance = await pkg.getAmmountBuyer();
        _seller_Balance = await pkg.getAmmountSeller();
        _carrier_Balance = await pkg.getAmmountCarrier();
        _none_Balance = await web3.eth.getBalance(pkg.address);
        assert.equal(_seller_Balance.toNumber(),_val*2);
        assert.equal(_buyer_Balance.toNumber(),buyerStake);
        assert.equal(_carrier_Balance.toNumber(),_val*2);
        //assert.equal(_none_Balance.toNumber(),_val);

        await printBalances()
        console.log("stranger balance: "+await web3.eth.getBalance(stranger).toNumber())


    })
    it("state Changes Normal flow", async function() {
        let state = await pkg.getState();
        assert.equal(state,0);

        await toShippedState();

        state = await pkg.getState();
        assert.equal(state,1);

        await fromShippedToDone();

        assert.equal(await web3.eth.getCode(pkg.address),0);  //when contract terminates, get code returnes 0x0

    })
    it("updates trajectroy", async function() {
        let state = await pkg.getState();
        assert.equal(state,0);

        await toShippedState();

        state = await pkg.getState();
        assert.equal(state,1);
        await sign3stations()

         //fails because of requires()
         //assert.throws(pkg.signPackage("garbage",{from:stranger}))

        let traj =await pkg.getTrajectory()
        let trajI =await pkg.getTrajectoryI(0)
        console.log("trajectory:"+traj)
        console.log("station 1:"+trajI)
        await pkg.signPackage("Station 4",{from:buyer})

        assert.equal(await web3.eth.getCode(pkg.address),0);  //when contract terminates, get code returnes 0x0

    })
    it("switch to returned state, terminate properly when seller sign", async function() {
        let state = await pkg.getState();
        assert.equal(state,0);

        await toShippedState();

        state = await pkg.getState();
        assert.equal(state,1);
        await sign3stations()

        //print balances
        await printBalances()
        //buyer returns package
        await pkg.returnPackage("RETURNED",{from:buyer})
        state = await pkg.getState();
        assert.equal(state,2);

        console.log("Package returned")

        //print balances
        await printBalances()

        await sign3stations()

        let traj =await pkg.getTrajectory()
        console.log("trajectory:"+traj)

        await pkg.signPackage("Got my merch back",{from:seller})

        //print balances
        await printBalances()

        assert.equal(await web3.eth.getCode(pkg.address),0);  //when contract terminates, get code returnes 0x0

    })
    it("switch to dispute, then resolve", async function() {
        let state = await pkg.getState();
        assert.equal(state,0);

        await toShippedState();

        state = await pkg.getState();
        assert.equal(state,1);
        await sign3stations()
        await pkg.returnPackage("RETURNED",{from:buyer})
        state = await pkg.getState();
        assert.equal(state,2);
        console.log("Package returned")

        await printBalances()

        let traj =await pkg.getTrajectory()
        console.log("trajectory:"+traj)

        await pkg.openDispute({from: carrier})
        state = await pkg.getState();
        assert.equal(state,3);
        console.log("Package under dispute")

        await printBalances()


        await pkg.resolveDispute(50,{from: disputeResolver})
        console.log("dispute resolved")
        await printBalances()

        assert.equal(await web3.eth.getCode(pkg.address),0);  //when contract terminates, get code returnes 0x0

    })


})
