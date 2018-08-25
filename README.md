# Project2SmartContract


##	Developer instructions – README

### Setting up the development environment
There are a few technical requirements. Please install the following:
1)	Node.js v6+ LTS and npm  - https://nodejs.org/en/
2)	Git - https://git-scm.com/

Next, install truffle by executing one command in your Node.js console:
                       
                       npm install –g truffle
                       
To deploy contracts on local block – chain node, for debugging and testing purposes (optional) download and install ganache:
                    
http://truffleframework.com/ganache
                    
For developing the front end side of the application, download and install android studio:

https://developer.android.com/studio/

### Clone project

                      git clone https://github.com/ykurtser/Project2SmartContract.git

### Contract developing, testing and deployment 
In the project directory, navigate to ./truffle. This directory contains:
1)	Contracts:  contains the solidity source files for our smart contracts.
2)	Migrations: Truffle uses a migration system to handle smart contract deployments. The migrations contract is an additional special smart contract that keeps track of changes to avoid unintentional spending.
3)	Test: contains both javascript and solidity tests for our contracts
4)	Truffle.js: a truffle configuration file.

Now, for editing or creating new contracts, go to ./contracts and edit .sol files.

To deploy new added contracts, you will have to update ./migrations/2_deploy_contracts.js for more details, go to 

https://truffleframework.com/docs/truffle/getting-started/running-migrations.


To deploy project's PackageManager developed by us, run command:

                             
                             truffle migrate –reset –network <network name> 

The –reset flag is for a new deployment of the contract, if not used the migrations contract will allow contract to be deployed only once, to avoid ether spending by mistake.
The –network flag specifies the network contract will be deployed to. Truffle provides a system for managing the compilation and deployment artifacts for each network, and does so in a way that simplifies final application deployment. The network must be configured in ./truffle.js.  the currently configured networks we have used are ganache (on local node), rinkeby and ropsten. For more details go to: https://truffleframework.com/docs/truffle/advanced/networks-and-app-deployment

For testing, all test files should be located in the ./test directory. Truffle will only run test files with the following file extensions: .js, .es, .es6, and .jsx, and .sol. All other files are ignored. Run command:

                     truffle test .pathToTestFile/testFile.js –network  <network name>
 
https://truffleframework.com/docs/truffle/testing/writing-tests-in-javascript
### Android app
To build and run the application, open ./android/app in android studio.

In order to interact with contracts with java, we use web3j library. Web3j provide a command line tool to create java adaptors to solidity contracts. If you wish to edit existing contracts or creating new ones, you will have to re generate this adaptors.
Install web3j command line tool:
 https://github.com/web3j/web3j/releases/tag/v3.5.0

For creating contract Java abstraction files:
1.	solc <.sol file path> --bin --abi --optimize -o (ex. solc ../truffle/contracts/P2PackageManager.sol --bin --abi --optimize -o ./)
2.	web3j solidity generate <.bin file path> <.abi file path> -o <project src/main path> -p <project's package name> (ex. web3j solidity generate ./P2Carrier.bin ./P2Carrier.abi -o ./../android/app/src/main/ -p com.example.chaimovy.myapplication)
For a better understanding on how to interact with contracts go to web3j documentation -

https://docs.web3j.io/index.html







