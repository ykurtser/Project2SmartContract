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
    private static final String BINARY = "608060405260405161012080620017c6833981018060405262000026919081019062000335565b6000805460028054600160a060020a0319908116600160a060020a038b8116919091179092556001805482168c84161790556004805482163384161790556003805490911689831617905560058790556006869055600783905560088390556009839055600e83905561010060a860020a0319909116610100918b16919091021760ff1916905542600a908155600b839055600c82905560408051828152610160810190915290602082015b6060815260200190600190039081620000d25750508051620000fd91600d916020909101906200019d565b5060003411156200011c576200011c896401000000006200012b810204565b50505050505050505062000418565b600254600160a060020a0382811691161415620001505760078054340190556200019a565b600054600160a060020a038281166101009092041614156200017a5760098054340190556200019a565b600154600160a060020a03828116911614156200019a5760088054340190555b50565b828054828255906000526020600020908101928215620001ef579160200282015b82811115620001ef5782518051620001de91849160209091019062000201565b5091602001919060010190620001be565b50620001fd92915062000282565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200024457805160ff191683800117855562000274565b8280016001018555821562000274579182015b828111156200027457825182559160200191906001019062000257565b50620001fd929150620002ad565b620002aa91905b80821115620001fd576000620002a08282620002ca565b5060010162000289565b90565b620002aa91905b80821115620001fd5760008155600101620002b4565b50805460018160011615610100020316600290046000825580601f10620002f257506200019a565b601f0160209004906000526020600020908101906200019a9190620002ad565b60006200032082516200040c565b9392505050565b6000620003208251620002aa565b60008060008060008060008060006101208a8c0312156200035557600080fd5b6000620003638c8c62000312565b9950506020620003768c828d0162000312565b9850506040620003898c828d0162000312565b97505060606200039c8c828d0162000312565b9650506080620003af8c828d0162000312565b95505060a0620003c28c828d0162000327565b94505060c0620003d58c828d0162000327565b93505060e0620003e88c828d0162000327565b925050610100620003fc8c828d0162000327565b9150509295985092959850929598565b600160a060020a031690565b61139e80620004286000396000f30060806040526004361061013d5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663155e8b4381146101a75780631865c57d146101d25780631fda9de4146101e7578063260519b9146102095780632c25eabd14610229578063305232d01461023e5780633300a6cd146102535780633c77b93c146102685780633e91a1751461027d5780634c38d8771461023e5780634fd6137c146102925780635c63ed69146102a7578063603daf9a146102bc578063694a2e20146102d1578063a4972638146102f3578063b1b34a2014610308578063b603cd801461031d578063c2b7b86814610332578063d0498dd314610352578063d47cffcb14610372578063dbd0e1b614610387578063f0a44b7a1461039c578063f4aec71e146103b1578063ff182cb6146103de575b610146336103f3565b61014e610460565b60075410158015610168575061016261046b565b60095410155b801561017d5750610177610460565b60085410155b801561019857506000805460ff16600481111561019657fe5b145b156101a5576101a5610474565b005b3480156101b357600080fd5b506101bc6104f8565b6040516101c991906112ab565b60405180910390f35b3480156101de57600080fd5b506101bc6104fe565b3480156101f357600080fd5b506101fc610515565b6040516101c99190611275565b34801561021557600080fd5b506101a5610224366004611166565b610524565b34801561023557600080fd5b506101bc610636565b34801561024a57600080fd5b506101bc610460565b34801561025f57600080fd5b506101bc61063c565b34801561027457600080fd5b506101bc610642565b34801561028957600080fd5b506101bc610648565b34801561029e57600080fd5b506101a561064e565b3480156102b357600080fd5b506101bc61046b565b3480156102c857600080fd5b506101fc61068f565b3480156102dd57600080fd5b506102e661069e565b6040516101c99190611289565b3480156102ff57600080fd5b506101bc610776565b34801561031457600080fd5b506101bc61077c565b34801561032957600080fd5b506101a5610782565b34801561033e57600080fd5b506101a561034d3660046111a3565b6107ae565b34801561035e57600080fd5b506101a561036d366004611166565b61090a565b34801561037e57600080fd5b506101bc610acb565b34801561039357600080fd5b506101fc610ada565b3480156103a857600080fd5b506101fc610aee565b3480156103bd57600080fd5b506103d16103cc3660046111a3565b610afd565b6040516101c9919061129a565b3480156103ea57600080fd5b506101bc610ba8565b600254600160a060020a038281169116141561041657600780543401905561045d565b600054600160a060020a0382811661010090920416141561043e57600980543401905561045d565b600154600160a060020a038281169116141561045d5760088054340190555b50565b600654600554015b90565b60065460020290565b60005460ff16600481111561048557fe5b600101600481111561049357fe5b6000805460ff191660018360048111156104a957fe5b02179055506000547f38526e5054fb54946813ae7aae6f3447cd6739b6cfd8ea481d64c4a61d8767939060ff1660048111156104e157fe5b6040516104ee91906112ab565b60405180910390a1565b600c5490565b6000805460ff16600481111561051057fe5b905090565b600154600160a060020a031690565b60018060005460ff16600481111561053857fe5b1461054257600080fd5b60025433600160a060020a0390811691161461055d57600080fd5b81600d600e5481548110151561056f57fe5b90600052602060002001908051906020019061058c929190611067565b50600e8054600101905561059e610474565b600254600554604051600160a060020a039092169181156108fc0291906000818181858888f193505050501580156105da573d6000803e3d6000fd5b5060055460078054919091039055600154600654604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610624573d6000803e3d6000fd5b50506006546008805491909103905550565b60065490565b60095490565b60055490565b600b5490565b60028060005460ff16600481111561066257fe5b1461066c57600080fd5b60015433600160a060020a0390811691161461068757600080fd5b61045d610474565b600254600160a060020a031690565b6060600d805480602002602001604051908101604052809291908181526020016000905b8282101561076d5760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156107595780601f1061072e57610100808354040283529160200191610759565b820191906000526020600020905b81548152906001019060200180831161073c57829003601f168201915b5050505050815260200190600101906106c2565b50505050905090565b60085490565b60075490565b60048060005460ff16600481111561079657fe5b146107a057600080fd5b600454600160a060020a0316ff5b60038060005460ff1660048111156107c257fe5b146107cc57600080fd5b60035433600160a060020a039081169116146107e757600080fd5b6000546101009004600160a060020a03166108fc606484610806610acb565b0281151561081057fe5b049081150290604051600060405180830381858888f1935050505015801561083c573d6000803e3d6000fd5b50600154600160a060020a03166108fc6064848103610859610acb565b0281151561086357fe5b049081150290604051600060405180830381858888f1935050505015801561088f573d6000803e3d6000fd5b506040805190810160405280601981526020017f436f6e7472616374207761736e27742061637469766174656400000000000000815250600d600e548154811015156108d757fe5b9060005260206000200190805190602001906108f4929190611067565b50600e80546001019055610906610bae565b5050565b6000805460ff16600481111561091c57fe5b14801561092f5750600c54600a54014210155b156109415761093c610bc2565b6109aa565b600160005460ff16600481111561095457fe5b1480156109675750600b54600a54014210155b156109745761093c610d34565b600260005460ff16600481111561098757fe5b14801561099d5750600b54600202600a54014210155b156109aa576109aa610e1f565b600160005460ff1660048111156109bd57fe5b1480156109f0575060015433600160a060020a03908116911614806109f0575060025433600160a060020a039081169116145b80610a415750600260005460ff166004811115610a0957fe5b148015610a41575060015433600160a060020a0390811691161480610a41575060005433600160a060020a0390811661010090920416145b1515610a4c57600080fd5b60005433600160a060020a03908116610100909204161415610a7057610a70610ec6565b60025433600160a060020a0390811691161415610a8f57610a8f610f70565b80600d600e54815481101515610aa157fe5b906000526020600020019080519060200190610abe929190611067565b5050600e80546001019055565b60065460055460029091020190565b6000546101009004600160a060020a031690565b600354600160a060020a031690565b6060600d82815481101515610b0e57fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610b9c5780601f10610b7157610100808354040283529160200191610b9c565b820191906000526020600020905b815481529060010190602001808311610b7f57829003601f168201915b50505050509050919050565b600e5490565b600080546004919060ff19166001836104a9565b60008060005460ff166004811115610bd657fe5b14610be057600080fd5b60006008541115610c2957600154600854604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610c27573d6000803e3d6000fd5b505b60006007541115610c7257600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610c70573d6000803e3d6000fd5b505b60006009541115610cbe5760008054600954604051610100909204600160a060020a0316926108fc8215029290818181858888f19350505050158015610cbc573d6000803e3d6000fd5b505b6040805190810160405280601981526020017f436f6e7472616374207761736e27742061637469766174656400000000000000815250600d600e54815481101515610d0557fe5b906000526020600020019080519060200190610d22929190611067565b50600e8054600101905561045d610bae565b60018060005460ff166004811115610d4857fe5b14610d5257600080fd5b600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610d8e573d6000803e3d6000fd5b5060008054600954600854604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610dd7573d6000803e3d6000fd5b506040805190810160405280601281526020017f44656c69766572792074696d6564206f75740000000000000000000000000000815250600d600e54815481101515610d0557fe5b60028060005460ff166004811115610e3357fe5b14610e3d57600080fd5b600254600654604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610e79573d6000803e3d6000fd5b5060008054600654600554604051600160a060020a03610100909404939093169360029092020180156108fc02929091818181858888f19350505050158015610cbc573d6000803e3d6000fd5b60028060005460ff166004811115610eda57fe5b14610ee457600080fd5b600154600654600554604051600160a060020a039093169260039092020180156108fc02916000818181858888f19350505050158015610f28573d6000803e3d6000fd5b506040805190810160405280601081526020017f5061636b6167652052657475726e656400000000000000000000000000000000815250600d600e54815481101515610d0557fe5b60018060005460ff166004811115610f8457fe5b14610f8e57600080fd5b60008054600654600554604051600160a060020a03610100909404939093169360029092020180156108fc02929091818181858888f19350505050158015610fda573d6000803e3d6000fd5b50600154600654600554604051600160a060020a039093169260029092020180156108fc02916000818181858888f1935050505015801561101f573d6000803e3d6000fd5b506040805190810160405280601181526020017f5061636b6167652064656c697665726564000000000000000000000000000000815250600d600e54815481101515610d0557fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106110a857805160ff19168380011785556110d5565b828001600101855582156110d5579182015b828111156110d55782518255916020019190600101906110ba565b506110e19291506110e5565b5090565b61046891905b808211156110e157600081556001016110eb565b6000601f8201831361111057600080fd5b813561112361111e826112e0565b6112b9565b9150808252602083016020830185838301111561113f57600080fd5b61114a83828461131e565b50505092915050565b600061115f8235610468565b9392505050565b60006020828403121561117857600080fd5b813567ffffffffffffffff81111561118f57600080fd5b61119b848285016110ff565b949350505050565b6000602082840312156111b557600080fd5b600061119b8484611153565b6111ca81611312565b82525050565b60006111db8261130e565b808452602084019350836020820285016111f485611308565b60005b8481101561122b57838303885261120f838351611237565b925061121a82611308565b6020989098019791506001016111f7565b50909695505050505050565b60006112428261130e565b80845261125681602086016020860161132a565b61125f8161135a565b9093016020019392505050565b6111ca81610468565b6020810161128382846111c1565b92915050565b6020808252810161115f81846111d0565b6020808252810161115f8184611237565b60208101611283828461126c565b60405181810167ffffffffffffffff811182821017156112d857600080fd5b604052919050565b600067ffffffffffffffff8211156112f757600080fd5b506020601f91909101601f19160190565b60200190565b5190565b600160a060020a031690565b82818337506000910152565b60005b8381101561134557818101518382015260200161132d565b83811115611354576000848401525b50505050565b601f01601f1916905600a265627a7a7230582002f4b79950bff67bdd18fba2ffd6f7448dee54b28ec0f19fd74da0f7d9b549e16c6578706572696d656e74616cf50037";

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

    public static final String FUNC_KILLME = "killMe";

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

    public RemoteCall<TransactionReceipt> killMe() {
        final Function function = new Function(
                FUNC_KILLME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
