//CODING TODO
//DESIGN TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

//import androidx.appcompat.app.AppCompatActivity;

public class AddDeliveryGuy extends Web3Activity {

    Button addCarrierBt;
    TextView debugTxt, addrTxt;
    String managerAddr;
    boolean isManager = false;
    P2Carrier carrierCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_guy);

        initViews();

        try {
            carrierCompany = P2Carrier.load(carrierAddr, web3, myCred, gasPrice, gasLimit);
            managerAddr = carrierCompany.getOwner().send();
        } catch (Exception e){
            managerAddr="";
        }

        if (!managerAddr.equals(myAddr)){
            debugTxt.setText("You are not The Carrier company manager, can't add carriers");
        } else{
            isManager = true;
        }

        addCarrierBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isManager) return;
                new AddDeliveryGuyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

    }

    private void initViews() {
        addCarrierBt = (Button) findViewById(R.id.addDeliveryGuyBt);
        debugTxt = (TextView) findViewById(R.id.addDeliveryGuyDebugText);
        addrTxt = (TextView) findViewById(R.id.addDeliveryGuyAddrText);
    }

    class AddDeliveryGuyTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        String Carrier2Add;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            Carrier2Add = addrTxt.getText().toString();
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {

            try {
                carrierCompany.addDeliveryStation(Carrier2Add).send();
            } catch (Exception e) {
                exc = e;
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);

            if (exc != null){
                debugTxt.setText("Couldn't add Carrier: " + exc.getMessage());
            } else {
                debugTxt.setText("Carrier " + Carrier2Add + "\nadded");
            }
        }

    }
}
