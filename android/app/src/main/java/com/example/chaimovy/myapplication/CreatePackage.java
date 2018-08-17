// CODING: SHOULD BE READY, NEEDS CODE REVIEW AND MANUAL TEST
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

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
    EditText createdAddr;

    EditText AddrKey;
    EditText ExceptionText;

    Button createPackageBt;
    Button fillDefaultsBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        createPackageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CreatePackageTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                /*
                String buyerAddr = buyerAddrTxt.getText().toString();
                String sellerAddr = sellerAddrTxt.getText().toString();
                String dispResolvAddr = dispResAddrTxt.getText().toString();
                String carrierAddr = carrierAddrTxt.getText().toString();
                BigInteger shippingFee = Convert.toWei(shippigFeeTxt.getText().toString(), Convert.Unit.ETHER).toBigInteger();
                BigInteger merchVal = Convert.toWei(merchValueTxt.getText().toString(), Convert.Unit.ETHER).toBigInteger();
                BigInteger TO1Int = new BigInteger(TO1Txt.getText().toString());
                BigInteger TO2Int = new BigInteger(TO2Txt.getText().toString());

                try {
                    P2PackageManager pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

                    TransactionReceipt txRecp = pMan.createPackage(sellerAddr, carrierAddr, buyerAddr, dispResolvAddr, merchVal, shippingFee, TO1Int, TO2Int).send();

                    String createdPkgAddr = pMan.getContractCreatedEvents(txRecp).get(0).addr;

                    //Adding pkg to shared properties
                    SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);
                    final SharedPreferences.Editor editor = SharedPref.edit();
                    //get existing pkg addresses set, empty if empty
                    Set<String> pkgSet = new HashSet<>();
                    pkgSet = SharedPref.getStringSet(myAddr, pkgSet);
                    //add new pkg addr
                    pkgSet.add(createdPkgAddr);
                    //write back
                    editor.putStringSet(myAddr, pkgSet);
                    editor.apply();

                    SharedPreferences.Editor pkgInfoEditor = getSharedPreferences(createdPkgAddr, Context.MODE_PRIVATE).edit();
                    pkgInfoEditor.putString("sellerAddr", sellerAddr);
                    pkgInfoEditor.putString("carrierAddr", carrierAddr);
                    pkgInfoEditor.putString("buyerAddr", buyerAddr);
                    pkgInfoEditor.putString("dispResolvAddr", dispResolvAddr);
                    pkgInfoEditor.putString("shippingFee", shippingFee.toString());
                    pkgInfoEditor.putString("merchVal", merchVal.toString());
                    pkgInfoEditor.apply();


                    createdAddr.setText(createdPkgAddr);
                } catch (Exception e) {
                    createdAddr.setText(e.toString());
                }
*/
            }
        });

        fillDefaultsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyerAddrTxt.setText("0x175A2e653C2bf106Ac1a061f26738A61ADC91C1d");
                sellerAddrTxt.setText("0xb032FCEf81Ac18844FCE6daCBF1e46FF4dAf0540");
                dispResAddrTxt.setText("0xc6C787E3a5784155461E643E193f1c72CC355528");
                carrierAddrTxt.setText("0x37968917CdC7550CF2039cBef47A7F5903fd2bc8");
                shippigFeeTxt.setText("0.2");
                merchValueTxt.setText("0.1");
                TO1Txt.setText("2");
                TO2Txt.setText("3");
            }
        });


    }

    private void initViews() {
        setContentView(R.layout.activity_create_package);

        buyerAddrTxt = (EditText) findViewById(R.id.buyerAddrText);
        sellerAddrTxt = (EditText) findViewById(R.id.SellerAddrText);
        dispResAddrTxt = (EditText) findViewById(R.id.DisputeResolverAddrText);
        carrierAddrTxt = (EditText) findViewById(R.id.CarrierAddrText);
        shippigFeeTxt = (EditText) findViewById(R.id.ShippingFeeText);
        merchValueTxt = (EditText) findViewById(R.id.merchValText);
        TO1Txt = (EditText) findViewById(R.id.TO1Text);
        TO2Txt = (EditText) findViewById(R.id.TO2Text);
        createdAddr = (EditText) findViewById(R.id.CreatePkgAddrText);

        createPackageBt = (Button) findViewById(R.id.CreateNewPkgBt);
        fillDefaultsBt = (Button) findViewById(R.id.FillDefaultCreatePkg);
    }

    class CreatePackageTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        P2PackageManager pMan;

        String buyerAddr;
        String sellerAddr;
        String dispResolvAddr;
        String carrierAddr;
        BigInteger shippingFee;
        BigInteger merchVal;
        BigInteger TO1Int;
        BigInteger TO2Int;
        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            buyerAddr = buyerAddrTxt.getText().toString();
            sellerAddr = sellerAddrTxt.getText().toString();
            dispResolvAddr = dispResAddrTxt.getText().toString();
            carrierAddr = carrierAddrTxt.getText().toString();
            shippingFee = Convert.toWei(shippigFeeTxt.getText().toString(), Convert.Unit.ETHER).toBigInteger();
            merchVal = Convert.toWei(merchValueTxt.getText().toString(), Convert.Unit.ETHER).toBigInteger();
            TO1Int = new BigInteger(TO1Txt.getText().toString());
            TO2Int = new BigInteger(TO2Txt.getText().toString());
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {



            try {
                pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

                return pMan.createPackage(sellerAddr, carrierAddr, buyerAddr, dispResolvAddr, merchVal, shippingFee, TO1Int, TO2Int).send();
            } catch (Exception e) {
                exc = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);
            if (txRecp == null) {
                createdAddr.setText(exc.getMessage());
                return;
            }
            if ( pMan.getContractCreatedEvents(txRecp).size()==0 ) {
                createdAddr.setText("oops, couldn't load created events for recp: " + txRecp.getTransactionHash());
                return;
            }
            if (pMan.getContractCreatedEvents(txRecp).isEmpty()){
                createdAddr.setText("got empty tx receipt");
                return;
            }
            String createdPkgAddr = pMan.getContractCreatedEvents(txRecp).get(0).addr;
            createdAddr.setText(createdPkgAddr);

            //Adding pkg to shared properties
            SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = SharedPref.edit();
            //get existing pkg addresses set, empty if empty
            Set<String> pkgSet = new HashSet<>();
            pkgSet = SharedPref.getStringSet(myAddr, pkgSet);
            //add new pkg addr
            pkgSet.add(createdPkgAddr);
            //write back
            editor.putStringSet(myAddr, pkgSet);
            editor.apply();

            SharedPreferences.Editor pkgInfoEditor = getSharedPreferences(createdPkgAddr, Context.MODE_PRIVATE).edit();
            pkgInfoEditor.putString("sellerAddr", sellerAddr);
            pkgInfoEditor.putString("carrierAddr", carrierAddr);
            pkgInfoEditor.putString("buyerAddr", buyerAddr);
            pkgInfoEditor.putString("dispResolvAddr", dispResolvAddr);
            pkgInfoEditor.putString("shippingFee", shippingFee.toString());
            pkgInfoEditor.putString("merchVal", merchVal.toString());
            pkgInfoEditor.apply();



        }

    }
}
