package com.example.chaimovy.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;

import androidx.appcompat.app.AppCompatActivity;

public class Web3Activity extends AppCompatActivity {

    String myAddr;
    String myKey;
    org.web3j.crypto.Credentials myCred;
    Web3j web3;
    BigInteger gasPrice;
    BigInteger gasLimit;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Policy change to allow async calls
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        myKey=SharedPref.getString("key","");
        myAddr=SharedPref.getString("addr","");

        myCred= Credentials.create(myKey);
        gasPrice= Convert.toWei("0.00000001", Convert.Unit.ETHER).toBigInteger();
        gasLimit = new BigInteger("4999999");
        web3 = Web3jFactory.build(new HttpService(getResources().getString(R.string.web3HostRopsten)));


    }
}



