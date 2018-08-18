package com.example.chaimovy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SendFunds extends Web3Activity {
    TextView addrTxt, statusTxt, accountDetailsTxt;
    EditText ammountEtherTxt;
    Button sendFunds;
    boolean isCarrier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_funds);
        initViews();

        SharedPreferences SharedPref = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String WhoAmI = SharedPref.getString("WhoAmI","");
        if (WhoAmI.equals("BuyerSeller")) isCarrier = false;
        else isCarrier = true;

        String pkgAddr = getIntent().getStringExtra("addr");
        addrTxt.setText(pkgAddr);

        try {
            String balanceAddr;
            String toPrint;
            if (isCarrier) {
                balanceAddr = carrierAddr;
                toPrint = "Carrier contract: \n";
            }
            else  {
                balanceAddr = myAddr;
                toPrint = "Logged in to account:";
            }

            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(balanceAddr, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            String balanceW = ethGetBalance.getBalance().toString();
            String balanceE = Convert.fromWei(balanceW,Convert.Unit.ETHER).toString();
            accountDetailsTxt.setText(toPrint + myAddr + "\nBalance [ETHER]: \n" + balanceE);
        } catch (Exception e){
            accountDetailsTxt.setText("Couldn't get account balance");
        }


        sendFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendFundsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                /*
                try {
                    /*
                    BigDecimal ammountWei = Convert.toWei(ammountEtherTxt.getText().toString(), Convert.Unit.ETHER);

                    TransactionReceipt transactionReceipt = Transfer.sendFunds(
                            web3, myCred, addrTxt.getText().toString(),
                            ammountWei, Convert.Unit.ETHER).send();


                    //Build raw transaction

                    EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                            myCred.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
                    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                    RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                            nonce, gasPrice, gasLimit, addrTxt.getText().toString(),ammountWei.toBigInteger());
                    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, myCred);
                    String hexValue = Numeric.toHexString(signedMessage);

                    EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();

                    statusTxt.setText("Funds Transfered");

                }
                catch (Exception e){
                    statusTxt.setText("Oops, couldn't send funds: " + e.getMessage());
                }
                */
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

    class SendFundsTask extends AsyncTask<Void, Void, TransactionReceipt> {

        private View loadingLayout;

        String pkgAddr;
        BigDecimal ammountWei;
        EthSendTransaction tx;
        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);

            pkgAddr = addrTxt.getText().toString();
            ammountWei = Convert.toWei(ammountEtherTxt.getText().toString(), Convert.Unit.ETHER);

        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {


            try {
                if (isCarrier){
                    P2Carrier carrierCont = P2Carrier.load(carrierAddr,web3,myCred,gasPrice,gasLimit);
                    if(!carrierCont.getOwner().send().equals(myAddr)){
                        throw new Exception("logged in account is not the contract owner.");
                    }
                    carrierCont.sendFundsToPackage(pkgAddr,ammountWei.toBigInteger()).send();
                }
                else {

                    EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                            myAddr, DefaultBlockParameterName.LATEST).send();
                    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                    RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                            nonce, gasPrice, gasLimit, pkgAddr,ammountWei.toBigInteger());
                    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, myCred);
                    String hexValue = Numeric.toHexString(signedMessage);
                    tx = web3.ethSendRawTransaction(hexValue).send();
                }

            }
            catch (Exception e){
                exc = new Exception("Oops, couldn't send funds: " + e.getMessage());
            }
            catch (Error r){
                exc = new Exception(exc.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(TransactionReceipt txRecp) {
            loadingLayout.setVisibility(View.GONE);
            if (exc!=null) {
                statusTxt.setText(exc.getMessage());
            }
            else{
                String toPrint="";
                if (tx!=null){
                    toPrint = "has error: " + tx.hasError() +  "\ntx result: " + tx.getResult() +"\nsent value: " +ammountWei.toString();
                }
                statusTxt.setText("tx info: has error:" + toPrint);
            }
        }

    }
}
