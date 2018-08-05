package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences SharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SharedPref.edit();

        signInBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.putString("addr",PublicAddText.getText().toString());
                editor.putString("key",PrivateKeyText.getText().toString());

                Intent I = new Intent(getApplicationContext(), ActionMenu.class);

                startActivity(I);

            }

        });

    }






}

