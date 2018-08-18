// CODING: TODO + ADD QR MODULE
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class SignPackage extends Web3Activity {

    Button scanQrBt, signPkgBt;
    private AutoCompleteTextView pkgAddrText;
    TextView locationText,debugText;
    String whoAmI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_package);

        initViews();

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        whoAmI=SharedPref.getString("WhoAmI","");

        scanQrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(SignPackage.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        signPkgBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SignPackageTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });


    }

    private void initViews() {
        scanQrBt = (Button) findViewById(R.id.signPkgScanQRCode);
        signPkgBt = (Button) findViewById(R.id.signPkgBt);
        pkgAddrText = (AutoCompleteTextView) findViewById(R.id.signPkgAddrText);
        locationText = (TextView) findViewById(R.id.signPackageLocation);
        debugText = (TextView) findViewById(R.id.signPkgDebugText);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                pkgAddrText.setText(result.getContents());
            }
        }
    }


    class SignPackageTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        int pkgState;
        String pkgAddrSt;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            pkgAddrSt = pkgAddrText.getText().toString();
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {
            try{
                P2Package pkg = P2Package.load(pkgAddrSt,web3,myCred,gasPrice,gasLimit);
                pkgState = pkg.getState().send().intValue();
                if (pkgState != 1 && pkgState != 2){
                    throw new Exception("Package is in state: " + P2Package.getStateString(pkgState) + ". Cant sign Pkg.");
                }
                if (whoAmI.equals("BuyerSeller")){
                    pkg.signPackage(locationText.getText().toString());
                }
                else {
                    P2Carrier carrier = P2Carrier.load(carrierAddr,web3,myCred,gasPrice,gasLimit);
                    if (!carrier.containsStation(myAddr).send().booleanValue()){
                        throw new Exception("Your address can't sign pkgs through this carrier");
                    }
                    carrier.signPackage(pkgAddrSt,locationText.getText().toString());
                }
            }
            catch (Exception e){
                exc = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);
            if (exc!=null) {
                debugText.setText(exc.getMessage());
            }
            else {
                debugText.setText("Package signed");
            }
        }

    }
}
