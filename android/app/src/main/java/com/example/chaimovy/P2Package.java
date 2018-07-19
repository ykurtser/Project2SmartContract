package com.example.chaimovy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
    private static final String BINARY = "0x608060405260405161012080620022df83398101806040526200002691908101906200055c565b87600060016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555085600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555086600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555084600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555083600581905550826006819055506000600781905550600060088190555060006009819055506000600e8190555060008060006101000a81548160ff02191690836003811115620001b857fe5b021790555042600a8190555081600b8190555080600c81905550600a6040519080825280602002602001820160405280156200020957816020015b6060815260200190600190039081620001f35790505b50600d9080519060200190620002219291906200039d565b5060003411156200024757620002468962000256640100000000026401000000009004565b5b5050505050505050506200065f565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415620002c357346007600082825401925050819055506200039a565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141562000330573460096000828254019250508190555062000399565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156200039857346008600082825401925050819055505b5b5b50565b828054828255906000526020600020908101928215620003f1579160200282015b82811115620003f0578251829080519060200190620003df92919062000404565b5091602001919060010190620003be565b5b5090506200040091906200048b565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200044757805160ff191683800117855562000478565b8280016001018555821562000478579182015b82811115620004775782518255916020019190600101906200045a565b5b509050620004879190620004bc565b5090565b620004b991905b80821115620004b55760008181620004ab9190620004e4565b5060010162000492565b5090565b90565b620004e191905b80821115620004dd576000816000905550600101620004c3565b5090565b90565b50805460018160011615610100020316600290046000825580601f106200050c57506200052d565b601f0160209004906000526020600020908101906200052c9190620004bc565b5b50565b60006200053e825162000635565b905092915050565b600062000554825162000655565b905092915050565b60008060008060008060008060006101208a8c0312156200057c57600080fd5b60006200058c8c828d0162000530565b99505060206200059f8c828d0162000530565b9850506040620005b28c828d0162000530565b9750506060620005c58c828d0162000530565b9650506080620005d88c828d0162000530565b95505060a0620005eb8c828d0162000546565b94505060c0620005fe8c828d0162000546565b93505060e0620006118c828d0162000546565b925050610100620006258c828d0162000546565b9150509295985092959850929598565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b611c70806200066f6000396000f300608060405260043610610128576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063155e8b43146101a85780631865c57d146101d35780631fda9de4146101fe578063260519b9146102295780632c25eabd14610252578063305232d01461027d5780633300a6cd146102a85780633c77b93c146102d35780633e91a175146102fe5780634c38d877146103295780634fd6137c146103545780635c63ed691461036b578063603daf9a14610396578063694a2e20146103c1578063a4972638146103ec578063b1b34a2014610417578063c2b7b86814610442578063d0498dd31461046b578063d47cffcb14610494578063dbd0e1b6146104bf578063f0a44b7a146104ea578063f4aec71e14610515575b61013133610552565b610139610694565b60075410158015610153575061014d6106a2565b60095410155b801561016857506101626106af565b60085410155b801561019857506000600381111561017c57fe5b6000809054906101000a900460ff16600381111561019657fe5b145b156101a6576101a56106bd565b5b005b3480156101b457600080fd5b506101bd610758565b6040516101ca9190611b00565b60405180910390f35b3480156101df57600080fd5b506101e8610762565b6040516101f59190611b00565b60405180910390f35b34801561020a57600080fd5b50610213610783565b6040516102209190611aa1565b60405180910390f35b34801561023557600080fd5b50610250600480360361024b919081019061193f565b6107ad565b005b34801561025e57600080fd5b50610267610985565b6040516102749190611b00565b60405180910390f35b34801561028957600080fd5b506102926106af565b60405161029f9190611b00565b60405180910390f35b3480156102b457600080fd5b506102bd61098f565b6040516102ca9190611b00565b60405180910390f35b3480156102df57600080fd5b506102e8610999565b6040516102f59190611b00565b60405180910390f35b34801561030a57600080fd5b506103136109a3565b6040516103209190611b00565b60405180910390f35b34801561033557600080fd5b5061033e610694565b60405161034b9190611b00565b60405180910390f35b34801561036057600080fd5b506103696109ad565b005b34801561037757600080fd5b506103806106a2565b60405161038d9190611b00565b60405180910390f35b3480156103a257600080fd5b506103ab610a48565b6040516103b89190611aa1565b60405180910390f35b3480156103cd57600080fd5b506103d6610a72565b6040516103e39190611abc565b60405180910390f35b3480156103f857600080fd5b50610401610b5b565b60405161040e9190611b00565b60405180910390f35b34801561042357600080fd5b5061042c610b65565b6040516104399190611b00565b60405180910390f35b34801561044e57600080fd5b5061046960048036036104649190810190611980565b610b6f565b005b34801561047757600080fd5b50610492600480360361048d919081019061193f565b610d39565b005b3480156104a057600080fd5b506104a96110e5565b6040516104b69190611b00565b60405180910390f35b3480156104cb57600080fd5b506104d46110f6565b6040516104e19190611aa1565b60405180910390f35b3480156104f657600080fd5b506104ff61111f565b60405161050c9190611aa1565b60405180910390f35b34801561052157600080fd5b5061053c60048036036105379190810190611980565b611149565b6040516105499190611ade565b60405180910390f35b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156105bd5734600760008282540192505081905550610691565b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156106285734600960008282540192505081905550610690565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141561068f57346008600082825401925050819055505b5b5b50565b600060065460055401905090565b6000600654600202905090565b600060065460055401905090565b60016000809054906101000a900460ff1660038111156106d957fe5b0160038111156106e557fe5b6000806101000a81548160ff0219169083600381111561070157fe5b02179055507f38526e5054fb54946813ae7aae6f3447cd6739b6cfd8ea481d64c4a61d8767936000809054906101000a900460ff16600381111561074157fe5b60405161074e9190611b00565b60405180910390a1565b6000600c54905090565b60008060009054906101000a900460ff16600381111561077e57fe5b905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60018060038111156107bb57fe5b6000809054906101000a900460ff1660038111156107d557fe5b1415156107e157600080fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561083d57600080fd5b81600d600e5481548110151561084f57fe5b90600052602060002001908051906020019061086c929190611830565b50600e600081548092919060010191905055506108876106bd565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6005549081150290604051600060405180830381858888f193505050501580156108f1573d6000803e3d6000fd5b50600554600760008282540392505081905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6006549081150290604051600060405180830381858888f1935050505015801561096e573d6000803e3d6000fd5b506006546008600082825403925050819055505050565b6000600654905090565b6000600954905090565b6000600554905090565b6000600b54905090565b60028060038111156109bb57fe5b6000809054906101000a900460ff1660038111156109d557fe5b1415156109e157600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610a3d57600080fd5b610a456106bd565b50565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600d805480602002602001604051908101604052809291908181526020016000905b82821015610b52578382906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b3e5780601f10610b1357610100808354040283529160200191610b3e565b820191906000526020600020905b815481529060010190602001808311610b2157829003601f168201915b505050505081526020019060010190610a96565b50505050905090565b6000600854905090565b6000600754905090565b6003806003811115610b7d57fe5b6000809054906101000a900460ff166003811115610b9757fe5b141515610ba357600080fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bff57600080fd5b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc606484610c466110e5565b02811515610c5057fe5b049081150290604051600060405180830381858888f19350505050158015610c7c573d6000803e3d6000fd5b50600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc606484606403610cc76110e5565b02811515610cd157fe5b049081150290604051600060405180830381858888f19350505050158015610cfd573d6000803e3d6000fd5b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60006003811115610d4657fe5b6000809054906101000a900460ff166003811115610d6057fe5b148015610d735750600c54600a54014210155b15610d8557610d80611204565b610e1e565b60016003811115610d9257fe5b6000809054906101000a900460ff166003811115610dac57fe5b148015610dbf5750600b54600a54014210155b15610dd157610dcc6113d8565b610e1d565b60026003811115610dde57fe5b6000809054906101000a900460ff166003811115610df857fe5b148015610e0e5750600b54600202600a54014210155b15610e1c57610e1b611521565b5b5b5b60016003811115610e2b57fe5b6000809054906101000a900460ff166003811115610e4557fe5b148015610ef75750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480610ef65750600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b5b80610fd7575060026003811115610f0a57fe5b6000809054906101000a900460ff166003811115610f2457fe5b148015610fd65750600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480610fd55750600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b5b5b1515610fe257600080fd5b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415611041576110406115ff565b5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156110a05761109f6116e0565b5b80600d600e548154811015156110b257fe5b9060005260206000200190805190602001906110cf929190611830565b50600e6000815480929190600101919050555050565b600060065460020260055401905090565b60008060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600d8281548110151561115a57fe5b906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111f85780601f106111cd576101008083540402835291602001916111f8565b820191906000526020600020905b8154815290600101906020018083116111db57829003601f168201915b50505050509050919050565b600080600381111561121257fe5b6000809054906101000a900460ff16600381111561122c57fe5b14151561123857600080fd5b600060085411156112af57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6008549081150290604051600060405180830381858888f193505050501580156112ad573d6000803e3d6000fd5b505b6000600754111561132657600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6007549081150290604051600060405180830381858888f19350505050158015611324573d6000803e3d6000fd5b505b6000600954111561139d57600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6009549081150290604051600060405180830381858888f1935050505015801561139b573d6000803e3d6000fd5b505b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60018060038111156113e657fe5b6000809054906101000a900460ff16600381111561140057fe5b14151561140c57600080fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6007549081150290604051600060405180830381858888f19350505050158015611476573d6000803e3d6000fd5b50600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600954600854019081150290604051600060405180830381858888f193505050501580156114e5573d6000803e3d6000fd5b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600280600381111561152f57fe5b6000809054906101000a900460ff16600381111561154957fe5b14151561155557600080fd5b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600954600854019081150290604051600060405180830381858888f193505050501580156115c3573d6000803e3d6000fd5b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600280600381111561160d57fe5b6000809054906101000a900460ff16600381111561162757fe5b14151561163357600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600654600302600554019081150290604051600060405180830381858888f193505050501580156116a4573d6000803e3d6000fd5b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60018060038111156116ee57fe5b6000809054906101000a900460ff16600381111561170857fe5b14151561171457600080fd5b600060019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600654600554019081150290604051600060405180830381858888f19350505050158015611782573d6000803e3d6000fd5b50600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600654600202600554019081150290604051600060405180830381858888f193505050501580156117f4573d6000803e3d6000fd5b50600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061187157805160ff191683800117855561189f565b8280016001018555821561189f579182015b8281111561189e578251825591602001919060010190611883565b5b5090506118ac91906118b0565b5090565b6118d291905b808211156118ce5760008160009055506001016118b6565b5090565b90565b600082601f83011215156118e857600080fd5b81356118fb6118f682611b48565b611b1b565b9150808252602083016020830185838301111561191757600080fd5b611922838284611be3565b50505092915050565b60006119378235611bd9565b905092915050565b60006020828403121561195157600080fd5b600082013567ffffffffffffffff81111561196b57600080fd5b611977848285016118d5565b91505092915050565b60006020828403121561199257600080fd5b60006119a08482850161192b565b91505092915050565b6119b281611baf565b82525050565b60006119c382611b81565b808452602084019350836020820285016119dc85611b74565b60005b84811015611a155783830388526119f7838351611a5c565b9250611a0282611ba2565b91506020880197506001810190506119df565b508196508694505050505092915050565b6000611a3182611b97565b808452611a45816020860160208601611bf2565b611a4e81611c25565b602085010191505092915050565b6000611a6782611b8c565b808452611a7b816020860160208601611bf2565b611a8481611c25565b602085010191505092915050565b611a9b81611bcf565b82525050565b6000602082019050611ab660008301846119a9565b92915050565b60006020820190508181036000830152611ad681846119b8565b905092915050565b60006020820190508181036000830152611af88184611a26565b905092915050565b6000602082019050611b156000830184611a92565b92915050565b6000604051905081810181811067ffffffffffffffff82111715611b3e57600080fd5b8060405250919050565b600067ffffffffffffffff821115611b5f57600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015611c10578082015181840152602081019050611bf5565b83811115611c1f576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820e53a114f374acb756c1b96fd35d148b89b8317c0855535b94ee4596bddbf26b76c6578706572696d656e74616cf50037";

    public static final String FUNC_SIGNPACKAGE = "signPackage";

    public static final String FUNC_RETURNPACKAGE = "returnPackage";

    public static final String FUNC_OPENDISPUTE = "openDispute";

    public static final String FUNC_RESOLVEDISPUTE = "resolveDispute";

    public static final String FUNC_GETSTATE = "getState";

    public static final String FUNC_GETBUYER = "getBuyer";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final String FUNC_GETCARRIER = "getCarrier";

    public static final String FUNC_GETDISPUTERESOLVER = "getDisputeResolver";

    public static final String FUNC_GETAMMOUNTBUYER = "getAmmountBuyer";

    public static final String FUNC_GETAMMOUNTSELLER = "getAmmountSeller";

    public static final String FUNC_GETAMMOUNTCARRIER = "getAmmountCarrier";

    public static final String FUNC_GETSTAKESINTO = "getStakesInTo";

    public static final String FUNC_GETARRIVALTO = "getarrivalTO";

    public static final String FUNC_GETMERCHVAL = "getMerchVal";

    public static final String FUNC_GETSHIPPINGFEE = "getShippingFee";

    public static final String FUNC_GETBUYERSTAKE = "getBuyerStake";

    public static final String FUNC_GETSELLERSTAKE = "getSellerStake";

    public static final String FUNC_GETCARRIERSTAKE = "getCarrierStake";

    public static final String FUNC_GETTRAJECTORY = "getTrajectory";

    public static final String FUNC_GETTRAJECTORYI = "getTrajectoryI";

    public static final String FUNC_GETDISPUTEFULLAMMOUNT = "getDisputeFullAmmount";

    public static final Event UINTFIELDASSIGNED_EVENT = new Event("uintFieldAssigned", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHANGEDSTATE_EVENT = new Event("changedState", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    protected P2Package(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected P2Package(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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


    public RemoteCall<TransactionReceipt> signPackage(String location) {
        final Function function = new Function(
                FUNC_SIGNPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(location)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> returnPackage(String reason) {
        final Function function = new Function(
                FUNC_RETURNPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(reason)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> openDispute() {
        final Function function = new Function(
                FUNC_OPENDISPUTE, 
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

    public RemoteCall<BigInteger> getState() {
        final Function function = new Function(FUNC_GETSTATE, 
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

    public RemoteCall<String> getSeller() {
        final Function function = new Function(FUNC_GETSELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getCarrier() {
        final Function function = new Function(FUNC_GETCARRIER, 
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

    public RemoteCall<BigInteger> getAmmountBuyer() {
        final Function function = new Function(FUNC_GETAMMOUNTBUYER, 
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

    public RemoteCall<BigInteger> getAmmountCarrier() {
        final Function function = new Function(FUNC_GETAMMOUNTCARRIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getStakesInTo() {
        final Function function = new Function(FUNC_GETSTAKESINTO, 
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

    public RemoteCall<BigInteger> getMerchVal() {
        final Function function = new Function(FUNC_GETMERCHVAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getShippingFee() {
        final Function function = new Function(FUNC_GETSHIPPINGFEE, 
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

    public RemoteCall<BigInteger> getSellerStake() {
        final Function function = new Function(FUNC_GETSELLERSTAKE, 
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
/*
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
*/
    public RemoteCall<String> getTrajectoryI(BigInteger i) {
        final Function function = new Function(FUNC_GETTRAJECTORYI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getDisputeFullAmmount() {
        final Function function = new Function(FUNC_GETDISPUTEFULLAMMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static P2Package load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Package(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static P2Package load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new P2Package(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
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
