package com.example.chaimovy.myapplication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class P2Package extends Contract {
    private static final String BINARY = "6080604052604051610120806200156b833981018060405262000026919081019062000335565b6000805460028054600160a060020a0319908116600160a060020a038b8116919091179092556001805482168c84161790556004805482163384161790556003805490911689831617905560058790556006869055600783905560088390556009839055600e83905561010060a860020a0319909116610100918b16919091021760ff1916905542600a908155600b839055600c82905560408051828152610160810190915290602082015b6060815260200190600190039081620000d25750508051620000fd91600d916020909101906200019d565b5060003411156200011c576200011c896401000000006200012b810204565b50505050505050505062000418565b600254600160a060020a0382811691161415620001505760078054340190556200019a565b600054600160a060020a038281166101009092041614156200017a5760098054340190556200019a565b600154600160a060020a03828116911614156200019a5760088054340190555b50565b828054828255906000526020600020908101928215620001ef579160200282015b82811115620001ef5782518051620001de91849160209091019062000201565b5091602001919060010190620001be565b50620001fd92915062000282565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200024457805160ff191683800117855562000274565b8280016001018555821562000274579182015b828111156200027457825182559160200191906001019062000257565b50620001fd929150620002ad565b620002aa91905b80821115620001fd576000620002a08282620002ca565b5060010162000289565b90565b620002aa91905b80821115620001fd5760008155600101620002b4565b50805460018160011615610100020316600290046000825580601f10620002f257506200019a565b601f0160209004906000526020600020908101906200019a9190620002ad565b60006200032082516200040c565b9392505050565b6000620003208251620002aa565b60008060008060008060008060006101208a8c0312156200035557600080fd5b6000620003638c8c62000312565b9950506020620003768c828d0162000312565b9850506040620003898c828d0162000312565b97505060606200039c8c828d0162000312565b9650506080620003af8c828d0162000312565b95505060a0620003c28c828d0162000327565b94505060c0620003d58c828d0162000327565b93505060e0620003e88c828d0162000327565b925050610100620003fc8c828d0162000327565b9150509295985092959850929598565b600160a060020a031690565b61114380620004286000396000f3006080604052600436106101325763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663155e8b43811461019c5780631865c57d146101c75780631fda9de4146101dc578063260519b9146101fe5780632c25eabd1461021e578063305232d0146102335780633300a6cd146102485780633c77b93c1461025d5780633e91a175146102725780634c38d877146102335780634fd6137c146102875780635c63ed691461029c578063603daf9a146102b1578063694a2e20146102c6578063a4972638146102e8578063b1b34a20146102fd578063c2b7b86814610312578063d0498dd314610332578063d47cffcb14610352578063dbd0e1b614610367578063f0a44b7a1461037c578063f4aec71e14610391578063ff182cb6146103be575b61013b336103d3565b610143610440565b6007541015801561015d575061015761044b565b60095410155b8015610172575061016c610440565b60085410155b801561018d57506000805460ff16600381111561018b57fe5b145b1561019a5761019a610454565b005b3480156101a857600080fd5b506101b16104d8565b6040516101be9190611050565b60405180910390f35b3480156101d357600080fd5b506101b16104de565b3480156101e857600080fd5b506101f16104f5565b6040516101be919061101a565b34801561020a57600080fd5b5061019a610219366004610f0b565b610504565b34801561022a57600080fd5b506101b1610616565b34801561023f57600080fd5b506101b1610440565b34801561025457600080fd5b506101b161061c565b34801561026957600080fd5b506101b1610622565b34801561027e57600080fd5b506101b1610628565b34801561029357600080fd5b5061019a61062e565b3480156102a857600080fd5b506101b161044b565b3480156102bd57600080fd5b506101f161066f565b3480156102d257600080fd5b506102db61067e565b6040516101be919061102e565b3480156102f457600080fd5b506101b1610756565b34801561030957600080fd5b506101b161075c565b34801561031e57600080fd5b5061019a61032d366004610f48565b610762565b34801561033e57600080fd5b5061019a61034d366004610f0b565b610852565b34801561035e57600080fd5b506101b1610a13565b34801561037357600080fd5b506101f1610a22565b34801561038857600080fd5b506101f1610a36565b34801561039d57600080fd5b506103b16103ac366004610f48565b610a45565b6040516101be919061103f565b3480156103ca57600080fd5b506101b1610af0565b600254600160a060020a03828116911614156103f657600780543401905561043d565b600054600160a060020a0382811661010090920416141561041e57600980543401905561043d565b600154600160a060020a038281169116141561043d5760088054340190555b50565b600654600554015b90565b60065460020290565b60005460ff16600381111561046557fe5b600101600381111561047357fe5b6000805460ff1916600183600381111561048957fe5b02179055506000547f38526e5054fb54946813ae7aae6f3447cd6739b6cfd8ea481d64c4a61d8767939060ff1660038111156104c157fe5b6040516104ce9190611050565b60405180910390a1565b600c5490565b6000805460ff1660038111156104f057fe5b905090565b600154600160a060020a031690565b60018060005460ff16600381111561051857fe5b1461052257600080fd5b60025433600160a060020a0390811691161461053d57600080fd5b81600d600e5481548110151561054f57fe5b90600052602060002001908051906020019061056c929190610e0c565b50600e8054600101905561057e610454565b600254600554604051600160a060020a039092169181156108fc0291906000818181858888f193505050501580156105ba573d6000803e3d6000fd5b5060055460078054919091039055600154600654604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610604573d6000803e3d6000fd5b50506006546008805491909103905550565b60065490565b60095490565b60055490565b600b5490565b60028060005460ff16600381111561064257fe5b1461064c57600080fd5b60015433600160a060020a0390811691161461066757600080fd5b61043d610454565b600254600160a060020a031690565b6060600d805480602002602001604051908101604052809291908181526020016000905b8282101561074d5760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156107395780601f1061070e57610100808354040283529160200191610739565b820191906000526020600020905b81548152906001019060200180831161071c57829003601f168201915b5050505050815260200190600101906106a2565b50505050905090565b60085490565b60075490565b60038060005460ff16600381111561077657fe5b1461078057600080fd5b60035433600160a060020a0390811691161461079b57600080fd5b6000546101009004600160a060020a03166108fc6064846107ba610a13565b028115156107c457fe5b049081150290604051600060405180830381858888f193505050501580156107f0573d6000803e3d6000fd5b50600154600160a060020a03166108fc606484810361080d610a13565b0281151561081757fe5b049081150290604051600060405180830381858888f19350505050158015610843573d6000803e3d6000fd5b50600454600160a060020a0316ff5b6000805460ff16600381111561086457fe5b1480156108775750600c54600a54014210155b1561088957610884610af6565b6108f2565b600160005460ff16600381111561089c57fe5b1480156108af5750600b54600a54014210155b156108bc57610884610bfe565b600260005460ff1660038111156108cf57fe5b1480156108e55750600b54600202600a54014210155b156108f2576108f2610ca1565b600160005460ff16600381111561090557fe5b148015610938575060015433600160a060020a0390811691161480610938575060025433600160a060020a039081169116145b806109895750600260005460ff16600381111561095157fe5b148015610989575060015433600160a060020a0390811691161480610989575060005433600160a060020a0390811661010090920416145b151561099457600080fd5b60005433600160a060020a039081166101009092041614156109b8576109b8610cff565b60025433600160a060020a03908116911614156109d7576109d7610d61565b80600d600e548154811015156109e957fe5b906000526020600020019080519060200190610a06929190610e0c565b5050600e80546001019055565b60065460055460029091020190565b6000546101009004600160a060020a031690565b600354600160a060020a031690565b6060600d82815481101515610a5657fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610ae45780601f10610ab957610100808354040283529160200191610ae4565b820191906000526020600020905b815481529060010190602001808311610ac757829003601f168201915b50505050509050919050565b600e5490565b60008060005460ff166003811115610b0a57fe5b14610b1457600080fd5b60006008541115610b5d57600154600854604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610b5b573d6000803e3d6000fd5b505b60006007541115610ba657600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610ba4573d6000803e3d6000fd5b505b60006009541115610bf05760008054600954604051610100909204600160a060020a0316926108fc8215029290818181858888f19350505050158015610843573d6000803e3d6000fd5b600454600160a060020a0316ff5b60018060005460ff166003811115610c1257fe5b14610c1c57600080fd5b600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610c58573d6000803e3d6000fd5b5060008054600954600854604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610843573d6000803e3d6000fd5b60028060005460ff166003811115610cb557fe5b14610cbf57600080fd5b600154600954600854604051600160a060020a0390931692910180156108fc02916000818181858888f19350505050158015610843573d6000803e3d6000fd5b60028060005460ff166003811115610d1357fe5b14610d1d57600080fd5b600154600654600554604051600160a060020a039093169260039092020180156108fc02916000818181858888f19350505050158015610843573d6000803e3d6000fd5b60018060005460ff166003811115610d7557fe5b14610d7f57600080fd5b60008054600654600554604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610dc7573d6000803e3d6000fd5b50600154600654600554604051600160a060020a039093169260029092020180156108fc02916000818181858888f19350505050158015610843573d6000803e3d6000fd5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e4d57805160ff1916838001178555610e7a565b82800160010185558215610e7a579182015b82811115610e7a578251825591602001919060010190610e5f565b50610e86929150610e8a565b5090565b61044891905b80821115610e865760008155600101610e90565b6000601f82018313610eb557600080fd5b8135610ec8610ec382611085565b61105e565b91508082526020830160208301858383011115610ee457600080fd5b610eef8382846110c3565b50505092915050565b6000610f048235610448565b9392505050565b600060208284031215610f1d57600080fd5b813567ffffffffffffffff811115610f3457600080fd5b610f4084828501610ea4565b949350505050565b600060208284031215610f5a57600080fd5b6000610f408484610ef8565b610f6f816110b7565b82525050565b6000610f80826110b3565b80845260208401935083602082028501610f99856110ad565b60005b84811015610fd0578383038852610fb4838351610fdc565b9250610fbf826110ad565b602098909801979150600101610f9c565b50909695505050505050565b6000610fe7826110b3565b808452610ffb8160208601602086016110cf565b611004816110ff565b9093016020019392505050565b610f6f81610448565b602081016110288284610f66565b92915050565b60208082528101610f048184610f75565b60208082528101610f048184610fdc565b602081016110288284611011565b60405181810167ffffffffffffffff8111828210171561107d57600080fd5b604052919050565b600067ffffffffffffffff82111561109c57600080fd5b506020601f91909101601f19160190565b60200190565b5190565b600160a060020a031690565b82818337506000910152565b60005b838110156110ea5781810151838201526020016110d2565b838111156110f9576000848401525b50505050565b601f01601f1916905600a265627a7a723058205eef6f2668b2396708dcc828b90b11c26ea19150540428b0ce7e0dfa683b52436c6578706572696d656e74616cf50037";

    public static final String FUNC_GETSTAKESINTO = "getStakesInTo";

    public static final String FUNC_GETSTATE = "getState";

    public static final String FUNC_GETCARRIER = "getCarrier";

    public static final String FUNC_RETURNPACKAGE = "returnPackage";

    public static final String FUNC_GETSHIPPINGFEE = "getShippingFee";

    public static final String FUNC_GETCARRIERSTAKE = "getCarrierStake";

    public static final String FUNC_GETAMMOUNTSELLER = "getAmmountSeller";

    public static final String FUNC_GETMERCHVAL = "getMerchVal";

    public static final String FUNC_GETARRIVALTO = "getarrivalTO";

    public static final String FUNC_GETBUYERSTAKE = "getBuyerStake";

    public static final String FUNC_OPENDISPUTE = "openDispute";

    public static final String FUNC_GETSELLERSTAKE = "getSellerStake";

    public static final String FUNC_GETBUYER = "getBuyer";

    public static final String FUNC_GETTRAJECTORY = "getTrajectory";

    public static final String FUNC_GETAMMOUNTCARRIER = "getAmmountCarrier";

    public static final String FUNC_GETAMMOUNTBUYER = "getAmmountBuyer";

    public static final String FUNC_RESOLVEDISPUTE = "resolveDispute";

    public static final String FUNC_SIGNPACKAGE = "signPackage";

    public static final String FUNC_GETDISPUTEFULLAMMOUNT = "getDisputeFullAmmount";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final String FUNC_GETDISPUTERESOLVER = "getDisputeResolver";

    public static final String FUNC_GETTRAJECTORYI = "getTrajectoryI";

    public static final String FUNC_GETTRAJECTORYSIZE = "getTrajectorySize";

    public static final Event UINTFIELDASSIGNED_EVENT = new Event("uintFieldAssigned", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHANGEDSTATE_EVENT = new Event("changedState", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected P2Package(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected P2Package(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> getStakesInTo() {
        final Function function = new Function(FUNC_GETSTAKESINTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getState() {
        final Function function = new Function(FUNC_GETSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static String getStateString(Integer state) {
        switch (state) {
            case 0:
                return "Waiting for stakes in";
            case 1:
                return "On the way to buyer";
            case 2:
                return "on the way back to seller";
            case 3:
                return "under dispute";
        }
        return "";
    }

    public RemoteCall<String> getCarrier() {
        final Function function = new Function(FUNC_GETCARRIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> returnPackage(String reason) {
        final Function function = new Function(
                FUNC_RETURNPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(reason)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getShippingFee() {
        final Function function = new Function(FUNC_GETSHIPPINGFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getCarrierStake() {
        final Function function = new Function(FUNC_GETCARRIERSTAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getAmmountSeller() {
        final Function function = new Function(FUNC_GETAMMOUNTSELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMerchVal() {
        final Function function = new Function(FUNC_GETMERCHVAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getarrivalTO() {
        final Function function = new Function(FUNC_GETARRIVALTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBuyerStake() {
        final Function function = new Function(FUNC_GETBUYERSTAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> openDispute() {
        final Function function = new Function(
                FUNC_OPENDISPUTE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getSellerStake() {
        final Function function = new Function(FUNC_GETSELLERSTAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getBuyer() {
        final Function function = new Function(FUNC_GETBUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getTrajectory() {
        final Function function = new Function(FUNC_GETTRAJECTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> getAmmountCarrier() {
        final Function function = new Function(FUNC_GETAMMOUNTCARRIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getAmmountBuyer() {
        final Function function = new Function(FUNC_GETAMMOUNTBUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> resolveDispute(BigInteger sellersCut) {
        final Function function = new Function(
                FUNC_RESOLVEDISPUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sellersCut)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> signPackage(String location) {
        final Function function = new Function(
                FUNC_SIGNPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(location)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getDisputeFullAmmount() {
        final Function function = new Function(FUNC_GETDISPUTEFULLAMMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getSeller() {
        final Function function = new Function(FUNC_GETSELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getDisputeResolver() {
        final Function function = new Function(FUNC_GETDISPUTERESOLVER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getTrajectoryI(BigInteger i) {
        final Function function = new Function(FUNC_GETTRAJECTORYI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getTrajectorySize() {
        final Function function = new Function(FUNC_GETTRAJECTORYSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<P2Package> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String PkgCreator, String Seller, String Carrier, String Buyer, String DisputeResolver, BigInteger MerchValue, BigInteger ShippingFee, BigInteger ArrivalTO, BigInteger WaitingForStakesInTO) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(PkgCreator), 
                new org.web3j.abi.datatypes.Address(Seller), 
                new org.web3j.abi.datatypes.Address(Carrier), 
                new org.web3j.abi.datatypes.Address(Buyer), 
                new org.web3j.abi.datatypes.Address(DisputeResolver), 
                new org.web3j.abi.datatypes.generated.Uint256(MerchValue), 
                new org.web3j.abi.datatypes.generated.Uint256(ShippingFee), 
                new org.web3j.abi.datatypes.generated.Uint256(ArrivalTO), 
                new org.web3j.abi.datatypes.generated.Uint256(WaitingForStakesInTO)));
        return deployRemoteCall(P2Package.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<P2Package> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String PkgCreator, String Seller, String Carrier, String Buyer, String DisputeResolver, BigInteger MerchValue, BigInteger ShippingFee, BigInteger ArrivalTO, BigInteger WaitingForStakesInTO) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(PkgCreator), 
                new org.web3j.abi.datatypes.Address(Seller), 
                new org.web3j.abi.datatypes.Address(Carrier), 
                new org.web3j.abi.datatypes.Address(Buyer), 
                new org.web3j.abi.datatypes.Address(DisputeResolver), 
                new org.web3j.abi.datatypes.generated.Uint256(MerchValue), 
                new org.web3j.abi.datatypes.generated.Uint256(ShippingFee), 
                new org.web3j.abi.datatypes.generated.Uint256(ArrivalTO), 
                new org.web3j.abi.datatypes.generated.Uint256(WaitingForStakesInTO)));
        return deployRemoteCall(P2Package.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public List<UintFieldAssignedEventResponse> getUintFieldAssignedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UINTFIELDASSIGNED_EVENT, transactionReceipt);
        ArrayList<UintFieldAssignedEventResponse> responses = new ArrayList<UintFieldAssignedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UintFieldAssignedEventResponse typedResponse = new UintFieldAssignedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fieldName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UintFieldAssignedEventResponse> uintFieldAssignedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, UintFieldAssignedEventResponse>() {
            @Override
            public UintFieldAssignedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UINTFIELDASSIGNED_EVENT, log);
                UintFieldAssignedEventResponse typedResponse = new UintFieldAssignedEventResponse();
                typedResponse.log = log;
                typedResponse.fieldName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<UintFieldAssignedEventResponse> uintFieldAssignedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UINTFIELDASSIGNED_EVENT));
        return uintFieldAssignedEventObservable(filter);
    }

    public List<ChangedStateEventResponse> getChangedStateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGEDSTATE_EVENT, transactionReceipt);
        ArrayList<ChangedStateEventResponse> responses = new ArrayList<ChangedStateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangedStateEventResponse typedResponse = new ChangedStateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.currState = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChangedStateEventResponse> changedStateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChangedStateEventResponse>() {
            @Override
            public ChangedStateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGEDSTATE_EVENT, log);
                ChangedStateEventResponse typedResponse = new ChangedStateEventResponse();
                typedResponse.log = log;
                typedResponse.currState = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ChangedStateEventResponse> changedStateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGEDSTATE_EVENT));
        return changedStateEventObservable(filter);
    }

    public static P2Package load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Package(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static P2Package load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Package(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class UintFieldAssignedEventResponse {
        public Log log;

        public String fieldName;

        public BigInteger value;
    }

    public static class ChangedStateEventResponse {
        public Log log;

        public BigInteger currState;
    }
}
