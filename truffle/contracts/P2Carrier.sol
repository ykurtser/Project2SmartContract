pragma solidity ^0.4.21;

import "./P2AddressSet.sol";
import "./P2Ownable.sol";
import "./P2Package.sol";

contract P2Carrier is P2Ownable{
    using P2AddressSet for P2AddressSet.set;
    P2AddressSet.set DeliveryStations;

    event DeliveryStationAdded(address indexed station);
    event DeliveryStationRemoved(address indexed station);
    event PackageSigned(address signer, P2Package pkg, string location);

    constructor(address _owner)
    P2Ownable(_owner)
    public {
        DeliveryStations.insert(_owner);
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: default function to accept ether transfered to contract
    *********************************************************************************************************************************/
    function () public payable
    {

    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: add delivery station to set of autorized signers
    *********************************************************************************************************************************/
    function addDeliveryStation(address station)
    public
    onlyOwner()
    {
        emit DeliveryStationAdded(station);
        DeliveryStations.insert(station);
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: remove delivery station to set of autorized signers
    *********************************************************************************************************************************/
    function removeDeliveryStation(address station)
    public
    onlyOwner()
    {
        emit DeliveryStationRemoved(station);
        DeliveryStations.remove(station);
    }

    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: tranfer funds to a package
    *********************************************************************************************************************************/
    function sendFundsToPackage(address pkg, uint ammount)
    public
    onlyOwner()
    {
        pkg.transfer(ammount);
    }
    /********************************************************************************************************************************
    * modifies: a transaction, changes blockchain state
    * effects: sign a package
    *********************************************************************************************************************************/
    function signPackage(P2Package pkg, string location)
    public {
        require(DeliveryStations.contains(msg.sender));
        emit PackageSigned(msg.sender,pkg,location);
        pkg.signPackage(location);
    }

    // get conract carrier
    function getOwner()
    public
    view
    returns (address)
    {
        return (owner);
    }
    
    // check if station exist in the autohrized signers set
    function containsStation(address station)
    public
    view
    returns (bool)
    {
        return (DeliveryStations.contains(station));
    }

}
