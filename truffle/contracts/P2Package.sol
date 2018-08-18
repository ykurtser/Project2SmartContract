pragma solidity ^0.4.21;
pragma experimental ABIEncoderV2;


contract P2Package {
    enum State {WaitingForStakesIn, Shipped, Returned, UnderDispute, Finished}
    State  state;
	address seller;
	address carrier;
	address buyer;
	address disputeResolver;
    address packageManger;
	uint merchValue;           //merchandise value [wei]
	uint shippingFee;          //shipping fee [wei]
	uint ammountBuyer;         //current ammount payed by buyer [wei]
	uint ammountCarrier;       //current ammount payed by carrier[wei]
	uint ammountSeller;        //current ammount payed by seller[wei]
	uint creationTime;
	uint arrivalTO;            // maximum time to package arrival [days]
	uint waitingForStakesInTO ;//maximum time to make payments [days]
	string[] trajectory;
	uint numOfSig; //number of stations allong pakage route
    event uintFieldAssigned(string fieldName, uint value);  //Debug event
    event changedState(uint currState);  //Debug event

    /********************************************************************************************************************************
    * requires: merchValue >=0, shippingFee>=0, ArrivalTO >=0, WaitingForStakesInTO  >=0
    * modifies: a transaction, changes blockchain state
    * effects: create a new package contract, if ether was sent resolvePayment
    *********************************************************************************************************************************/
	constructor(address PkgCreator,address Seller,address Carrier,address Buyer,address DisputeResolver,uint MerchValue,uint ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO) public payable
	{
	    seller = Seller;
	    buyer = Buyer;
	    carrier = Carrier;
        packageManger = msg.sender;
	    disputeResolver = DisputeResolver;
	    merchValue = MerchValue;
	    shippingFee = ShippingFee;
	    ammountBuyer = 0;
	    ammountCarrier = 0;
	    ammountSeller = 0;
	    numOfSig = 0;
	    state = State.WaitingForStakesIn;
	    creationTime = now;
	    arrivalTO = ArrivalTO;
	    waitingForStakesInTO = WaitingForStakesInTO;
	    trajectory = new string[](10); // check how to keep trajectory

	    //check if creator paid and update
        if(msg.value>0){
            resolvePayment(PkgCreator);
        }
    }

    /////////////  Modifiers  /////////////

    modifier atState(State _state) {
        require(state == _state);
        _;
    }

    // Perform timed transitions. Be sure to mention
    // this modifier first, otherwise the guards
    // will not take the new stage into account.
    modifier timedTransitions() {
        if (state == State.WaitingForStakesIn && now >= creationTime + waitingForStakesInTO)
        {
          terminateNotShipped();
        }
        else if (state == State.Shipped &&  now >= creationTime + arrivalTO)
        {
            terminateLost();
        }
        else if (state == State.Returned &&  now >= creationTime + 2*arrivalTO)
        {
            terminateOnReturn();
        }
        _;
    }
    /********************************************************************************************************************************
    * modifies: this
    * effects: if payer is one of known addressess (buyer,carrier,seller), update the correspondant amounnt paid
    *********************************************************************************************************************************/
    function resolvePayment(address payer) private
    {
        if (payer == buyer)
        {
            ammountBuyer+=msg.value;
        }
        else if (payer == seller)
        {
            ammountSeller+=msg.value;
        }
        else if (payer == carrier)
        {
            ammountCarrier+=msg.value;
        }

    }
    /********************************************************************************************************************************
    * modifies: this
    * effects: move to next state
    *********************************************************************************************************************************/
    function changeState() private
    {
        state = State(uint(state) + 1);
        emit changedState(uint(state));

    }

    /********************************************************************************************************************************
    * modifies: this
    * effects: move to State Done
    *********************************************************************************************************************************/
    function changeStateDone() private
    {
        state = State.Finished;
        emit changedState(uint(state));
    }

    /********************************************************************************************************************************
    * modifies: this
    * effects: recieve ether, resolvePayment, change state if all stakes paid
    *********************************************************************************************************************************/
    function () public payable {
	    resolvePayment(msg.sender);
	    //check if everybody paid, package ready for shipping
	    if (ammountBuyer >= getBuyerStake() && ammountSeller >= getSellerStake() && ammountCarrier >= getCarrierStake() && state == State.WaitingForStakesIn)
	        changeState();
	}
  /********************************************************************************************************************************
  * modifies: this, a transaction, changes blockchain state
  * effects: if message sender  is buyer, terminateNormal(), if message sender  is seller, terminateReturned(), update packegae trajectory
  *********************************************************************************************************************************/
	function signPackage(string location) public timedTransitions() { //TODO MAKE CONDITIONS MORE READABLE
	    require( state == State.Shipped && (msg.sender == carrier || msg.sender==buyer) || state == State.Returned && (msg.sender == carrier || msg.sender==seller));
        if (msg.sender == seller)
            terminateReturned();
        if (msg.sender == buyer)
	        terminateNormal();
	   // if signer is carrier just update trajectory
	    trajectory[numOfSig] = location;
	    numOfSig++;
	}
  /********************************************************************************************************************************
  * modifies: this, a transaction, changes blockchain state
  * effects: chnages package state to returned, refund buyer with merchandis Value, pay shipping fee to carrier, change state
  *********************************************************************************************************************************/
	function returnPackage(string reason) public atState(State.Shipped){
	    require(msg.sender == buyer);
	    trajectory[numOfSig]  = reason;
	    numOfSig++;
	    changeState();
	    buyer.transfer(merchValue);
	    ammountBuyer-=merchValue;
	    carrier.transfer(shippingFee);
	    ammountCarrier-=shippingFee;
	}
  /********************************************************************************************************************************
  * modifies: this, a transaction, changes blockchain state
  * effects: if message sender is carrier, change  state
  *********************************************************************************************************************************/
  function openDispute() public atState(State.Returned) {
    require(msg.sender == carrier);
    changeState();
  }
  /********************************************************************************************************************************
  * modifies: this, a transaction, changes blockchain state
  * effects: self destruct
  *********************************************************************************************************************************/
  function killMe() public atState(State.Finished) {
      selfdestruct(packageManger);
  }

  /********************************************************************************************************************************
  * modifies: this, a transaction, changes blockchain state
  * effects: tranfer sellers cut to seller, (1-sellersCut) to carrier, then change state to done
  *********************************************************************************************************************************/
 function resolveDispute(uint sellersCut) public atState(State.UnderDispute){
   require(msg.sender == disputeResolver);
   seller.transfer(uint(( getDisputeFullAmmount() * sellersCut)/100));
   carrier.transfer(uint(( getDisputeFullAmmount() * (100-sellersCut))/100));
   trajectory[numOfSig]  = "Contract wasn't activated";
   numOfSig++;
   changeStateDone();
 }
 /********************************************************************************************************************************
 * modifies: this, a transaction, changes blockchain state
 * effects: transfer the agreed ammounts to seller and carrier, then change state to done
 *********************************************************************************************************************************/
 function terminateNormal() private atState(State.Shipped){
   seller.transfer(merchValue+2*shippingFee);
   carrier.transfer(merchValue+2*shippingFee);
   trajectory[numOfSig]  = "Package delivered";
   numOfSig++;
   changeStateDone();
 }
 /********************************************************************************************************************************
 * modifies: this, a transaction, changes blockchain state
 * effects: transfer the deposits carrier, then change state to done
 *********************************************************************************************************************************/
 function terminateReturned() private atState(State.Returned){
   carrier.transfer(merchValue+3*shippingFee);
   trajectory[numOfSig]  = "Package Returned";
   numOfSig++;
   changeStateDone();
 }
 /********************************************************************************************************************************
 * modifies: this, a transaction, changes blockchain state
 * effects: transfer all parties ammounts paid, then change state to done
 *********************************************************************************************************************************/
 function terminateNotShipped() private atState(State.WaitingForStakesIn){
   if (ammountCarrier>0)
   {
      carrier.transfer(ammountCarrier);
   }
   if (ammountBuyer>0)
   {
      buyer.transfer(ammountBuyer);
   }
   if (ammountSeller>0)
   {
      seller.transfer(ammountSeller);
   }
   trajectory[numOfSig]  = "Contract wasn't activated";
   numOfSig++;
   changeStateDone();
 }
 /********************************************************************************************************************************
 * modifies: this, a transaction, changes blockchain state
 * effects: transfer refund to buyer, deposits to seller, then change state to done
 *********************************************************************************************************************************/
 function terminateLost() private atState(State.Shipped){
   buyer.transfer(ammountBuyer);
   seller.transfer(ammountCarrier+ammountSeller);
   trajectory[numOfSig]  = "Delivery timed out";
   numOfSig++;
   changeStateDone();
 }
 /********************************************************************************************************************************
 * modifies: this, a transaction, changes blockchain state
 * effects: transfer the agreed ammounts to carrier, then change state to done
 *********************************************************************************************************************************/
 function terminateOnReturn() private atState(State.Returned){
   buyer.transfer(shippingFee);
   seller.transfer(merchValue+2*shippingFee);
   trajectory[numOfSig]  = "Contract wasn't activated";
   numOfSig++;
   changeStateDone();
 }

 ////////// Getters ///////////

    function getState()
    public
    view
    returns(uint)
    {
        return uint(state);
    }

    function getBuyer()
    public
    view
    returns(address)
    {
        return (buyer);
    }

    function getSeller()
    public
    view
    returns(address)
    {
        return (seller);
    }

    function getCarrier()
    public
    view
    returns(address)
    {
        return (carrier);
    }

    function getDisputeResolver()
    public
    view
    returns(address)
    {
        return (disputeResolver);
    }

    function getAmmountBuyer()
    public
    view
    returns(uint)
    {
        return (ammountBuyer);
    }

    function getAmmountSeller()
    public
    view
    returns(uint)
    {
        return (ammountSeller);
    }

    function getAmmountCarrier()
    public
    view
    returns(uint)
    {
        return (ammountCarrier);
    }

    function getStakesInTo()
    public
    view
    returns(uint)
    {
        return (waitingForStakesInTO);
    }

    function getarrivalTO()
    public
    view
    returns(uint)
    {
        return (arrivalTO);
    }

    function getMerchVal()
    public
    view
    returns(uint)
    {
        return (merchValue);
    }

    function getShippingFee()
    public
    view
    returns(uint)
    {
        return (shippingFee);
    }

    function getBuyerStake()
    public
    view
    returns(uint)
    {
        return (merchValue+shippingFee);
    }

    function getSellerStake()
    public
    view
    returns(uint)
    {
        return (2*shippingFee);
    }

    function getCarrierStake()
    public
    view
    returns(uint)
    {
        return (merchValue+shippingFee);
    }

    function getTrajectory()
    public
    view
    returns(string[])
    {
        return (trajectory);
    }

    function getTrajectorySize()
    public
    view
    returns(uint)
    {
        return numOfSig;
    }

    function getTrajectoryI(uint i)
    public
    view
    returns(string)
    {
        return (trajectory[i]);
    }

    function getDisputeFullAmmount()
    public
    view
    returns(uint)
    {
        return merchValue + (2*shippingFee);
    }

}
