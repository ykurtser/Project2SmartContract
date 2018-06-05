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
	uint256 merchValue; //todo resolve type if int256 is too big or can do float
	uint256 shippingFee;
	uint256 ammountBuyer;
	uint256 ammountCarrier;
	uint256 ammountSeller;
	uint creationTime;
	uint arrivalTO;
	uint waitingForStakesInTO ;
	string trajectory;

	// Contract constructor: set owner
	 constructor(address PkgCreator,address Seller,address Carrier,address Buyer,address DisputeResolver,uint256 MerchValue,uint256 ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO) payable
	{
	    seller = Seller;
	    buyer = Buyer;
	    carrier = Carrier;
	    disputeResolver = DisputeResolver;
	    merchValue = MerchValue;
	    shippingFee = ShippingFee;
	    ammountBuyer = 0;
	    ammountCarrier = 0;
	    ammountSeller = 0;
	    state = State.WaitingForStakesIn;
	    creationTime = now;
	    arrivalTO = ArrivalTO;
	    waitingForStakesInTO = WaitingForStakesInTO;
	    trajectory = new string; // check how to keep trajectory


	    //check if creator paid and update
	    if (PkgCreator == buyer)
	    {
	        ammountBuyer=msg.value;
	    }
	    else if (PkgCreator == seller)
	    {
	        ammountSeller=msg.value;
	    }
	    else if (PkgCreator == carrier)
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
        {}
            //todo: terminate
        else if (state == State.Shipped &&  now >= creationTime + arrivalTO)
        {}    //todo: terminate
        else if (state == State.Returned &&  now >= creationTime + 2*arrivalTO)
            //todo: terminate
        _;
    }

    function changeState()
    {
        state = State(uint(state) + 1);
    }
	//pay to contract, check who paid, update amount paid then change state
	function () public payable timedTransitions() {
	    if (msg.sender == buyer)
	    {
	        ammountBuyer=ammountBuyer+msg.value;
	    }
	    else if (msg.sender == seller)
	    {
	        ammountSeller=ammountSeller+msg.value;
	    }
	    else if (msg.sender == carrier)
	    {
	        ammountCarrier = ammountCarrier+msg.value;
	    }
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
	    trajectory = location; //todo: manage history of trajectory in array of strings
	}

	function returnPackage(string reason) public atState(State.Shipped){
	    require(msg.sender == buyer);
	    trajectory = reason; //todo: manage history of trajectory in array of strings
	    changeState();
	    buyer.transfer(merchValue);
	    ammountBuyer-=merchValue;
	    carrier.transfer(shippingFee);
	    ammountCarrier-=shippingFee;
	}


}
