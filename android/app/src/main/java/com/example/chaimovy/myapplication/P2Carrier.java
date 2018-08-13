package com.example.chaimovy.myapplication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class P2Carrier extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405160208061097c833981016040818152915160008054600160a060020a031916600160a060020a0383169081179091557fc3d2a535000000000000000000000000000000000000000000000000000000008352600160048401526024830152915173__../truffle/contracts/P2AddressSet.so__9163c3d2a535916044808301926020929190829003018186803b1580156100af57600080fd5b505af41580156100c3573d6000803e3d6000fd5b505050506040513d60208110156100d957600080fd5b505050610891806100eb6000396000f3006080604052600436106100985763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663707401ee811461009a578063715018a6146101015780637e33ff4b14610116578063893d20e8146101375780638da5cb5b14610168578063afe3c6101461017d578063c179d703146101b2578063f2e2c4af146101d3578063f2fde38b146101f7575b005b3480156100a657600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610098958335600160a060020a03169536956044949193909101919081908401838280828437509497506102189650505050505050565b34801561010d57600080fd5b50610098610471565b34801561012257600080fd5b50610098600160a060020a03600435166104e1565b34801561014357600080fd5b5061014c6105d9565b60408051600160a060020a039092168252519081900360200190f35b34801561017457600080fd5b5061014c6105e8565b34801561018957600080fd5b5061019e600160a060020a03600435166105f7565b604080519115158252519081900360200190f35b3480156101be57600080fd5b50610098600160a060020a03600435166106a7565b3480156101df57600080fd5b50610098600160a060020a0360043516602435610770565b34801561020357600080fd5b50610098600160a060020a03600435166107c1565b604080517f32cc1c5300000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0333166024820152905173__../truffle/contracts/P2AddressSet.so__916332cc1c53916044808301926020929190829003018186803b15801561029257600080fd5b505af41580156102a6573d6000803e3d6000fd5b505050506040513d60208110156102bc57600080fd5b505115156102c957600080fd5b7f5dca52be3de0dd469988fa9678424836d34c38c867f33b8b3634c9b3a4d027d93383836040518084600160a060020a0316600160a060020a0316815260200183600160a060020a0316600160a060020a0316815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561035a578181015183820152602001610342565b50505050905090810190601f1680156103875780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a16040517fd0498dd3000000000000000000000000000000000000000000000000000000008152602060048201818152835160248401528351600160a060020a0386169363d0498dd39386939283926044019185019080838360005b838110156104095781810151838201526020016103f1565b50505050905090810190601f1680156104365780820380516001836020036101000a031916815260200191505b5092505050600060405180830381600087803b15801561045557600080fd5b505af1158015610469573d6000803e3d6000fd5b505050505050565b60005433600160a060020a0390811691161461048c57600080fd5b60008054604051600160a060020a03909116917ff8df31144d9c2f0f6b59d69b8b98abd5459d07f2742c4df920b25aae33c6482091a26000805473ffffffffffffffffffffffffffffffffffffffff19169055565b60005433600160a060020a039081169116146104fc57600080fd5b604051600160a060020a038216907f42c11a7a3ead6edab391799fdcd01828e174df779fab85e9c53bfcb1caa157a890600090a2604080517f4bbed3d300000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905173__../truffle/contracts/P2AddressSet.so__91634bbed3d3916044808301926020929190829003018186803b1580156105aa57600080fd5b505af41580156105be573d6000803e3d6000fd5b505050506040513d60208110156105d457600080fd5b505050565b600054600160a060020a031690565b600054600160a060020a031681565b604080517f32cc1c5300000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905160009173__../truffle/contracts/P2AddressSet.so__916332cc1c5391604480820192602092909190829003018186803b15801561067557600080fd5b505af4158015610689573d6000803e3d6000fd5b505050506040513d602081101561069f57600080fd5b505192915050565b60005433600160a060020a039081169116146106c257600080fd5b604051600160a060020a038216907f1fcf49580398cec7111d47c06938189c65978f0e0b979f9e66686023e9f01d9d90600090a2604080517fc3d2a53500000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905173__../truffle/contracts/P2AddressSet.so__9163c3d2a535916044808301926020929190829003018186803b1580156105aa57600080fd5b60005433600160a060020a0390811691161461078b57600080fd5b604051600160a060020a0383169082156108fc029083906000818181858888f193505050501580156105d4573d6000803e3d6000fd5b60005433600160a060020a039081169116146107dc57600080fd5b6107e5816107e8565b50565b600160a060020a03811615156107fd57600080fd5b60008054604051600160a060020a03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a36000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790555600a165627a7a7230582085b530fa16954a9beb90ad24767d39d9c4f3dcdf35b6baf435993b1beb795c0d0029";

    public static final String FUNC_SIGNPACKAGE = "signPackage";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REMOVEDELIVERYSTATION = "removeDeliveryStation";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CONTAINSSTATION = "containsStation";

    public static final String FUNC_ADDDELIVERYSTATION = "addDeliveryStation";

    public static final String FUNC_SENDFUNDSTOPACKAGE = "sendFundsToPackage";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event DELIVERYSTATIONADDED_EVENT = new Event("DeliveryStationAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DELIVERYSTATIONREMOVED_EVENT = new Event("DeliveryStationRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event PACKAGESIGNED_EVENT = new Event("PackageSigned", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event OWNERSHIPRENOUNCED_EVENT = new Event("OwnershipRenounced", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    protected P2Carrier(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected P2Carrier(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> signPackage(String pkg, String location) {
        final Function function = new Function(
                FUNC_SIGNPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(pkg), 
                new org.web3j.abi.datatypes.Utf8String(location)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeDeliveryStation(String station) {
        final Function function = new Function(
                FUNC_REMOVEDELIVERYSTATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(station)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getOwner() {
        final Function function = new Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> containsStation(String station) {
        final Function function = new Function(FUNC_CONTAINSSTATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(station)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addDeliveryStation(String station) {
        final Function function = new Function(
                FUNC_ADDDELIVERYSTATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(station)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendFundsToPackage(String pkg, BigInteger ammount) {
        final Function function = new Function(
                FUNC_SENDFUNDSTOPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(pkg), 
                new org.web3j.abi.datatypes.generated.Uint256(ammount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<P2Carrier> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)));
        return deployRemoteCall(P2Carrier.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<P2Carrier> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)));
        return deployRemoteCall(P2Carrier.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<DeliveryStationAddedEventResponse> getDeliveryStationAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DELIVERYSTATIONADDED_EVENT, transactionReceipt);
        ArrayList<DeliveryStationAddedEventResponse> responses = new ArrayList<DeliveryStationAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeliveryStationAddedEventResponse typedResponse = new DeliveryStationAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.station = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DeliveryStationAddedEventResponse> deliveryStationAddedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, DeliveryStationAddedEventResponse>() {
            @Override
            public DeliveryStationAddedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DELIVERYSTATIONADDED_EVENT, log);
                DeliveryStationAddedEventResponse typedResponse = new DeliveryStationAddedEventResponse();
                typedResponse.log = log;
                typedResponse.station = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<DeliveryStationAddedEventResponse> deliveryStationAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELIVERYSTATIONADDED_EVENT));
        return deliveryStationAddedEventObservable(filter);
    }

    public List<DeliveryStationRemovedEventResponse> getDeliveryStationRemovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DELIVERYSTATIONREMOVED_EVENT, transactionReceipt);
        ArrayList<DeliveryStationRemovedEventResponse> responses = new ArrayList<DeliveryStationRemovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeliveryStationRemovedEventResponse typedResponse = new DeliveryStationRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.station = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DeliveryStationRemovedEventResponse> deliveryStationRemovedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, DeliveryStationRemovedEventResponse>() {
            @Override
            public DeliveryStationRemovedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DELIVERYSTATIONREMOVED_EVENT, log);
                DeliveryStationRemovedEventResponse typedResponse = new DeliveryStationRemovedEventResponse();
                typedResponse.log = log;
                typedResponse.station = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<DeliveryStationRemovedEventResponse> deliveryStationRemovedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELIVERYSTATIONREMOVED_EVENT));
        return deliveryStationRemovedEventObservable(filter);
    }

    public List<PackageSignedEventResponse> getPackageSignedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PACKAGESIGNED_EVENT, transactionReceipt);
        ArrayList<PackageSignedEventResponse> responses = new ArrayList<PackageSignedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PackageSignedEventResponse typedResponse = new PackageSignedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.pkg = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.location = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PackageSignedEventResponse> packageSignedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, PackageSignedEventResponse>() {
            @Override
            public PackageSignedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PACKAGESIGNED_EVENT, log);
                PackageSignedEventResponse typedResponse = new PackageSignedEventResponse();
                typedResponse.log = log;
                typedResponse.signer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.pkg = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.location = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<PackageSignedEventResponse> packageSignedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PACKAGESIGNED_EVENT));
        return packageSignedEventObservable(filter);
    }

    public List<OwnershipRenouncedEventResponse> getOwnershipRenouncedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, transactionReceipt);
        ArrayList<OwnershipRenouncedEventResponse> responses = new ArrayList<OwnershipRenouncedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnershipRenouncedEventResponse> ownershipRenouncedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipRenouncedEventResponse>() {
            @Override
            public OwnershipRenouncedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, log);
                OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OwnershipRenouncedEventResponse> ownershipRenouncedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPRENOUNCED_EVENT));
        return ownershipRenouncedEventObservable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventObservable(filter);
    }

    public static P2Carrier load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Carrier(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static P2Carrier load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Carrier(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class DeliveryStationAddedEventResponse {
        public Log log;

        public String station;
    }

    public static class DeliveryStationRemovedEventResponse {
        public Log log;

        public String station;
    }

    public static class PackageSignedEventResponse {
        public Log log;

        public String signer;

        public String pkg;

        public String location;
    }

    public static class OwnershipRenouncedEventResponse {
        public Log log;

        public String previousOwner;
    }

    public static class OwnershipTransferredEventResponse {
        public Log log;

        public String previousOwner;

        public String newOwner;
    }
}
