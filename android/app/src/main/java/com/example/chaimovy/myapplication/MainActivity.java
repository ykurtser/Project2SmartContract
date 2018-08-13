// CODING: DONE
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BuyerSellerBt = (Button) findViewById(R.id.buyerSellerBt);
        Button DeliveryManagerBt = (Button) findViewById(R.id.deliveryManagerBt);
        Button DeliveryGuyBt = (Button) findViewById(R.id.deliveryGuyBt);

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SharedPref.edit();

        BuyerSellerBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityBuyerSeller.class);
                editor.putString("WhoAmI", "BuyerSeller");
                editor.apply();
                startActivity(I);
            }

        });
        DeliveryManagerBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI", "DeliveryManager");
                editor.apply();
                startActivity(I);
            }

        });
        DeliveryGuyBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI", "DeliveryGuy");
                editor.apply();
                startActivity(I);
            }

        });


    }
}
