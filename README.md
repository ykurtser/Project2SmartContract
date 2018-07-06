# Project2SmartContract

## book / literature

1)Mastering bitcoin - 2nd edition:
>https://github.com/bitcoinbook/bitcoinbook
    https://www.safaribooksonline.com/library/view/mastering-bitcoin-2nd/9781491954379/

2)Mastering ethereum:
>https://github.com/ethereumbook/ethereumbook

3)Official Ethereum white paper:
>https://github.com/ethereum/wiki/wiki/White-Paper

4)Official Ethereum yellow paper:
>https://ethereum.github.io/yellowpaper/paper.pdf

5)solidity full documentation:
>https://solidity.readthedocs.io/en/latest/

6)upgradeability-using-unstructured-storage -
>https://blog.zeppelinos.org/upgradeability-using-unstructured-storage/    

7)testing with truffle docs:
>http://truffleframework.com/docs/getting_started/testing

## Tools etc

1)Truffle Framework - documentation and tutorials:
>http://truffleframework.com/

2)Open zeppelin - framework of reusable and secure smart contracts in Solidity
>https://openzeppelin.org/

3)Ropsten testnet virtual network block explorer -
>http://ropsten.etherscan.io

4)Online IDEs:  

Remix
>https://remix.ethereum.org/#optimize=false&version=soljson-v0.4.19+commit.c4cbbb05.js

ethfiddle
>https://ethfiddle.com/

##misc.

gas statistics
>https://ethgasstation.info/

## android

1) web3j library:  
>https://web3j.io/

## Our work outside github:
Design Doc:
>https://docs.google.com/document/d/1SY4MZ2lpgaZFmno_a3G7N8HybsHYpK0T1cBQ6YVagMg/edit





for creating contract Java abstraction files:
1) solc <.sol file path> --bin --abi --optimize -o <out files path>     (ex. solc ../truffle/contracts/P2PackageManager.sol --bin --abi --optimize -o ./)
2) web3j solidity generate <.bin file path> <.abi file path> -o <project src/main path> -p <project's package name>
(ex. web3j solidity generate ./P2Carrier.bin ./P2Carrier.abi -o ./../android/app/src/main/ -p com.example.chaimovy.myapplication)
