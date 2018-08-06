// CODING: SHOULD BE READY, NEEDS CODE REVIEW AND MANUAL TEST
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chaimovy.P2PackageManager;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Set;

public class CreatePackage extends Web3Activity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_package);

        buyerAddr      = (EditText)findViewById(R.id.buyerAddrText);
        sellerAddr     = (EditText)findViewById(R.id.SellerAddrText);
        dispResAddr    = (EditText)findViewById(R.id.DisputeResolverAddrText);
        carrierAddr    = (EditText)findViewById(R.id.CarrierAddrText);
        shippigFee     = (EditText)findViewById(R.id.ShippingFeeText);
        merchValue     = (EditText)findViewById(R.id.merchValText);
        TO1            = (EditText)findViewById(R.id.TO1Text);
        TO2            = (EditText)findViewById(R.id.TO2Text);
        initialPayment = (EditText)findViewById(R.id.initialPaymentText);




        final Button createPackageBt = findViewById(R.id.CreateNewPkgBt);
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
                            P2PackageManager pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

                            TransactionReceipt txRecp = pMan.createPackage(sellerAddrStr,carrierAddrStr,BuyerAddrStr,dispResAddrstr,merchValueInt,shippigFeeInt,TO1Int,TO2Int).send();
                            String createdPkgAddr = pMan.getContractCreatedEvents(txRecp).get(0).addr;

                            //Adding pkg to shared properties
                            SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);
                            final SharedPreferences.Editor editor = SharedPref.edit();
                            //get existing pkg addresses set, empty if empty
                            Set<String> pkgSet = Collections.emptySet();
                            pkgSet = SharedPref.getStringSet(myAddr,pkgSet);
                            //add new pkg addr
                            pkgSet.add(createdPkgAddr);
                            //write back
                            editor.putStringSet("pkgTrek",pkgSet);

                            //TODO print out to a text the created address so it could be written down.

                        }
                        catch (Exception e){
                            ExceptionText.setText(e.toString());
                        }



                    }
                });


    }
}
