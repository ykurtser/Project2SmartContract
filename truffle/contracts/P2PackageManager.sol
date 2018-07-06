// Version of Solidity compiler this program was written for
pragma solidity ^0.4.21;
import "./P2Carrier.sol";


contract P2PackageManager is P2Ownable{
    event contractCreated(address addr);
    constructor()
    P2Ownable(msg.sender)
    public {}

    function createPackage(address Seller,address Carrier,address Buyer,address DisputeResolver,uint MerchValue,uint ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO)
    public
    {
        P2Package newPackege = new P2Package(msg.sender,Seller,Carrier,Buyer,DisputeResolver,MerchValue,ShippingFee,ArrivalTO,WaitingForStakesInTO);
        emit contractCreated(address(newPackege));
    }

    function createCarrier()
    public
    {
        P2Carrier newCarrier = new P2Carrier(msg.sender);
        emit contractCreated(address(newCarrier));
    }

    function claimExcessEth()
    onlyOwner()
    public
    returns (address)
    {
        owner.transfer(address(this).balance);
    }
    function getOwner()
    public
    view
    returns (address)
    {
        return (owner);
    }

    function () public payable
    {

    }


}
