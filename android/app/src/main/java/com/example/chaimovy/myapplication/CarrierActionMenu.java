
// CODING: DONE IF NO FURTHER FEATURES
// DESING TODO
package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CarrierActionMenu extends AppCompatActivity {

    String address;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_action_menu);
        Button createPkgBt = findViewById(R.id.carrierCreatePkgBt);
        Button myPkgsBt = findViewById(R.id.CarrierMyPkgsBt);
        Button signPkgBt = findViewById(R.id.CarrierSignPkgBt);
        Button openDispBt = findViewById(R.id.CarrierOpenDisputeBt);
        Button addDeliveryGuyBt = findViewById(R.id.addDeliveryGuyBt);
        if (getIntent().hasExtra("address") &&getIntent().hasExtra("key"))
        {
            address=getIntent().getExtras().getString("address");
            key=getIntent().getExtras().getString("key");
        }

        createPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), CreatePackage.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });
        myPkgsBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), MyPackages.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });
        signPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), SignPackage.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });

        openDispBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), OpenDispute.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });
        addDeliveryGuyBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), AddDeliveryGuy.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });
    }
}
