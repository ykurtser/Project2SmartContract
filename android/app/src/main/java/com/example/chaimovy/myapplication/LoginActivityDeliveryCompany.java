// CODING: DONE.
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.appcompat.app.AppCompatActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivityDeliveryCompany extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_delivery_company);

        Button signInBt                   = findViewById(R.id.carrierSignInBt);
        Button createNewDeliveryCompanyBt = findViewById(R.id.createNewDeliveryCompanyBt);
        Button FillLastUsedBt             = findViewById(R.id.FillLastUsedBt);

        final TextView CarrierPublicAddrText = findViewById(R.id.CarrierPublicAddrText);
        final TextView PrivateKeyText        = findViewById(R.id.CarrierKeyText);
        final TextView CompanyAddrText       = findViewById(R.id.CompanyAddrText);
        final TextView debugText             = findViewById(R.id.DebugDeliveryLoginText);

        final SharedPreferences SharedPrefuserInfo = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor userInfoEditor = SharedPrefuserInfo.edit();

//        userInfoEditor.putString("debug","success");
//        userInfoEditor.apply();
//        debugText.setText("shared pref test:" + SharedPrefuserInfo.getString("debug","failure"));

        final Web3j web3 = Web3jFactory.build(new HttpService(getResources().getString(R.string.web3HostRopsten)));



        //INTERNALS//



        ///BUTTON LISTENERS///

        signInBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String userAddr = CarrierPublicAddrText.getText().toString();
                String key = PrivateKeyText.getText().toString();
                String companyAddr = CompanyAddrText.getText().toString();

                //TODO maybe do here check that user address is approved carrier

                userInfoEditor.putString("addr"          ,userAddr);
                userInfoEditor.putString("key"           ,key);
                userInfoEditor.putString("carrier"       ,companyAddr);
                userInfoEditor.apply();

                Intent I = new Intent(getApplicationContext(), CarrierActionMenu.class);
                startActivity(I);

            }

        });

        createNewDeliveryCompanyBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try {
                    String UserAddr = CarrierPublicAddrText.getText().toString();
                    String key = PrivateKeyText.getText().toString();

                    //TODO: change to text and not Toast
                    debugText.setText("Trying to Create a new company contract, this can take a minute, please wait...");

                    Credentials myCred = Credentials.create(key);

                    BigInteger gasPrice = Convert.toWei("0.00000001", Convert.Unit.ETHER).toBigInteger();
                    BigInteger gasLimit = new BigInteger("1149216");

                    P2PackageManager pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

                    TransactionReceipt txRecp = pMan.createCarrier().sendAsync().get();

                    //putting created address on Company's addr text field
                    CompanyAddrText.setText(pMan.getContractCreatedEvents(txRecp).get(0).addr);

                    //TODO: change to text and not Toast
                    debugText.setText("Company contract created\nPlease write your address");

                }

                catch (Exception e){
                    debugText.setText("Error: \n" + e.toString());
                    Toast.makeText(getBaseContext(),"Oops, something went wrong: \n" + e.toString(),Toast.LENGTH_LONG).show();
                }


            }


        });

        FillLastUsedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lastAddr = SharedPrefuserInfo.getString("addr",getResources().getString(R.string.debugAddr));
                String lastKey = SharedPrefuserInfo.getString("key",getResources().getString(R.string.debugKey));
                String lastCarrier = SharedPrefuserInfo.getString("carrier",getResources().getString(R.string.debugCarrier));

                CarrierPublicAddrText.setText(lastAddr);
                PrivateKeyText.setText(lastKey);
                CompanyAddrText.setText(lastCarrier);



            }
        });



    }

}





