// CODING: DONE IF NO FURTHER FEATURES
//DESING TODO

package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActionMenu extends AppCompatActivity {


    String address;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);
        Button createPkgBt = findViewById(R.id.createPkgBt);
        Button myPkgsBt = findViewById(R.id.myPkgsBt);
        Button signPkgBt = findViewById(R.id.signPkgBt);
        Button openDispBt = findViewById(R.id.openDispBt);
        Button returnPkgBt = findViewById(R.id.returnPkgBt);
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
        returnPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), ReturnPackage.class);
                I.putExtra("address", address);
                I.putExtra("key", key);
                startActivity(I);

            }

        });

    }
}
