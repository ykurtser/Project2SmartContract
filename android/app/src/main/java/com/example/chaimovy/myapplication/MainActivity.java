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

    Button buyerSellerBt, deliveryManagerBt, deliveryGuyBt, disputeResolverBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buyerSellerBt = (Button) findViewById(R.id.buyerSellerBt);
        deliveryManagerBt = (Button) findViewById(R.id.carrierManagerBt);
        deliveryGuyBt = (Button) findViewById(R.id.deliveryGuyBt);
        disputeResolverBt = (Button) findViewById(R.id.disputeResolverBt);

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SharedPref.edit();

        buyerSellerBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityBuyerSeller.class);
                editor.putString("WhoAmI", "BuyerSeller");
                editor.apply();
                startActivity(I);
            }

        });
        deliveryManagerBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI", "DeliveryManager");
                editor.apply();
                startActivity(I);
            }

        });
        deliveryGuyBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityDeliveryCompany.class);
                editor.putString("WhoAmI", "DeliveryGuy");
                editor.apply();
                startActivity(I);
            }

        });
        disputeResolverBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivityBuyerSeller.class);
                editor.putString("WhoAmI", "DispRes");
                editor.apply();
                startActivity(I);
            }

        });


    }
}
