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
        BigDecimal ammountEther;

        Exception exc;

        @Override
        protected void onPreExecute() {
            loadingLayout = findViewById(R.id.loadingLayout);
            loadingLayout.setVisibility(View.VISIBLE);

            pkgAddr = addrTxt.getText().toString();
            ammountEther = new BigDecimal(ammountEtherTxt.getText().toString());
        }

        @Override
        protected TransactionReceipt doInBackground(Void... voids) {


            try {
                if (isCarrier){
                    P2Carrier carrierCont = P2Carrier.load(carrierAddr,web3,myCred,gasPrice,gasLimit);
                    if(!carrierCont.getOwner().send().equals(myAddr)){
                        throw new Exception("logged in account is not the contract owner.");
                    }
                    carrierCont.sendFundsToPackage(pkgAddr,ammountEther.toBigInteger()).send();
                }
                else {
                    Transfer.sendFunds(web3, myCred, pkgAddr, ammountEther, Convert.Unit.ETHER).send();
                    EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                            myCred.getAddress(), DefaultBlockParameterName.LATEST).send();
                    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
                    RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                            nonce, gasPrice, gasLimit, pkgAddr, ammountEther.toBigInteger());
                    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, myCred);
                    String hexValue = Numeric.toHexString(signedMessage);
                    web3.ethSendRawTransaction(hexValue).send();
                }

            }
            catch (Exception e){
                exc = new Exception("Oops, couldn't send funds: " + e.getMessage());
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
                statusTxt.setText("Funds Transfered");
            }
        }

    }
}
