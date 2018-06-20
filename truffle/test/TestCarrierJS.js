
const Carrier = artifacts.require('../contracts/Carrier.sol');


contract('Carrier', function ([carrierOwner,deliveryGuy1,deliveryGuy2,deliveryGuy3]) {
  let carrier;

  beforeEach('setup contract for each test', async function(){
      carrier = await Carrier.new(carrierOwner);
      console.log("new carrier created at address: " + carrier.address);
  })


  it('owner assigned', async function () {
    let _owner =await carrier.getOwner()
    assert.equal(_owner,carrierOwner)
  })
  it('add and remove stations+check for duplicates', async function () {
    let _owner =await carrier.getOwner()
    assert.isTrue(await carrier.containsStation(carrierOwner))
    assert.isFalse(await carrier.containsStation(deliveryGuy1))
    assert.isFalse(await carrier.containsStation(deliveryGuy2))
    await carrier.addDeliveryStation(deliveryGuy1)
    assert.isTrue(await carrier.containsStation(deliveryGuy1))
    assert.isFalse(await carrier.containsStation(deliveryGuy2))
    await carrier.addDeliveryStation(deliveryGuy2)
    assert.isTrue(await carrier.containsStation(deliveryGuy1))
    assert.isTrue(await carrier.containsStation(deliveryGuy2))
    await carrier.removeDeliveryStation(deliveryGuy1)
    assert.isFalse(await carrier.containsStation(deliveryGuy1))
    assert.isTrue(await carrier.containsStation(deliveryGuy2))
    await carrier.removeDeliveryStation(deliveryGuy2)
    assert.isFalse(await carrier.containsStation(deliveryGuy1))
    assert.isFalse(await carrier.containsStation(deliveryGuy2))
    await carrier.removeDeliveryStation(deliveryGuy3)
    assert.isFalse(await carrier.containsStation(deliveryGuy3))
    await carrier.addDeliveryStation(deliveryGuy1)
    assert.isTrue(await carrier.containsStation(deliveryGuy1))
    await carrier.addDeliveryStation(deliveryGuy1)
    await carrier.removeDeliveryStation(deliveryGuy1)
    assert.isFalse(await carrier.containsStation(deliveryGuy1))
    //removing owners address is currently possible



  })

});
