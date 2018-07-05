package com.example.chaimovy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Web3j web3 = Web3jFactory.build(new HttpService("http://10.0.2.2:7545"));
        TextView displayText = findViewById(R.id.editText);
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();

            displayText.setText(clientVersion);


        }
        catch(ExecutionException e)
        {
            displayText.setText(e.toString());
        }
        catch(InterruptedException e)
        {
            displayText.setText(e.toString());
        }

    }
}
