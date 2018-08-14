// CODING: DONE.
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

public class LoginActivityBuyerSeller extends AppCompatActivity {

    Button signInBt, lastUsedBt;
    TextView PublicAddText, PrivateKeyText;
    String WhoAmI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_buyer_seller);

        initViews();

        final SharedPreferences SharedPrefuserInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SharedPrefuserInfo.edit();
        WhoAmI = SharedPrefuserInfo.getString("WhoAmI","");


        signInBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.putString("addr",PublicAddText.getText().toString());
                editor.putString("key",PrivateKeyText.getText().toString());
                editor.apply();

                Intent I;
                if (WhoAmI.equals("DispRes")) I=new Intent(getApplicationContext(), SolveDispute.class);
                else I = new Intent(getApplicationContext(), ActionMenu.class);

                startActivity(I);
            }

        });

        lastUsedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lastAddr = SharedPrefuserInfo.getString("addr", getResources().getString(R.string.debugAddr));
                String lastKey = SharedPrefuserInfo.getString("key", getResources().getString(R.string.debugKey));

                PublicAddText.setText(lastAddr);
                PrivateKeyText.setText(lastKey);
            }
        });

    }

    private void initViews() {
        signInBt = (Button) findViewById(R.id.loginBuySellsignInBt);
        lastUsedBt = (Button) findViewById(R.id.loginBuySellLastUsedBt);
        PublicAddText = (TextView) findViewById(R.id.loginBuySellPublicAddrText);
        PrivateKeyText = (TextView) findViewById(R.id.loginBuySellPrivateKeyText);
    }


}

