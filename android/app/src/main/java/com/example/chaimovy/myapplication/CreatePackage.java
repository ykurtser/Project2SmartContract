package com.example.chaimovy.myapplication;

import android.os.Bundle;
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

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

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

    P2PackageManager pMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:7545"));
        EthGetBalance ethGetBalance=null;
        try {
            ethGetBalance = web3.ethGetBalance(myAddr, DefaultBlockParameterName.LATEST).sendAsync().get();
        }
        catch (ExecutionException e){

        }
        catch (InterruptedException e){

        }
        BigInteger myBalance = ethGetBalance.getBalance();
        Credentials myCred=Credentials.create(myKey,myAddr);


        pMan = P2PackageManager.load(getString(R.string.packageManagerAddr), web3, myCred, new BigInteger("90990051782"), myBalance);


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

        final Button createPackageBt  = (Button)findViewById(R.id.CreateNewPkgBt);


        createPackageBt.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        String BuyerAddrStr     = buyerAddr.getText().toString();
                        String sellerAddrStr    = sellerAddr.getText().toString();
                        String dispResAddrstr   = dispResAddr.getText().toString();
                        String carrierAddrStr   = carrierAddr.getText().toString();
                        BigInteger shippigFeeInt   = new BigInteger(shippigFee.getText().toString());
                        BigInteger merchValueInt   =new BigInteger(merchValue.getText().toString());
                        BigInteger TO1Int          = new BigInteger(TO1.getText().toString());
                        BigInteger TO2Int          = new BigInteger(TO2.getText().toString());
                        BigInteger initialPaymentInt  = new BigInteger((initialPayment.getText().toString()));
                        try
                        {
                            TransactionReceipt txRecp = pMan.createPackage(sellerAddrStr,carrierAddrStr,BuyerAddrStr,dispResAddrstr,merchValueInt,shippigFeeInt,TO1Int,TO2Int).send();
                            String txReciept = txRecp.toString();//.getLogs().get(0).getData();
                            createPackageBt.setText(txReciept);

                        }
                        catch (Exception e){
                            createPackageBt.setText(e.toString());

                        }



                    }
                });


    }
}
