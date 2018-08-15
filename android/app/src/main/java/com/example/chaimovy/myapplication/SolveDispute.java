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

import java.math.BigInteger;

public class SolveDispute extends Web3Activity {

    TextView addrTxt, carrierCutTxt, debugTxt;
    EditText sellerCutTxt;
    Button resolveBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_dispute);

        initViews();

        sellerCutTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sellerCutSt = sellerCutTxt.getText().toString();
                if (sellerCutSt.length() == 0){
                    carrierCutTxt.setText("");
                }
                else {
                    Integer SellerCut = Integer.valueOf(sellerCutSt);
                    Integer CarrierCut = 100 - SellerCut;
                    carrierCutTxt.setText(CarrierCut.toString());
                }
            }
        });

        resolveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    P2Package pkg = P2Package.load(addrTxt.getText().toString(),web3,myCred,gasPrice,gasLimit);

                    String dispResolverAddr = pkg.getDisputeResolver().send();
                    if (!dispResolverAddr.equals(myAddr)){
                        debugTxt.setText("You are not set as the dispute resolver fot this package");
                        return;
                    }

                    int pkgState = pkg.getState().send().intValue();
                    if (pkgState != 3) {
                        debugTxt.setText("Package is not under dispute, its state is: \n" + P2Package.getStateString(pkgState));
                        return;
                    }

                    BigInteger sellersCut = new BigInteger(sellerCutTxt.getText().toString());
                    if (sellersCut.intValue() < 0 || sellersCut.intValue() > 100){
                        debugTxt.setText("The cut must be in range [0-100] fix it and try again");
                        return;
                    }

                    pkg.resolveDispute(sellersCut).sendAsync();

                }
                catch (Exception e){
                    debugTxt.setText("Oops, something went wrong:\n" + e.getMessage());
                }
            }
        });




    }

    private void initViews() {
        addrTxt = (TextView) findViewById(R.id.reresolveDisputeAddress);
        carrierCutTxt = (TextView) findViewById(R.id.resolveDisputeCarrierCut);
        debugTxt = (TextView) findViewById(R.id.resolveDisputeDebugText);
        sellerCutTxt = (EditText) findViewById(R.id.resolveDisputeSellerCut);
        resolveBt = (Button) findViewById(R.id.resolveDisputeBt);
    }
}
