package com.example.chaimovy.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

public class SolveDispute extends Web3Activity {

    TextView addrTxt, carrierCutTxt, debugTxt;
    EditText sellerCutTxt;
    Button resolveBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_dispute);

        initViews();

        sellerCutTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sellerCutSt = sellerCutTxt.getText().toString();
                if (sellerCutSt.length() == 0){
                    carrierCutTxt.setText("");
                }
                else {
                    Integer SellerCut = Integer.valueOf(sellerCutSt);
                    Integer CarrierCut = 100 - SellerCut;
                    carrierCutTxt.setText(CarrierCut.toString());
                }
            }
        });

        resolveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SolveDisputeTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });




    }

    private void initViews() {
        addrTxt = (TextView) findViewById(R.id.reresolveDisputeAddress);
        carrierCutTxt = (TextView) findViewById(R.id.resolveDisputeCarrierCut);
        debugTxt = (TextView) findViewById(R.id.resolveDisputeDebugText);
        sellerCutTxt = (EditText) findViewById(R.id.resolveDisputeSellerCut);
        resolveBt = (Button) findViewById(R.id.resolveDisputeBt);
    }

    class SolveDisputeTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        String pkgAddrSt;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            pkgAddrSt = addrTxt.getText().toString();
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {


            try{
                P2Package pkg = P2Package.load(pkgAddrSt,web3,myCred,gasPrice,gasLimit);

                String dispResolverAddr = pkg.getDisputeResolver().send();
                if (!dispResolverAddr.equals(myAddr)){
                    throw new Exception("You are not set as the dispute resolver fot this package");
                }

                int pkgState = pkg.getState().send().intValue();
                if (pkgState != 3) {
                    throw new Exception("Package is not under dispute, its state is: \n" + P2Package.getStateString(pkgState));
                }

                BigInteger sellersCut = new BigInteger(sellerCutTxt.getText().toString());
                if (sellersCut.intValue() < 0 || sellersCut.intValue() > 100){
                    throw new Exception("The cut must be in range [0-100] fix it and try again");
                }

                pkg.resolveDispute(sellersCut).send();

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
                debugTxt.setText(exc.getMessage());
            }
            else{
                debugTxt.setText("Funds Transfered");
            }
        }

    }
}
