package com.example.chaimovy.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
public class Web3Activity extends AppCompatActivity {

    String myAddr;
    String myKey;
    String carrierAddr;
    org.web3j.crypto.Credentials myCred;
    Web3j web3;
    BigInteger gasPrice;
    BigInteger gasLimit;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Policy change to allow async calls
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            myKey = SharedPref.getString("key", "");
            myAddr = SharedPref.getString("addr", "");
            carrierAddr = SharedPref.getString("carrier", "");

            myCred = Credentials.create(myKey);
            gasPrice = new BigInteger(getResources().getString(R.string.gasPrice));
            gasLimit = new BigInteger(getResources().getString(R.string.gasLimit));
            web3 = Web3jFactory.build(new HttpService(getResources().getString(R.string.web3HostRopsten)));
            //EthBlock lastBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
            //List<EthBlock.TransactionResult> tranList = lastBlock.getBlock().getTransactions();
            //EthBlock.TransactionResult tran = tranList.get(0);


        }
        catch (Exception e){

        }

    }
}



