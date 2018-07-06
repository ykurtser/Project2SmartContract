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
                I.putExtra("address", addr);
                I.putExtra("key", key);
                startActivity(I);

            }

        });

    }






}

