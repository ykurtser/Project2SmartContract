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

public class OpenDispute extends Web3Activity {

    Button scanTQrBt, openDisputeBt;
    TextView debugText, pkgAddrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dispute);

        initViews();

        scanTQrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(OpenDispute.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });


        openDisputeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OpenDisputeTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });




    }

    private void initViews() {
        scanTQrBt = (Button) findViewById(R.id.openDispQrBt);
        openDisputeBt = (Button) findViewById(R.id.openDispBt);
        pkgAddrText = (AutoCompleteTextView) findViewById(R.id.openDispAddrText);
        debugText = (TextView) findViewById(R.id.openDispDebugText);
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

    class OpenDisputeTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        Integer pkgState;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {

            try{
                P2Package pkg = P2Package.load(pkgAddrText.getText().toString(),web3,myCred,gasPrice,gasLimit);

                pkg.openDispute().send();
                pkgState = pkg.getState().send().intValue();
            }
            catch (Exception e){
                exc=e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);

            if (exc!=null){
                debugText.setText("Exception: " + exc.getMessage());
                return;
            }
            if (pkgState != 3){
                debugText.setText("Oops, something went wrong, can't open dispute right now. ");
                return;
            }

            debugText.setText("Dispute opened");
        }

    }
}
