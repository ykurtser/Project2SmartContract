package com.example.chaimovy.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chaimovy.P2PackageManager;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
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


        ExceptionText = (EditText)findViewById(R.id.debug_exception);
        AddrKey = (EditText)findViewById(R.id.debug_addr_key);


        AddrKey.setText("addr: " + myAddr + ", key: " + myKey);

        try {
            Web3j web33 = Web3jFactory.build(new HttpService("https://ropsten.infura.io/06KZcq3hzQMYyXUurR9q"));


            EthGetBalance ethGetBalance = web33.ethGetBalance("0x175A2e653C2bf106Ac1a061f26738A61ADC91C1d", DefaultBlockParameterName.LATEST).sendAsync().get();

            String wei = ethGetBalance.getBalance().toString();


            ExceptionText.setText(Convert.fromWei(wei, Convert.Unit.ETHER).toString());


            String BuyerAddrStr = "0x5E46aef5B1ce6C86aF9B27B10f011d4A2348D1ec";
            String sellerAddrStr = "0x52DF6906851A5CaEE19CDC6442d296D952338a0a";
            String dispResAddrstr = "0x780E9bDC47Ef692116B76f40aa2Ed7bd7bB70Ef4";
            String carrierAddrStr = "0x780E9bDC47Ef692116B76f40aa2Ed7bd7bB70Ef4";
            BigInteger shippigFeeInt = new BigInteger("999999999");
            BigInteger merchValueInt = new BigInteger("999999998");
            BigInteger TO1Int = new BigInteger("2");
            BigInteger TO2Int = new BigInteger("3");
            BigInteger initialPaymentInt = new BigInteger("0");

                    //0x517ab26d369ac2add9124fc464e818be788e08492c3f5fa516456973b1ec6e43 yuval account (package manager owner)
            //e995e2b620a0637d164560f902d1a33a53bbdca5fef9afb6794e3174626b6185 my account
            //
            Credentials myCred= Credentials.create("e995e2b620a0637d164560f902d1a33a53bbdca5fef9afb6794e3174626b6185");


            BigInteger gasPrice= Convert.toWei("0.0000001", Convert.Unit.ETHER).toBigInteger();
            BigInteger gasLimit = new BigDecimal("6721975").toBigInteger();

            pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web33, myCred, gasPrice, gasLimit);


            TransactionReceipt txRecp = pMan.createCarrier().sendAsync().get();
            //TransactionReceipt txRecp2 = pMan.claimExcessEth().sendAsync().get();

            ExceptionText.setText(pMan.getContractCreatedEvents(txRecp).get(0).addr);
            //ExceptionText.setText(txRecp2.toString());

            /*
            String Owner = pMan.getOwner().sendAsync().get();

            ExceptionText.setText(Owner);

            TransactionReceipt txRecp = pMan.createPackage(sellerAddrStr, carrierAddrStr, BuyerAddrStr, dispResAddrstr, merchValueInt, shippigFeeInt, TO1Int, TO2Int).send();



            int numOfLogs = txRecp.getLogs().size();
            int i=0;
            String AllLogs = "";
            while (i<numOfLogs){
                AllLogs += "#" + i + ": " + txRecp.getLogs().get(i).toString() + "       ";
                i++;
            }
            ExceptionText.setText(pMan.getContractCreatedEvents(txRecp).get(0).addr);

*/

            //String newPkgAddr = txRecp.getLogs().get(0).getData();
            //String account1 ="";
            //
            //account1 = web33.ethAccounts().send().getAccounts().get(0);
            //myCred1 =

            //RemoteCall<TransactionReceipt> tr = Transfer.sendFunds(web33,myCred1,"0x52DF6906851A5CaEE19CDC6442d296D952338a0a", BigDecimal.valueOf(1.0), Convert.Unit.ETHER);

        }
        catch (Exception e){
            ExceptionText.setText(e.toString());
        }


        final Button createPackageBt = (Button) findViewById(R.id.CreateNewPkgBt);
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
                            //String account1 ="";
                            //Credentials myCred1= null;
                            //account1 = web33.ethAccounts().send().getAccounts().get(0);
                            //myCred1 = Credentials.create("");

                            //RemoteCall<TransactionReceipt> tr = Transfer.sendFunds(web33,myCred1,"0x52DF6906851A5CaEE19CDC6442d296D952338a0a", BigDecimal.valueOf(1.0), Convert.Unit.ETHER);





                        }
                        catch (Exception e){
                            ExceptionText.setText(e.toString());
                        }



                    }
                });


    }
}
