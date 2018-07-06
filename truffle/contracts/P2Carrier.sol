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
    function () public payable
    {

    }

    function addDeliveryStation(address station)
    public
    onlyOwner()
    {
        emit DeliveryStationAdded(station);
        DeliveryStations.insert(station);
    }

    function removeDeliveryStation(address station)
    public
    onlyOwner()
    {
        emit DeliveryStationRemoved(station);
        DeliveryStations.remove(station);
    }
    function sendFundsToPackage(address pkg, uint ammount)
    public
    onlyOwner()
    {
        pkg.transfer(ammount);
    }

    function signPackage(P2Package pkg, string location)
    public {
        require(DeliveryStations.contains(msg.sender));
        emit PackageSigned(msg.sender,pkg,location);
        pkg.signPackage(location);
    }
    function getOwner()
    public
    view
    returns (address)
    {
        return (owner);
    }
    function containsStation(address station)
    public
    view
    returns (bool)
    {
        return (DeliveryStations.contains(station));
    }

}
