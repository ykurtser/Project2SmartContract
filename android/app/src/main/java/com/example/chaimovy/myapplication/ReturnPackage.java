// CODING: TODO
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ReturnPackage extends Web3Activity {

    Button scanQrBt, returnPkgBt;
    private AutoCompleteTextView pkgAddrText;
    TextView reasonText,debugText;
    String whoAmI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_package);

        initViews();

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        whoAmI=SharedPref.getString("WhoAmI","");

        scanQrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(ReturnPackage.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        returnPkgBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    P2Package pkg = P2Package.load(pkgAddrText.getText().toString(),web3,myCred,gasPrice,gasLimit);
                    int pkgState = pkg.getState().send().intValue();
                    if (pkgState != 1){
                        Toast.makeText(getBaseContext(), "Package is in state: " + P2Package.getStateString(pkgState) + ". Cant return Pkg.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    pkg.returnPackage(reasonText.getText().toString());

                    pkgState = pkg.getState().send().intValue();
                    if (pkgState != 2){
                        debugText.setText("Oops, something went wrong, can't return package right now. ");
                        return;
                    }

                    debugText.setText("Package State changed to returned");
                }
                catch (Exception e){
                    debugText.setText("Exception: " + e.getMessage());
                }


            }
        });


    }

    private void initViews() {
        scanQrBt = (Button) findViewById(R.id.retPkgScanQRCode);
        returnPkgBt = (Button) findViewById(R.id.retPkgBt);
        pkgAddrText = (AutoCompleteTextView) findViewById(R.id.retPkgAddrText);
        reasonText = (TextView) findViewById(R.id.retPkgResonText);
        debugText = (TextView) findViewById(R.id.retPkgDebugText);

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
}
