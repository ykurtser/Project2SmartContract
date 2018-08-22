// CODING: TODO
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import org.web3j.protocol.core.methods.response.TransactionReceipt;


public class ReturnPackage extends Web3Activity {

    private static final String TAG = "DeliveryApp";

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
                new ReturnPackageTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    class ReturnPackageTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        int pkgState;
        String reasonSt;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            reasonSt=reasonText.getText().toString();
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {


            try{
                P2Package pkg = P2Package.load(pkgAddrText.getText().toString(),web3,myCred,gasPrice,gasLimit);
                pkgState = pkg.getState().send().intValue();
                if (pkgState != 1){
                    throw new Exception("Package is in state: " + P2Package.getStateString(pkgState) + ". Cant return Pkg.");
                }

                TransactionReceipt retVal = pkg.returnPackage(reasonSt).send();

                pkgState = pkg.getState().send().intValue();
                if (pkgState != 2){
                    throw new Exception("Oops, something went wrong, can't return package right now. ");
                }

                return retVal;
            }
            catch (Exception e){
                exc=e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);

            if (txRecp!=null){
                Log.i(TAG,"gas for create carrier:\n" + txRecp.getGasUsed().toString() + "\ncumulative gas:" + txRecp.getCumulativeGasUsed().toString());
            }

            if (exc!=null){
                debugText.setText(exc.getMessage());
            }
            else{
                debugText.setText("Package State changed to returned");
            }

        }

    }
}
