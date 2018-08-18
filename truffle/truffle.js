var HDWalletProvider = require("truffle-hdwallet-provider");
var mnemonic = "want sorry dawn anxiety poverty armed recycle exchange jungle owner tooth wage"

module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // for more about customizing your Truffle configuration!

  networks: {
    development: {
      host: "127.0.0.1",
      port: 8545,
      network_id: "*" // Match any network id
    },
    ganache: {
      host: "127.0.0.1",
      port: 7545,
      network_id: "*" // Match any network id
    },
    ropsten: {
          provider: function() {
            return new HDWalletProvider(mnemonic, "https://ropsten.infura.io/06KZcq3hzQMYyXUurR9q")
          },
          network_id: 3,
          gas: 4600000
      },
    rinkeby: {
        provider: function() {
              return new HDWalletProvider(mnemonic, "https://rinkeby.infura.io/06KZcq3hzQMYyXUurR9q")
        },
        network_id: 3
    }
  }
};

mocha: {
  useColors: true
}
