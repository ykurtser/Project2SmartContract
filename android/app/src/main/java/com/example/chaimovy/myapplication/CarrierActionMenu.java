
// CODING: DONE IF NO FURTHER FEATURES
// DESING TODO
package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//import androidx.appcompat.app.AppCompatActivity;

public class CarrierActionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_action_menu);

        Button createPkgBt = (Button)findViewById(R.id.carrierCreatePkgBt);
        Button myPkgsBt = (Button)findViewById(R.id.CarrierMyPkgsBt);
        Button signPkgBt = (Button)findViewById(R.id.CarrierSignPkgBt);
        Button openDispBt = (Button)findViewById(R.id.CarrierOpenDisputeBt);
        Button addDeliveryGuyBt = (Button) findViewById(R.id.addDeliveryGuyBt);


        createPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), CreatePackage.class);
                startActivity(I);
            }

        });
        myPkgsBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), MyPackages.class);
                startActivity(I);

            }

        });
        signPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), SignPackage.class);
                startActivity(I);

            }

        });

        openDispBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), OpenDispute.class);
                startActivity(I);

            }

        });
        addDeliveryGuyBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), AddDeliveryGuy.class);
                startActivity(I);

            }

        });
    }
}
