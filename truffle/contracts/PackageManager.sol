// Version of Solidity compiler this program was written for
pragma solidity ^0.4.21;
import "./Carrier.sol";


contract PackageManager is Ownable{

    constructor()
    Ownable(msg.sender)
    public {}

    function createPackage(address Seller,address Carrier,address Buyer,address DisputeResolver,uint MerchValue,uint ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO)
    public
    returns (address)
    {
        return new Package(msg.sender,Seller,Carrier,Buyer,DisputeResolver,MerchValue,ShippingFee,ArrivalTO, WaitingForStakesInTO);
    }

    function createCarrier()
    public
    returns (address)
    {
        return new Carrier(msg.sender);
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
