pragma solidity ^0.4.22;

library P2AddressSet {
  // We define a new struct datatype that will be used to
  // hold its data in the calling contract.
  struct set { mapping(address => bool) flags; }

  // Note that the first parameter is of type "storage
  // reference" and thus only its storage address and not
  // its contents is passed as part of the call.  This is a
  // special feature of library functions.  It is idiomatic
  // to call the first parameter `self`, if the function can
  // be seen as a method of that object.
  function insert(set storage self, address value)
      public
      returns (bool)
  {
      if (self.flags[value])
          return false; // already there
      self.flags[value] = true;
      return true;
  }

  function remove(set storage self, address value)
      public
      returns (bool)
  {
      if (!self.flags[value])
          return false; // not there
      self.flags[value] = false;
      return true;
  }

  function contains(set storage self, address value)
      public
      view
      returns (bool)
  {
      return self.flags[value];
  }
}
