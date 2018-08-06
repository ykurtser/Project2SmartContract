// CODING: DONE
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:7545"));
        Button BuyerSellerBt = findViewById(R.id.BuyerSellerBt);
        Button DeliveryManagerBt = findViewById(R.id.DeliveryManagerBt);
        Button DeliveryGuyBt = findViewById(R.id.DeliveryGuyBt);

        SharedPreferences SharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SharedPref.edit();

        BuyerSellerBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent I = new Intent(getApplicationContext(), LoginActivityBuyerSeller.class);
                editor.putString("WhoAmI","BuyerSeller");
                editor.apply();
                startActivity(I);
            }

        });
        DeliveryManagerBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI","DeliveryManager");
                editor.apply();
                startActivity(I);
            }

        });
        DeliveryGuyBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI","DeliveryGuy");
                editor.apply();
                startActivity(I);
            }

        });



    }
}
