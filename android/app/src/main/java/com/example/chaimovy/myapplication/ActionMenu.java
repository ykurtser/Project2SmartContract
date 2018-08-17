// CODING: DONE IF NO FURTHER FEATURES
//DESING TODO

package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//import androidx.appcompat.app.AppCompatActivity;

public class ActionMenu extends AppCompatActivity {


    String address;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_menu);
        Button createPkgBt = (Button) findViewById(R.id.createPkgBt);
        Button myPkgsBt = (Button) findViewById(R.id.myPkgsBt);
        Button signPkgBt =(Button) findViewById(R.id.signPkgBt);
        Button openDispBt = (Button)findViewById(R.id.openDispBt);
        Button returnPkgBt = (Button)findViewById(R.id.returnPkgBt);


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
        returnPkgBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), ReturnPackage.class);
                startActivity(I);

            }

        });

    }
}
