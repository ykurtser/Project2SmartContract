package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivityBuyerSeller extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_buyer_seller);
        Button signInBt = findViewById(R.id.signInBt);
        final TextView PublicAddText = findViewById(R.id.PublicAddText);
        final TextView PrivateKeyText = findViewById(R.id.PrivateKeyText);
        signInBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String addr = PublicAddText.getText().toString();
                String key = PrivateKeyText.getText().toString();
                Intent I = new Intent(getApplicationContext(), ActionMenu.class);
                I.putExtra("address", "0x5E46aef5B1ce6C86aF9B27B10f011d4A2348D1ec");  //TODO return to given addr
                I.putExtra("key", "8325d59384506fa3f5b6d92ecee138b7f209d4c1e1b0a9b266df2bc6d0fc6564"); //todo return to given key
                startActivity(I);

            }

        });

    }






}

