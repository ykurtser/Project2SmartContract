package com.example.chaimovy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class PackageManager extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a033316600160a060020a03199091161790556123578061003d6000396000f3006080604052600436106100825763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166364b347fa8114610084578063715018a6146100c357806388818165146100d8578063893d20e8146101095780638da5cb5b1461011e5780638f61210214610133578063f2fde38b14610148575b005b34801561009057600080fd5b50610082600160a060020a036004358116906024358116906044358116906064351660843560a43560c43560e435610169565b3480156100cf57600080fd5b50610082610235565b3480156100e457600080fd5b506100ed6102a5565b60408051600160a060020a039092168252519081900360200190f35b34801561011557600080fd5b506100ed610304565b34801561012a57600080fd5b506100ed610313565b34801561013f57600080fd5b50610082610322565b34801561015457600080fd5b50610082600160a060020a036004351661039e565b600033898989898989898961017c610442565b600160a060020a03998a1681529789166020890152958816604080890191909152948816606088015292909616608086015260a085015260c084019490945260e083019390935261010082015290519081900361012001906000f0801580156101e9573d6000803e3d6000fd5b5060408051600160a060020a038316815290519192507ffcf9a0c9dedbfcd1a047374855fc36baaf605bd4f4837802a0cc938ba1b5f302919081900360200190a1505050505050505050565b60005433600160a060020a0390811691161461025057600080fd5b60008054604051600160a060020a03909116917ff8df31144d9c2f0f6b59d69b8b98abd5459d07f2742c4df920b25aae33c6482091a26000805473ffffffffffffffffffffffffffffffffffffffff19169055565b6000805433600160a060020a039081169116146102c157600080fd5b60008054604051600160a060020a0391821692309092163180156108fc0292909190818181858888f19350505050158015610300573d6000803e3d6000fd5b5090565b600054600160a060020a031690565b600054600160a060020a031681565b60003361032d610452565b600160a060020a03909116815260405190819003602001906000f08015801561035a573d6000803e3d6000fd5b5060408051600160a060020a038316815290519192507ffcf9a0c9dedbfcd1a047374855fc36baaf605bd4f4837802a0cc938ba1b5f302919081900360200190a150565b60005433600160a060020a039081169116146103b957600080fd5b6103c2816103c5565b50565b600160a060020a03811615156103da57600080fd5b60008054604051600160a060020a03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a36000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60405161154d8061046383390190565b60405161097c806119b08339019056006080604052604051610120806200154d833981018060405262000026919081019062000335565b6000805460028054600160a060020a0319908116600160a060020a038b8116919091179092556001805482168c84161790556004805482163384161790556003805490911689831617905560058790556006869055600783905560088390556009839055600e83905561010060a860020a0319909116610100918b16919091021760ff1916905542600a908155600b839055600c82905560408051828152610160810190915290602082015b6060815260200190600190039081620000d25750508051620000fd91600d916020909101906200019d565b5060003411156200011c576200011c896401000000006200012b810204565b50505050505050505062000418565b600254600160a060020a0382811691161415620001505760078054340190556200019a565b600054600160a060020a038281166101009092041614156200017a5760098054340190556200019a565b600154600160a060020a03828116911614156200019a5760088054340190555b50565b828054828255906000526020600020908101928215620001ef579160200282015b82811115620001ef5782518051620001de91849160209091019062000201565b5091602001919060010190620001be565b50620001fd92915062000282565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200024457805160ff191683800117855562000274565b8280016001018555821562000274579182015b828111156200027457825182559160200191906001019062000257565b50620001fd929150620002ad565b620002aa91905b80821115620001fd576000620002a08282620002ca565b5060010162000289565b90565b620002aa91905b80821115620001fd5760008155600101620002b4565b50805460018160011615610100020316600290046000825580601f10620002f257506200019a565b601f0160209004906000526020600020908101906200019a9190620002ad565b60006200032082516200040c565b9392505050565b6000620003208251620002aa565b60008060008060008060008060006101208a8c0312156200035557600080fd5b6000620003638c8c62000312565b9950506020620003768c828d0162000312565b9850506040620003898c828d0162000312565b97505060606200039c8c828d0162000312565b9650506080620003af8c828d0162000312565b95505060a0620003c28c828d0162000327565b94505060c0620003d58c828d0162000327565b93505060e0620003e88c828d0162000327565b925050610100620003fc8c828d0162000327565b9150509295985092959850929598565b600160a060020a031690565b61112580620004286000396000f3006080604052600436106101275763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663155e8b4381146101915780631865c57d146101bc5780631fda9de4146101d1578063260519b9146101f35780632c25eabd14610213578063305232d0146102285780633300a6cd1461023d5780633c77b93c146102525780633e91a175146102675780634c38d877146102285780634fd6137c1461027c5780635c63ed6914610291578063603daf9a146102a6578063694a2e20146102bb578063a4972638146102dd578063b1b34a20146102f2578063c2b7b86814610307578063d0498dd314610327578063d47cffcb14610347578063dbd0e1b61461035c578063f0a44b7a14610371578063f4aec71e14610386575b610130336103b3565b610138610420565b60075410158015610152575061014c61042b565b60095410155b80156101675750610161610420565b60085410155b801561018257506000805460ff16600381111561018057fe5b145b1561018f5761018f610434565b005b34801561019d57600080fd5b506101a66104b8565b6040516101b39190611032565b60405180910390f35b3480156101c857600080fd5b506101a66104be565b3480156101dd57600080fd5b506101e66104d5565b6040516101b39190610ffc565b3480156101ff57600080fd5b5061018f61020e366004610eed565b6104e4565b34801561021f57600080fd5b506101a66105f6565b34801561023457600080fd5b506101a6610420565b34801561024957600080fd5b506101a66105fc565b34801561025e57600080fd5b506101a6610602565b34801561027357600080fd5b506101a6610608565b34801561028857600080fd5b5061018f61060e565b34801561029d57600080fd5b506101a661042b565b3480156102b257600080fd5b506101e661064f565b3480156102c757600080fd5b506102d061065e565b6040516101b39190611010565b3480156102e957600080fd5b506101a6610736565b3480156102fe57600080fd5b506101a661073c565b34801561031357600080fd5b5061018f610322366004610f2a565b610742565b34801561033357600080fd5b5061018f610342366004610eed565b610832565b34801561035357600080fd5b506101a66109f3565b34801561036857600080fd5b506101e6610a02565b34801561037d57600080fd5b506101e6610a16565b34801561039257600080fd5b506103a66103a1366004610f2a565b610a25565b6040516101b39190611021565b600254600160a060020a03828116911614156103d657600780543401905561041d565b600054600160a060020a038281166101009092041614156103fe57600980543401905561041d565b600154600160a060020a038281169116141561041d5760088054340190555b50565b600654600554015b90565b60065460020290565b60005460ff16600381111561044557fe5b600101600381111561045357fe5b6000805460ff1916600183600381111561046957fe5b02179055506000547f38526e5054fb54946813ae7aae6f3447cd6739b6cfd8ea481d64c4a61d8767939060ff1660038111156104a157fe5b6040516104ae9190611032565b60405180910390a1565b600c5490565b6000805460ff1660038111156104d057fe5b905090565b600154600160a060020a031690565b60018060005460ff1660038111156104f857fe5b1461050257600080fd5b60025433600160a060020a0390811691161461051d57600080fd5b81600d600e5481548110151561052f57fe5b90600052602060002001908051906020019061054c929190610dee565b50600e8054600101905561055e610434565b600254600554604051600160a060020a039092169181156108fc0291906000818181858888f1935050505015801561059a573d6000803e3d6000fd5b5060055460078054919091039055600154600654604051600160a060020a039092169181156108fc0291906000818181858888f193505050501580156105e4573d6000803e3d6000fd5b50506006546008805491909103905550565b60065490565b60095490565b60055490565b600b5490565b60028060005460ff16600381111561062257fe5b1461062c57600080fd5b60015433600160a060020a0390811691161461064757600080fd5b61041d610434565b600254600160a060020a031690565b6060600d805480602002602001604051908101604052809291908181526020016000905b8282101561072d5760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156107195780601f106106ee57610100808354040283529160200191610719565b820191906000526020600020905b8154815290600101906020018083116106fc57829003601f168201915b505050505081526020019060010190610682565b50505050905090565b60085490565b60075490565b60038060005460ff16600381111561075657fe5b1461076057600080fd5b60035433600160a060020a0390811691161461077b57600080fd5b6000546101009004600160a060020a03166108fc60648461079a6109f3565b028115156107a457fe5b049081150290604051600060405180830381858888f193505050501580156107d0573d6000803e3d6000fd5b50600154600160a060020a03166108fc60648481036107ed6109f3565b028115156107f757fe5b049081150290604051600060405180830381858888f19350505050158015610823573d6000803e3d6000fd5b50600454600160a060020a0316ff5b6000805460ff16600381111561084457fe5b1480156108575750600c54600a54014210155b1561086957610864610ad0565b6108d2565b600160005460ff16600381111561087c57fe5b14801561088f5750600b54600a54014210155b1561089c57610864610bd8565b600260005460ff1660038111156108af57fe5b1480156108c55750600b54600202600a54014210155b156108d2576108d2610c7b565b600160005460ff1660038111156108e557fe5b148015610918575060015433600160a060020a0390811691161480610918575060025433600160a060020a039081169116145b806109695750600260005460ff16600381111561093157fe5b148015610969575060015433600160a060020a0390811691161480610969575060005433600160a060020a0390811661010090920416145b151561097457600080fd5b60005433600160a060020a0390811661010090920416141561099857610998610ce1565b60025433600160a060020a03908116911614156109b7576109b7610d43565b80600d600e548154811015156109c957fe5b9060005260206000200190805190602001906109e6929190610dee565b5050600e80546001019055565b60065460055460029091020190565b6000546101009004600160a060020a031690565b600354600160a060020a031690565b6060600d82815481101515610a3657fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610ac45780601f10610a9957610100808354040283529160200191610ac4565b820191906000526020600020905b815481529060010190602001808311610aa757829003601f168201915b50505050509050919050565b60008060005460ff166003811115610ae457fe5b14610aee57600080fd5b60006008541115610b3757600154600854604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610b35573d6000803e3d6000fd5b505b60006007541115610b8057600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610b7e573d6000803e3d6000fd5b505b60006009541115610bca5760008054600954604051610100909204600160a060020a0316926108fc8215029290818181858888f19350505050158015610823573d6000803e3d6000fd5b600454600160a060020a0316ff5b60018060005460ff166003811115610bec57fe5b14610bf657600080fd5b600254600754604051600160a060020a039092169181156108fc0291906000818181858888f19350505050158015610c32573d6000803e3d6000fd5b5060008054600954600854604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610823573d6000803e3d6000fd5b60028060005460ff166003811115610c8f57fe5b14610c9957600080fd5b60008054600954600854604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610823573d6000803e3d6000fd5b60028060005460ff166003811115610cf557fe5b14610cff57600080fd5b600154600654600554604051600160a060020a039093169260039092020180156108fc02916000818181858888f19350505050158015610823573d6000803e3d6000fd5b60018060005460ff166003811115610d5757fe5b14610d6157600080fd5b60008054600654600554604051600160a060020a036101009094049390931693910180156108fc02929091818181858888f19350505050158015610da9573d6000803e3d6000fd5b50600154600654600554604051600160a060020a039093169260029092020180156108fc02916000818181858888f19350505050158015610823573d6000803e3d6000fd5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e2f57805160ff1916838001178555610e5c565b82800160010185558215610e5c579182015b82811115610e5c578251825591602001919060010190610e41565b50610e68929150610e6c565b5090565b61042891905b80821115610e685760008155600101610e72565b6000601f82018313610e9757600080fd5b8135610eaa610ea582611067565b611040565b91508082526020830160208301858383011115610ec657600080fd5b610ed18382846110a5565b50505092915050565b6000610ee68235610428565b9392505050565b600060208284031215610eff57600080fd5b813567ffffffffffffffff811115610f1657600080fd5b610f2284828501610e86565b949350505050565b600060208284031215610f3c57600080fd5b6000610f228484610eda565b610f5181611099565b82525050565b6000610f6282611095565b80845260208401935083602082028501610f7b8561108f565b60005b84811015610fb2578383038852610f96838351610fbe565b9250610fa18261108f565b602098909801979150600101610f7e565b50909695505050505050565b6000610fc982611095565b808452610fdd8160208601602086016110b1565b610fe6816110e1565b9093016020019392505050565b610f5181610428565b6020810161100a8284610f48565b92915050565b60208082528101610ee68184610f57565b60208082528101610ee68184610fbe565b6020810161100a8284610ff3565b60405181810167ffffffffffffffff8111828210171561105f57600080fd5b604052919050565b600067ffffffffffffffff82111561107e57600080fd5b506020601f91909101601f19160190565b60200190565b5190565b600160a060020a031690565b82818337506000910152565b60005b838110156110cc5781810151838201526020016110b4565b838111156110db576000848401525b50505050565b601f01601f1916905600a265627a7a72305820666eb6a9b351d2c3068560f33a4cd8c661f503b6e634a2263bcdc515cc624a596c6578706572696d656e74616cf50037608060405234801561001057600080fd5b5060405160208061097c833981016040818152915160008054600160a060020a031916600160a060020a0383169081179091557f94771fdb000000000000000000000000000000000000000000000000000000008352600160048401526024830152915173__./AddressSet.sol:AddressSet___________916394771fdb916044808301926020929190829003018186803b1580156100af57600080fd5b505af41580156100c3573d6000803e3d6000fd5b505050506040513d60208110156100d957600080fd5b505050610891806100eb6000396000f3006080604052600436106100985763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663707401ee811461009a578063715018a6146101015780637e33ff4b14610116578063893d20e8146101375780638da5cb5b14610168578063afe3c6101461017d578063c179d703146101b2578063f2e2c4af146101d3578063f2fde38b146101f7575b005b3480156100a657600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610098958335600160a060020a03169536956044949193909101919081908401838280828437509497506102189650505050505050565b34801561010d57600080fd5b50610098610471565b34801561012257600080fd5b50610098600160a060020a03600435166104e1565b34801561014357600080fd5b5061014c6105d9565b60408051600160a060020a039092168252519081900360200190f35b34801561017457600080fd5b5061014c6105e8565b34801561018957600080fd5b5061019e600160a060020a03600435166105f7565b604080519115158252519081900360200190f35b3480156101be57600080fd5b50610098600160a060020a03600435166106a7565b3480156101df57600080fd5b50610098600160a060020a0360043516602435610770565b34801561020357600080fd5b50610098600160a060020a03600435166107c1565b604080517f7ef295c900000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0333166024820152905173__./AddressSet.sol:AddressSet___________91637ef295c9916044808301926020929190829003018186803b15801561029257600080fd5b505af41580156102a6573d6000803e3d6000fd5b505050506040513d60208110156102bc57600080fd5b505115156102c957600080fd5b7f5dca52be3de0dd469988fa9678424836d34c38c867f33b8b3634c9b3a4d027d93383836040518084600160a060020a0316600160a060020a0316815260200183600160a060020a0316600160a060020a0316815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561035a578181015183820152602001610342565b50505050905090810190601f1680156103875780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a16040517fd0498dd3000000000000000000000000000000000000000000000000000000008152602060048201818152835160248401528351600160a060020a0386169363d0498dd39386939283926044019185019080838360005b838110156104095781810151838201526020016103f1565b50505050905090810190601f1680156104365780820380516001836020036101000a031916815260200191505b5092505050600060405180830381600087803b15801561045557600080fd5b505af1158015610469573d6000803e3d6000fd5b505050505050565b60005433600160a060020a0390811691161461048c57600080fd5b60008054604051600160a060020a03909116917ff8df31144d9c2f0f6b59d69b8b98abd5459d07f2742c4df920b25aae33c6482091a26000805473ffffffffffffffffffffffffffffffffffffffff19169055565b60005433600160a060020a039081169116146104fc57600080fd5b604051600160a060020a038216907f42c11a7a3ead6edab391799fdcd01828e174df779fab85e9c53bfcb1caa157a890600090a2604080517f464a41ed00000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905173__./AddressSet.sol:AddressSet___________9163464a41ed916044808301926020929190829003018186803b1580156105aa57600080fd5b505af41580156105be573d6000803e3d6000fd5b505050506040513d60208110156105d457600080fd5b505050565b600054600160a060020a031690565b600054600160a060020a031681565b604080517f7ef295c900000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905160009173__./AddressSet.sol:AddressSet___________91637ef295c991604480820192602092909190829003018186803b15801561067557600080fd5b505af4158015610689573d6000803e3d6000fd5b505050506040513d602081101561069f57600080fd5b505192915050565b60005433600160a060020a039081169116146106c257600080fd5b604051600160a060020a038216907f1fcf49580398cec7111d47c06938189c65978f0e0b979f9e66686023e9f01d9d90600090a2604080517f94771fdb00000000000000000000000000000000000000000000000000000000815260016004820152600160a060020a0383166024820152905173__./AddressSet.sol:AddressSet___________916394771fdb916044808301926020929190829003018186803b1580156105aa57600080fd5b60005433600160a060020a0390811691161461078b57600080fd5b604051600160a060020a0383169082156108fc029083906000818181858888f193505050501580156105d4573d6000803e3d6000fd5b60005433600160a060020a039081169116146107dc57600080fd5b6107e5816107e8565b50565b600160a060020a03811615156107fd57600080fd5b60008054604051600160a060020a03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a36000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790555600a165627a7a72305820cb2074a0761166a961ca3568835fe540b029892386f3e8904855f453e40934fb0029a165627a7a72305820ce0bc3884aeadb68c2cf3a046111f2e03146d4b0a341f42f7361cfcc75396a8b0029";

    public static final String FUNC_CREATEPACKAGE = "createPackage";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_CLAIMEXCESSETH = "claimExcessEth";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CREATECARRIER = "createCarrier";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event CONTRACTCREATED_EVENT = new Event("contractCreated", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPRENOUNCED_EVENT = new Event("OwnershipRenounced", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    protected PackageManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PackageManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> createPackage(String Seller, String Carrier, String Buyer, String DisputeResolver, BigInteger MerchValue, BigInteger ShippingFee, BigInteger ArrivalTO, BigInteger WaitingForStakesInTO) {
        final Function function = new Function(
                FUNC_CREATEPACKAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(Seller), 
                new org.web3j.abi.datatypes.Address(Carrier), 
                new org.web3j.abi.datatypes.Address(Buyer), 
                new org.web3j.abi.datatypes.Address(DisputeResolver), 
                new org.web3j.abi.datatypes.generated.Uint256(MerchValue), 
                new org.web3j.abi.datatypes.generated.Uint256(ShippingFee), 
                new org.web3j.abi.datatypes.generated.Uint256(ArrivalTO), 
                new org.web3j.abi.datatypes.generated.Uint256(WaitingForStakesInTO)), 
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

    public RemoteCall<TransactionReceipt> claimExcessEth() {
        final Function function = new Function(
                FUNC_CLAIMEXCESSETH, 
                Arrays.<Type>asList(), 
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

    public RemoteCall<TransactionReceipt> createCarrier() {
        final Function function = new Function(
                FUNC_CREATECARRIER, 
                Arrays.<Type>asList(), 
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

    public static RemoteCall<PackageManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PackageManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<PackageManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PackageManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<ContractCreatedEventResponse> getContractCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTRACTCREATED_EVENT, transactionReceipt);
        ArrayList<ContractCreatedEventResponse> responses = new ArrayList<ContractCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContractCreatedEventResponse typedResponse = new ContractCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ContractCreatedEventResponse> contractCreatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ContractCreatedEventResponse>() {
            @Override
            public ContractCreatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONTRACTCREATED_EVENT, log);
                ContractCreatedEventResponse typedResponse = new ContractCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ContractCreatedEventResponse> contractCreatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTRACTCREATED_EVENT));
        return contractCreatedEventObservable(filter);
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

    public static PackageManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PackageManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static PackageManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PackageManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ContractCreatedEventResponse {
        public Log log;

        public String addr;
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
