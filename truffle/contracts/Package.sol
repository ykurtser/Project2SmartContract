pragma solidity ^0.4.21;
pragma experimental ABIEncoderV2;


contract Package {
    enum State {WaitingForStakesIn, Shipped, Returned, UnderDispute}
    State  state;
	address seller;
	address carrier;
	address buyer;
	address disputeResolver;
    address packageManger;
	uint merchValue;           //[wei]
	uint shippingFee;          //[wei]
	uint ammountBuyer;         //[wei]
	uint ammountCarrier;       //[wei]
	uint ammountSeller;        //[wei]
	uint creationTime;
	uint arrivalTO;            //[days]
	uint waitingForStakesInTO ;//[days]
	string[] trajectory;
	uint numOfSig; //number of stations allong pakage route
    event uintFieldAssigned(string fieldName, uint value);  //Debug event
    event changedState(uint currState);  //Debug event

	// Contract constructor set initials Values
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

    function changeState() private
    {
        state = State(uint(state) + 1);
        emit changedState(uint(state));
    }

	//pay to contract, check who paid, update amount paid then change state
    function () public payable timedTransitions() {
	    resolvePayment(msg.sender);
	    //check if everybody paid, package ready for shipping
	    if (ammountBuyer >= getBuyerStake() && ammountSeller >= getSellerStake() && ammountCarrier >= getCarrierStake() && state == State.WaitingForStakesIn)
	        changeState();
	}

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

	function returnPackage(string reason) public atState(State.Shipped){
	    require(msg.sender == buyer);
	    trajectory[numOfSig]  = reason; //todo: manage history of trajectory in array of strings
	    numOfSig++;
	    changeState();
	    buyer.transfer(merchValue);
	    ammountBuyer-=merchValue;
	    carrier.transfer(shippingFee);
	    ammountCarrier-=shippingFee;
	}

  function openDispute() public atState(State.Returned) {
    require(msg.sender == carrier);
    changeState();
    //todo: think of what message we actualy send  to disputeResolver

  }
 function resolveDispute(uint sellersCut) public atState(State.UnderDispute){
   require(msg.sender == disputeResolver);
   seller.transfer(uint(( getDisputeFullAmmount() * sellersCut)/100));
   carrier.transfer(uint(( getDisputeFullAmmount() * (100-sellersCut))/100));
   selfdestruct(packageManger);

 }

 function terminateNormal() private atState(State.Shipped){
   seller.transfer(merchValue+shippingFee);
   carrier.transfer(merchValue+2*shippingFee);
   selfdestruct(packageManger);
 }
 function terminateReturned() private atState(State.Returned){
   carrier.transfer(merchValue+3*shippingFee);
   selfdestruct(packageManger);
 }

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
      buyer.transfer(ammountSeller);
   }
   selfdestruct(packageManger);
 }

 function terminateLost() private atState(State.Shipped){
   buyer.transfer(ammountBuyer);
   seller.transfer(ammountCarrier+ammountSeller);
   selfdestruct(packageManger);
 }

 function terminateOnReturn() private atState(State.Returned){
   seller.transfer(ammountCarrier+ammountSeller);
   selfdestruct(packageManger);
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
