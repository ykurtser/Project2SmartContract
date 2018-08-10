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
import java.util.HashSet;
import java.util.Set;

public class CreatePackage extends Web3Activity {

    EditText buyerAddrTxt;
    EditText sellerAddrTxt;
    EditText dispResAddrTxt;
    EditText carrierAddrTxt;
    EditText shippigFeeTxt;
    EditText merchValueTxt;
    EditText TO1Txt;
    EditText TO2Txt;
    EditText initialPaymentTxt;
    EditText createdAddr;

    EditText AddrKey;
    EditText ExceptionText;

    Button createPackageBt;
    Button fillDefaultsBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        createPackageBt.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        String buyerAddr                = buyerAddrTxt.getText().toString();
                        String sellerAddr               = sellerAddrTxt.getText().toString();
                        String dispResolvAddr           = dispResAddrTxt.getText().toString();
                        String carrierAddr              = carrierAddrTxt.getText().toString();
                        BigInteger shippingFee          = new BigInteger(shippigFeeTxt.getText().toString());
                        BigInteger merchVal             = new BigInteger(merchValueTxt.getText().toString());
                        BigInteger TO1Int               = new BigInteger(TO1Txt.getText().toString());
                        BigInteger TO2Int               = new BigInteger(TO2Txt.getText().toString());
                        BigInteger initialPaymentInt    = new BigInteger((initialPaymentTxt.getText().toString()));  //TODO check if relevant and add functionality

                        try
                        {
                            P2PackageManager pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

                            TransactionReceipt txRecp = pMan.createPackage(sellerAddr,carrierAddr,buyerAddr,dispResolvAddr,merchVal,shippingFee,TO1Int,TO2Int).send();

                            String createdPkgAddr = pMan.getContractCreatedEvents(txRecp).get(0).addr;

                            //Adding pkg to shared properties
                            SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);
                            final SharedPreferences.Editor editor = SharedPref.edit();
                            //get existing pkg addresses set, empty if empty
                            Set<String> pkgSet = new HashSet<>();
                            pkgSet = SharedPref.getStringSet(myAddr,pkgSet);
                            //add new pkg addr
                            pkgSet.add(createdPkgAddr);
                            //write back
                            editor.putStringSet(myAddr,pkgSet);
                            editor.apply();

                            SharedPreferences.Editor pkgInfoEditor = getSharedPreferences(createdPkgAddr, Context.MODE_PRIVATE).edit();
                            pkgInfoEditor.putString("sellerAddr",sellerAddr);
                            pkgInfoEditor.putString("carrierAddr",carrierAddr);
                            pkgInfoEditor.putString("buyerAddr",buyerAddr);
                            pkgInfoEditor.putString("dispResolvAddr",dispResolvAddr);
                            pkgInfoEditor.putString("shippingFee",shippingFee.toString());
                            pkgInfoEditor.putString("merchVal",merchVal.toString());
                            pkgInfoEditor.apply();


                            createdAddr.setText(createdPkgAddr);
                        }
                        catch (Exception e){
                            createdAddr.setText(e.toString());
                        }



                    }
                });

        fillDefaultsBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                buyerAddrTxt.setText("0x175A2e653C2bf106Ac1a061f26738A61ADC91C1d");
                sellerAddrTxt.setText("0xb032FCEf81Ac18844FCE6daCBF1e46FF4dAf0540");
                dispResAddrTxt.setText("0xc6C787E3a5784155461E643E193f1c72CC355528");
                carrierAddrTxt.setText("0x37968917CdC7550CF2039cBef47A7F5903fd2bc8");
                shippigFeeTxt.setText("100000000000000000");
                merchValueTxt.setText("150000000000000000");
                TO1Txt.setText("2");
                TO2Txt.setText("3");
                initialPaymentTxt.setText("0");
            }
        });


    }

    private void initViews() {
        setContentView(R.layout.activity_create_package);

        buyerAddrTxt      = findViewById(R.id.buyerAddrText);
        sellerAddrTxt     = findViewById(R.id.SellerAddrText);
        dispResAddrTxt    = findViewById(R.id.DisputeResolverAddrText);
        carrierAddrTxt    = findViewById(R.id.CarrierAddrText);
        shippigFeeTxt     = findViewById(R.id.ShippingFeeText);
        merchValueTxt     = findViewById(R.id.merchValText);
        TO1Txt            = findViewById(R.id.TO1Text);
        TO2Txt            = findViewById(R.id.TO2Text);
        initialPaymentTxt = findViewById(R.id.initialPaymentText);
        createdAddr       = findViewById(R.id.CreatePkgAddrText);

        createPackageBt   = findViewById(R.id.CreateNewPkgBt);
        fillDefaultsBt    = findViewById(R.id.FillDefaultCreatePkg);
    }
}
