pragma solidity ^0.4.21;

import "./AddressSet.sol";
import "./Ownable.sol";
import "./Package.sol";

contract Carrier is Ownable{
    using AddressSet for AddressSet.set;
    AddressSet.set DeliveryStations;

    event DeliveryStationAdded(address indexed station);
    event DeliveryStationRemoved(address indexed station);
    event PackageSigned(address signer, Package package, string location);

    constructor(address _owner)
    Ownable(_owner)
    public {
        DeliveryStations.insert(_owner);
    }

    function addDeliveryStation(address station)
    public
    onlyOwner() {
        emit DeliveryStationAdded(station);
        DeliveryStations.insert(station);
    }

    function removeDeliveryStation(address station)
    public
    onlyOwner() {
        emit DeliveryStationRemoved(station);
        DeliveryStations.remove(station);
    }

    function signPackage(Package pkg, string location)
    public {
        require(DeliveryStations.contains(msg.sender));
        emit PackageSigned(msg.sender,pkg,location);
        pkg.signPackage(location);
    }

}
