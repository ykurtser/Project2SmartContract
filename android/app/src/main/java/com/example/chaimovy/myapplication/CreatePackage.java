package com.example.chaimovy.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chaimovy.P2PackageManager;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePackage extends AppCompatActivity {

    EditText buyerAddr;
    EditText sellerAddr;
    EditText dispResAddr;
    EditText carrierAddr;
    EditText shippigFee;
    EditText merchValue;
    EditText TO1;
    EditText TO2;
    EditText initialPayment;

    EditText AddrKey;
    EditText ExceptionText;

    P2PackageManager pMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_create_package);
        String myKey="";
        String myAddr="";



        if (getIntent().hasExtra("key") && getIntent().hasExtra("address")){
            myKey=getIntent().getExtras().getString("key");
            myAddr=getIntent().getExtras().getString("address");
        }

        if (myKey.isEmpty() || myAddr.isEmpty()) {
            throw  new IllegalArgumentException();
        }

        /*
        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:7545"));
        EthGetBalance ethGetBalance=null;
        try {
            ethGetBalance = web3.ethGetBalance(myAddr, DefaultBlockParameterName.LATEST).sendAsync().get();
        }
        catch (ExecutionException e){

        }
        catch (InterruptedException e){

        }

        //KeyPair kp = new KeyPair(Numeric.toBigInt(myAddr),Numeric.toBigInt(myKey));
//        final ECKeyPair keyPair = ECKeyPair.create(Numeric.toBigInt(myKey));
//        BigInteger publicKey = keyPair.getPublicKey();

        //later to be use in Web3j you may need
//        final Credentials myCred = Credentials.create(keyPair);
        BigInteger myBalance = ethGetBalance.getBalance();
//        Credentials myCred=Credentials.create(myKey,myAddr);

        ExceptionText = (EditText)findViewById(R.id.debug_exception);

        String account ="";
        Credentials myCred= null;
        try {
            account = web3.ethAccounts().send().getAccounts().get(0);
            myCred = Credentials.create(account);
            ECKeyPair keyPair = myCred.getEcKeyPair();
            BigInteger privateKey = keyPair.getPrivateKey();
            BigInteger publicKey = keyPair.getPublicKey();
        }
        catch ( IOException e ) {
            ExceptionText.setText(e.getMessage());
        }



        pMan = P2PackageManager.load(getString(R.string.packageManagerAddr), web3, myCred, new BigInteger("90990051782"), myBalance);

*/
        //set objects to all buttons/textviews
        buyerAddr      = (EditText)findViewById(R.id.buyerAddrText);
        sellerAddr     = (EditText)findViewById(R.id.SellerAddrText);
        dispResAddr    = (EditText)findViewById(R.id.DisputeResolverAddrText);
        carrierAddr    = (EditText)findViewById(R.id.CarrierAddrText);
        shippigFee     = (EditText)findViewById(R.id.ShippingFeeText);
        merchValue     = (EditText)findViewById(R.id.merchValText);
        TO1            = (EditText)findViewById(R.id.TO1Text);
        TO2            = (EditText)findViewById(R.id.TO2Text);
        initialPayment = (EditText)findViewById(R.id.initialPaymentText);

        AddrKey = (EditText)findViewById(R.id.debug_addr_key);


        AddrKey.setText("addr: " + myAddr + ", key: " + myKey);

        final Button createPackageBt  = (Button)findViewById(R.id.CreateNewPkgBt);


        createPackageBt.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        /*
                        String BuyerAddrStr     = buyerAddr.getText().toString();
                        String sellerAddrStr    = sellerAddr.getText().toString();
                        String dispResAddrstr   = dispResAddr.getText().toString();
                        String carrierAddrStr   = carrierAddr.getText().toString();
                        BigInteger shippigFeeInt   = new BigInteger(shippigFee.getText().toString());
                        BigInteger merchValueInt   =new BigInteger(merchValue.getText().toString());
                        BigInteger TO1Int          = new BigInteger(TO1.getText().toString());
                        BigInteger TO2Int          = new BigInteger(TO2.getText().toString());
                        BigInteger initialPaymentInt  = new BigInteger((initialPayment.getText().toString()));
                        */

                        String BuyerAddrStr     = "0x5E46aef5B1ce6C86aF9B27B10f011d4A2348D1ec";
                        String sellerAddrStr    = "0x52DF6906851A5CaEE19CDC6442d296D952338a0a";
                        String dispResAddrstr   = "0x780E9bDC47Ef692116B76f40aa2Ed7bd7bB70Ef4";
                        String carrierAddrStr   = "0x780E9bDC47Ef692116B76f40aa2Ed7bd7bB70Ef4";
                        BigInteger shippigFeeInt   = new BigInteger("999999999");
                        BigInteger merchValueInt   =new BigInteger("999999998");
                        BigInteger TO1Int          = new BigInteger("2");
                        BigInteger TO2Int          = new BigInteger("3");
                        BigInteger initialPaymentInt  = new BigInteger("0");

                        try
                        {

                            //TransactionReceipt txRecp = pMan.createPackage(sellerAddrStr,carrierAddrStr,BuyerAddrStr,dispResAddrstr,merchValueInt,shippigFeeInt,TO1Int,TO2Int).send();

                            //String newPkgAddr = txRecp.getLogs().get(0).getData();
                            Web3j web33 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/06KZcq3hzQMYyXUurR9q"));
                            //String account1 ="";
                            //Credentials myCred1= null;
                            //account1 = web33.ethAccounts().send().getAccounts().get(0);
                            //myCred1 = Credentials.create("");

                            //RemoteCall<TransactionReceipt> tr = Transfer.sendFunds(web33,myCred1,"0x52DF6906851A5CaEE19CDC6442d296D952338a0a", BigDecimal.valueOf(1.0), Convert.Unit.ETHER);



                            EthGetBalance ethGetBalance = web33
                                    .ethGetBalance("0x175A2e653C2bf106Ac1a061f26738A61ADC91C1d", DefaultBlockParameterName.LATEST)
                                    .sendAsync()
                                    .get();

                            String wei = ethGetBalance.getBalance().toString();


                            ExceptionText.setText(Convert.fromWei(wei,Convert.Unit.ETHER).toString());

                        }
                        catch (Exception e){
                            ExceptionText.setText(e.toString());
                        }



                    }
                });


    }
}
