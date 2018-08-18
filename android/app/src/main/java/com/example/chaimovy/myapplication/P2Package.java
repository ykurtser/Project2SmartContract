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
    private static final String BINARY = "60806040526040516101208062001843833981018060405262000026919081019062000335565b6000805460028054600160a060020a0319908116600160a060020a038b8116919091179092556001805482168c84161790556004805482163384161790556003805490911689831617905560058790556006869055600783905560088390556009839055600e83905561010060a860020a0319909116610100918b16919091021760ff1916905542600a908155600b839055600c82905560408051828152610160810190915290602082015b6060815260200190600190039081620000d25750508051620000fd91600d916020909101906200019d565b5060003411156200011c576200011c896401000000006200012b810204565b50505050505050505062000418565b600254600160a060020a0382811691161415620001505760078054340190556200019a565b600054600160a060020a038281166101009092041614156200017a5760098054340190556200019a565b600154600160a060020a03828116911614156200019a5760088054340190555b50565b828054828255906000526020600020908101928215620001ef579160200282015b82811115620001ef5782518051620001de91849160209091019062000201565b5091602001919060010190620001be565b50620001fd92915062000282565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200024457805160ff191683800117855562000274565b8280016001018555821562000274579182015b828111156200027457825182559160200191906001019062000257565b50620001fd929150620002ad565b620002aa91905b80821115620001fd576000620002a08282620002ca565b5060010162000289565b90565b620002aa91905b80821115620001fd5760008155600101620002b4565b50805460018160011615610100020316600290046000825580601f10620002f257506200019a565b601f0160209004906000526020600020908101906200019a9190620002ad565b60006200032082516200040c565b9392505050565b6000620003208251620002aa565b60008060008060008060008060006101208a8c0312156200035557600080fd5b6000620003638c8c62000312565b9950506020620003768c828d0162000312565b9850506040620003898c828d0162000312565b97505060606200039c8c828d0162000312565b9650506080620003af8c828d0162000312565b95505060a0620003c28c828d0162000327565b94505060c0620003d58c828d0162000327565b93505060e0620003e88c828d0162000327565b925050610100620003fc8c828d0162000327565b9150509295985092959850929598565b600160a060020a031690565b61141b80620004286000396000f3006080604052600436106101485763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663155e8b4381146101b25780631865c57d146101dd5780631fda9de4146101f2578063260519b9146102145780632c25eabd14610234578063305232d0146102495780633300a6cd1461025e5780633c77b93c146102735780633e91a175146102885780634c38d877146102495780634fd6137c1461029d5780635c63ed69146102b2578063603daf9a146102c7578063694a2e20146102dc578063a4972638146102fe578063b1b34a2014610313578063b603cd8014610328578063c2b7b8681461033d578063d0498dd31461035d578063d47cffcb1461037d578063d997ccb314610392578063dbd0e1b61461039a578063f0a44b7a146103af578063f4aec71e146103c4578063ff182cb6146103f1575b61015133610406565b610159610473565b60075410158015610173575061016d61047e565b60095410155b80156101885750610182610473565b60085410155b80156101a357506000805460ff1660048111156101a157fe5b145b156101b0576101b0610487565b005b3480156101be57600080fd5b506101c761050b565b6040516101d49190611328565b60405180910390f35b3480156101e957600080fd5b506101c7610511565b3480156101fe57600080fd5b50610207610528565b6040516101d491906112f2565b34801561022057600080fd5b506101b061022f3660046111e3565b610537565b34801561024057600080fd5b506101c7610649565b34801561025557600080fd5b506101c7610473565b34801561026a57600080fd5b506101c761064f565b34801561027f57600080fd5b506101c7610655565b34801561029457600080fd5b506101c761065b565b3480156102a957600080fd5b506101b0610661565b3480156102be57600080fd5b506101c761047e565b3480156102d357600080fd5b506102076106a2565b3480156102e857600080fd5b506102f16106b1565b6040516101d49190611306565b34801561030a57600080fd5b506101c7610789565b34801561031f57600080fd5b506101c761078f565b34801561033457600080fd5b506101b0610795565b34801561034957600080fd5b506101b0610358366004611220565b6107c1565b34801561036957600080fd5b506101b06103783660046111e3565b61091d565b34801561038957600080fd5b506101c7610ade565b6101b0610aed565b3480156103a657600080fd5b50610207610b57565b3480156103bb57600080fd5b50610207610b6b565b3480156103d057600080fd5b506103e46103df366004611220565b610b7a565b6040516101d49190611317565b3480156103fd57600080fd5b506101c7610c25565b600254600160a060020a0382811691161415610429576007805434019055610470565b600054600160a060020a03828116610100909204161415610451576009805434019055610470565b600154600160a060020a03828116911614156104705760088054340190555b50565b600654600554015b90565b60065460020290565b60005460ff16600481111561049857fe5b60010160048111156104a657fe5b6000805460ff191660018360048111156104bc57fe5b02179055506000547f38526e5054fb54946813ae7aae6f3447cd6739b6cfd8ea481d64c4a61d8767939060ff1660048111156104f457fe5b6040516105019190611328565b60405180910390a1565b600c5490565b6000805460ff16600481111561052357fe5b905090565b600154600160a060020a031690565b60018060005460ff16600481111561054b57fe5b1461055557600080fd5b60025433600160a060020a0390811691161461057057600080fd5b81600d600e5481548110151561058257fe5b90600052602060002001908051906020019061059f9291906110e4565b50600e805460010190556105b1610487565b600254600554604051600160a060020a039092169181156108fc0291906000818181858888f193505050501580156105ed573d6000803e3d6000fd5b5060055460078054919091039055600154600654604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610637573d6000803e3d6000fd5b50506006546008805491909103905550565b60065490565b60095490565b60055490565b600b5490565b60028060005460ff16600481111561067557fe5b1461067f57600080fd5b60015433600160a060020a0390811691161461069a57600080fd5b610470610487565b600254600160a060020a031690565b6060600d805480602002602001604051908101604052809291908181526020016000905b828210156107805760008481526020908190208301805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561076c5780601f106107415761010080835404028352916020019161076c565b820191906000526020600020905b81548152906001019060200180831161074f57829003601f168201915b5050505050815260200190600101906106d5565b50505050905090565b60085490565b60075490565b60048060005460ff1660048111156107a957fe5b146107b357600080fd5b600454600160a060020a0316ff5b60038060005460ff1660048111156107d557fe5b146107df57600080fd5b60035433600160a060020a039081169116146107fa57600080fd5b6000546101009004600160a060020a03166108fc606484610819610ade565b0281151561082357fe5b049081150290604051600060405180830381858888f1935050505015801561084f573d6000803e3d6000fd5b50600154600160a060020a03166108fc606484810361086c610ade565b0281151561087657fe5b049081150290604051600060405180830381858888f193505050501580156108a2573d6000803e3d6000fd5b506040805190810160405280601981526020017f436f6e7472616374207761736e27742061637469766174656400000000000000815250600d600e548154811015156108ea57fe5b9060005260206000200190805190602001906109079291906110e4565b50600e80546001019055610919610c2b565b5050565b6000805460ff16600481111561092f57fe5b1480156109425750600c54600a54014210155b156109545761094f610c3f565b6109bd565b600160005460ff16600481111561096757fe5b14801561097a5750600b54600a54014210155b156109875761094f610db1565b600260005460ff16600481111561099a57fe5b1480156109b05750600b54600202600a54014210155b156109bd576109bd610e9c565b600160005460ff1660048111156109d057fe5b148015610a03575060015433600160a060020a0390811691161480610a03575060025433600160a060020a039081169116145b80610a545750600260005460ff166004811115610a1c57fe5b148015610a54575060015433600160a060020a0390811691161480610a54575060005433600160a060020a0390811661010090920416145b1515610a5f57600080fd5b60005433600160a060020a03908116610100909204161415610a8357610a83610f43565b60025433600160a060020a0390811691161415610aa257610aa2610fed565b80600d600e54815481101515610ab457fe5b906000526020600020019080519060200190610ad19291906110e4565b5050600e80546001019055565b60065460055460029091020190565b610af633610406565b610afe610473565b60075410158015610b185750610b1261047e565b60095410155b8015610b2d5750610b27610473565b60085410155b8015610b4857506000805460ff166004811115610b4657fe5b145b15610b5557610b55610487565b565b6000546101009004600160a060020a031690565b600354600160a060020a031690565b6060600d82815481101515610b8b57fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610c195780601f10610bee57610100808354040283529160200191610c19565b820191906000526020600020905b815481529060010190602001808311610bfc57829003601f168201915b50505050509050919050565b600e5490565b600080546004919060ff19166001836104bc565b60008060005460ff166004811115610c5357fe5b14610c5d57600080fd5b60006008541115610ca657600154600854604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610ca4573d6000803e3d6000fd5b505b60006007541115610cef57600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610ced573d6000803e3d6000fd5b505b60006009541115610d3b5760008054600954604051610100909204600160a060020a0316926108fc8215029290818181858888f19350505050158015610d39573d6000803e3d6000fd5b505b6040805190810160405280601981526020017f436f6e7472616374207761736e27742061637469766174656400000000000000815250600d600e54815481101515610d8257fe5b906000526020600020019080519060200190610d9f9291906110e4565b50600e80546001019055610470610c2b565b60018060005460ff166004811115610dc557fe5b14610dcf57600080fd5b600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610e0b573d6000803e3d6000fd5b5060008054600954600854604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610e54573d6000803e3d6000fd5b506040805190810160405280601281526020017f44656c69766572792074696d6564206f75740000000000000000000000000000815250600d600e54815481101515610d8257fe5b60028060005460ff166004811115610eb057fe5b14610eba57600080fd5b600254600654604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610ef6573d6000803e3d6000fd5b5060008054600654600554604051600160a060020a03610100909404939093169360029092020180156108fc02929091818181858888f19350505050158015610d39573d6000803e3d6000fd5b60028060005460ff166004811115610f5757fe5b14610f6157600080fd5b600154600654600554604051600160a060020a039093169260039092020180156108fc02916000818181858888f19350505050158015610fa5573d6000803e3d6000fd5b506040805190810160405280601081526020017f5061636b6167652052657475726e656400000000000000000000000000000000815250600d600e54815481101515610d8257fe5b60018060005460ff16600481111561100157fe5b1461100b57600080fd5b60008054600654600554604051600160a060020a03610100909404939093169360029092020180156108fc02929091818181858888f19350505050158015611057573d6000803e3d6000fd5b50600154600654600554604051600160a060020a039093169260029092020180156108fc02916000818181858888f1935050505015801561109c573d6000803e3d6000fd5b506040805190810160405280601181526020017f5061636b6167652064656c697665726564000000000000000000000000000000815250600d600e54815481101515610d8257fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061112557805160ff1916838001178555611152565b82800160010185558215611152579182015b82811115611152578251825591602001919060010190611137565b5061115e929150611162565b5090565b61047b91905b8082111561115e5760008155600101611168565b6000601f8201831361118d57600080fd5b81356111a061119b8261135d565b611336565b915080825260208301602083018583830111156111bc57600080fd5b6111c783828461139b565b50505092915050565b60006111dc823561047b565b9392505050565b6000602082840312156111f557600080fd5b813567ffffffffffffffff81111561120c57600080fd5b6112188482850161117c565b949350505050565b60006020828403121561123257600080fd5b600061121884846111d0565b6112478161138f565b82525050565b60006112588261138b565b8084526020840193508360208202850161127185611385565b60005b848110156112a857838303885261128c8383516112b4565b925061129782611385565b602098909801979150600101611274565b50909695505050505050565b60006112bf8261138b565b8084526112d38160208601602086016113a7565b6112dc816113d7565b9093016020019392505050565b6112478161047b565b60208101611300828461123e565b92915050565b602080825281016111dc818461124d565b602080825281016111dc81846112b4565b6020810161130082846112e9565b60405181810167ffffffffffffffff8111828210171561135557600080fd5b604052919050565b600067ffffffffffffffff82111561137457600080fd5b506020601f91909101601f19160190565b60200190565b5190565b600160a060020a031690565b82818337506000910152565b60005b838110156113c25781810151838201526020016113aa565b838111156113d1576000848401525b50505050565b601f01601f1916905600a265627a7a7230582015d8b7a3689ea1460a332febbe6c0908e8c5331d344af8bc971c1439fb1baba66c6578706572696d656e74616cf50037";

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

    public static final String FUNC_PAYME = "payMe";

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

    public RemoteCall<TransactionReceipt> payMe(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAYME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
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
