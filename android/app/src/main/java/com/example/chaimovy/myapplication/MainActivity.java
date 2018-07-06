package com.example.chaimovy.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;

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
            // send asynchronous requests to get balance
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance("0xd51d1b97A1Dab01E0eEd629A31B09D58E3ccB642", DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            BigInteger wei = ethGetBalance.getBalance();

            //displayText.setText(wei.toString());
            try
            {
            String account = web3.ethAccounts().sendAsync().get().getAccounts().get(0);

            Credentials credentials = Credentials.create(account);

                TransactionReceipt transactionReceipt = Transfer.sendFunds(
                        web3, credentials, "0xA895A52FD9b319559c17b6FeE7Cbcd10A7efC994", BigDecimal.valueOf(1.0), Convert.Unit.ETHER).send();
                displayText.setText(transactionReceipt.toString());
            }
            /*
            catch(TransactionException e)
            {
                displayText.setText(e.toString()+"TransactionException");
            }

            catch(IOException e)
            {
                displayText.setText(e.toString()+"IOexception");
            }
            */
            catch(Exception e)
            {
                displayText.setText(e.toString()+" exception");
            }


        }
        catch(ExecutionException e)
        {
            displayText.setText(e.toString()+"excecutionException");
        }
        catch(InterruptedException e)
        {
            displayText.setText(e.toString()+"intrupptedExeption");
        }


    }
}
