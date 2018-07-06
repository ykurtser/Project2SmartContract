package com.example.chaimovy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivityDeliveryCompany extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_delivery_company);
        Button signInBt = findViewById(R.id.carrierSignInBt);
        Button createNewDeliveryCompanyBt = findViewById(R.id.createNewDeliveryCompanyBt);
        final TextView CarrierPublicAddrText = findViewById(R.id.CarrierPublicAddrText);
        final TextView PrivateKeyText = findViewById(R.id.CarrierKeyText);
        final TextView CompanyAddrText = findViewById(R.id.CompanyAddrText);
        signInBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String UserAddr = CarrierPublicAddrText.getText().toString();
                String key = PrivateKeyText.getText().toString();
                String CompanyAddr = CompanyAddrText.getText().toString();
                Intent I = new Intent(getApplicationContext(), CarrierActionMenu.class);
                I.putExtra("address", UserAddr);
                I.putExtra("key", key);
                I.putExtra("companyAddress", CompanyAddr);
                startActivity(I);

            }

        });
        createNewDeliveryCompanyBt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String UserAddr = CarrierPublicAddrText.getText().toString();
                String key = PrivateKeyText.getText().toString();
                Intent I = new Intent(getApplicationContext(), CarrierActionMenu.class);
                I.putExtra("address", UserAddr);
                I.putExtra("key", key);
                startActivity(I);

            }

        });

    }

}





