package com.example.chaimovy.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SendFunds extends Web3Activity {
    TextView addrTxt, statusTxt, accountDetailsTxt;
    EditText ammountEtherTxt;
    Button sendFunds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_funds);
        initViews();

        String pkgAddr = getIntent().getStringExtra("addr");
        addrTxt.setText(pkgAddr);

        try {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(myAddr, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            String balanceW = ethGetBalance.getBalance().toString();
            String balanceE = Convert.fromWei(balanceW,Convert.Unit.ETHER).toString();
            accountDetailsTxt.setText("Logged in to account: \n" + myAddr + "\nBalance [ETHER]: \n" + balanceE);
        } catch (Exception e){
            accountDetailsTxt.setText("Couldn't get account balance");
        }


        sendFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BigDecimal ammountWei = new BigDecimal(ammountEtherTxt.getText().toString());
                    TransactionReceipt transactionReceipt = Transfer.sendFunds(
                            web3, myCred, addrTxt.getText().toString(),
                            ammountWei, Convert.Unit.ETHER).send();
                    statusTxt.setText("Funds Transferes");
                }
                catch (Exception e){
                    statusTxt.setText("Oops, couldn't send funds: " + e.getMessage());
                }
            }
        });
        
    }

    private void initViews() {
        addrTxt = (TextView) findViewById(R.id.sendFundsAddress);
        accountDetailsTxt = (TextView) findViewById(R.id.sendFundsAccountDetailsTxt);
        statusTxt = (TextView) findViewById(R.id.sendFundStatusText);
        ammountEtherTxt = (EditText) findViewById(R.id.sendFundsAmmountEther);
        sendFunds = (Button) findViewById(R.id.sendFundsBt);
    }
}
