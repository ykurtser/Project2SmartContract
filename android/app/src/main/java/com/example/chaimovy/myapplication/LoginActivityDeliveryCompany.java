// CODING: DONE.
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import android.support.v7.app.AppCompatActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivityDeliveryCompany extends AppCompatActivity {


    private TextView privateKeyText;
    private TextView debugText;
    private TextView companyAddrText;
    private Web3j web3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_delivery_company);

        Button signInBt = (Button) findViewById(R.id.carrierSignInBt);
        Button createNewDeliveryCompanyBt = (Button) findViewById(R.id.createNewDeliveryCompanyBt);
        Button FillLastUsedBt = (Button) findViewById(R.id.FillLastUsedBt);

        final TextView CarrierPublicAddrText = (TextView) findViewById(R.id.CarrierPublicAddrText);
        privateKeyText = (TextView) findViewById(R.id.CarrierKeyText);
        companyAddrText = (TextView) findViewById(R.id.CompanyAddrText);
        debugText = (TextView) findViewById(R.id.DebugDeliveryLoginText);

        final SharedPreferences SharedPrefuserInfo = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor userInfoEditor = SharedPrefuserInfo.edit();

//        userInfoEditor.putString("debug","success");
//        userInfoEditor.apply();
//        debugText.setText("shared pref test:" + SharedPrefuserInfo.getString("debug","failure"));

        web3 = Web3jFactory.build(new HttpService(getResources().getString(R.string.web3HostRopsten)));


        //INTERNALS//


        ///BUTTON LISTENERS///

        signInBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userAddr = CarrierPublicAddrText.getText().toString();
                String key = privateKeyText.getText().toString();
                String companyAddr = companyAddrText.getText().toString();

                //TODO maybe do here check that user address is approved carrier

                userInfoEditor.putString("addr", userAddr);
                userInfoEditor.putString("key", key);
                userInfoEditor.putString("carrier", companyAddr);
                userInfoEditor.apply();

                Intent I = new Intent(getApplicationContext(), CarrierActionMenu.class);
                startActivity(I);

            }

        });

        createNewDeliveryCompanyBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CreateNewDeliveryCompanyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }


        });

        FillLastUsedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lastAddr = SharedPrefuserInfo.getString("addr", getResources().getString(R.string.debugAddrCarrierMan));
                String lastKey = SharedPrefuserInfo.getString("key", getResources().getString(R.string.debugKeyCarrierMan));
                String lastCarrier = SharedPrefuserInfo.getString("carrier", getResources().getString(R.string.debugCarrierContract));

                CarrierPublicAddrText.setText(lastAddr);
                privateKeyText.setText(lastKey);
                companyAddrText.setText(lastCarrier);


            }
        });


    }


    class CreateNewDeliveryCompanyTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private String key;
        Exception exc;
        private P2PackageManager pMan;
        private View loadingLayout;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);
            key = privateKeyText.getText().toString();
            debugText.setText("Trying to Create a new company contract, this can take a minute, please wait...");
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {
            try {
                Credentials myCred = Credentials.create(key);

                BigInteger gasPrice = Convert.toWei("0.00000001", Convert.Unit.ETHER).toBigInteger();
                BigInteger gasLimit = new BigInteger("1149216");

                pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);
                return pMan.createCarrier().sendAsync().get();

            } catch (Exception e) {
                exc = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt transactionReceipt) {
            loadingLayout.setVisibility(View.GONE);
            if (transactionReceipt == null) {
                debugText.setText("Error: \n" + exc.getMessage());
                Toast.makeText(getBaseContext(), "Oops, something went wrong: \n" + exc.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                companyAddrText.setText(pMan.getContractCreatedEvents(transactionReceipt).get(0).addr);
                debugText.setText("Company contract created\nPlease write your address");
            }
        }
    }

}





