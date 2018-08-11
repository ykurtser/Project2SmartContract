// Version of Solidity compiler this program was written for
pragma solidity ^0.4.21;
import "./P2Carrier.sol";


contract P2PackageManager is P2Ownable{
    event contractCreated(address addr);

    constructor()
    P2Ownable(msg.sender)
    public {}

    /********************************************************************************************************************************
    * requires: merchValue >=0, shippingFee>=0, ArrivalTO (maximum time to package arrival) >=0, WaitingForStakesInTO (maximum time to payments >=0)
    * modifies: a transaction, changes blockchain state
    * effects: create a new package contract, emits event containing it's address
    *********************************************************************************************************************************/
    function createPackage(address Seller,address Carrier,address Buyer,address DisputeResolver,uint MerchValue,uint ShippingFee, uint ArrivalTO, uint WaitingForStakesInTO)
    public
    {
        P2Package newPackege = new P2Package(msg.sender,Seller,Carrier,Buyer,DisputeResolver,MerchValue,ShippingFee,ArrivalTO,WaitingForStakesInTO);
        emit contractCreated(address(newPackege));
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: create a new carrier contract, emits event containing it's address
    *********************************************************************************************************************************/
    function createCarrier()
    public
    {
        P2Carrier newCarrier = new P2Carrier(msg.sender);
        emit contractCreated(address(newCarrier));
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: if massage sender is the owner, transfer all contract's balance to owner
    *********************************************************************************************************************************/
    function claimExcessEth()
    onlyOwner()
    public
    returns (address)
    {
        owner.transfer(address(this).balance);
    }
    /********************************************************************************************************************************
    * effects: get conract owners
    *********************************************************************************************************************************/
    function getOwner()
    public
    view
    returns (address)
    {
        return (owner);
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: default function to accept ether transfered to contract
    *********************************************************************************************************************************/
    function () public payable
    {

    }


}
