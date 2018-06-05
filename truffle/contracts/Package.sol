// Version of Solidity compiler this program was written for
pragma solidity ^0.4.23;

// Our first contract is a faucet!
contract Package {
  enum State {WaitingForStakesIn, Shipped, Returned, UnderDispute}
  State public state;
	address seller;
	address carrier;
	address buyer;
	address disputeResolver;
    address packageManger;
	uint merchValue; //todo resolve type if int256 is too big or can do float
	uint shippingFee;
	uint ammountBuyer;
	uint ammountCarrier;
	uint ammountSeller;
	uint creationTime;
	uint arrivalTO;
	uint waitingForStakesInTO ;
	string[] trajectory;
	uint numOfSig; //number of stations allong pakage route

	// Contract constructor: set owner
	 constructor(address PkgCreator,address Seller,address Carrier,address Buyer,address DisputeResolver,uint256 MerchValue,uint256 ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO) public payable
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
	    trajectory = new string[](1000); // check how to keep trajectory


	    //check if creator paid and update
      resolvePayment(PkgCreator);


	}
  function resolvePayment(address payer) private
  {
    if (payer == buyer)
    {
        ammountBuyer=msg.value;
    }
    else if (payer == seller)
    {
        ammountSeller=msg.value;
    }
    else if (payer == carrier)
    {
        ammountCarrier=msg.value;
    }

  }
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

    function changeState() private
    {
        state = State(uint(state) + 1);
    }
	//pay to contract, check who paid, update amount paid then change state
	function () public payable timedTransitions() {
	    resolvePayment(msg.sender);
	    //check if everybody paid, package ready for shipping
	    if (ammountBuyer >= merchValue+shippingFee && ammountSeller >= 2*shippingFee && ammountCarrier >= merchValue+shippingFee && state == State.WaitingForStakesIn)
	        changeState();
	}

	function signPackage(string location) public timedTransitions() { //TODO MAKE CONDITIONS MORE READABLE
	    require(state == State.Shipped && (msg.sender == carrier || msg.sender==buyer) || state == State.Returned && (msg.sender == carrier || msg.sender==seller));
	    if (msg.sender == buyer)
	        terminateNormal();
	    if (msg.sender == seller)
	        terminateReturned();
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
   buyer.transfer(uint((merchValue + 2*shippingFee)*sellersCut/100));
   buyer.transfer(uint((merchValue + 2*shippingFee)*(1-sellersCut)/100));
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


}