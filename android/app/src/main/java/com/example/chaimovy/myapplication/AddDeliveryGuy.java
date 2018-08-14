//CODING TODO
//DESIGN TODO

package com.example.chaimovy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

//import androidx.appcompat.app.AppCompatActivity;

public class AddDeliveryGuy extends Web3Activity {

    Button addCarrierBt;
    TextView debugTxt, addrTxt;
    String managerAddr;
    boolean isManager = false;
    P2Carrier carrierCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_guy);

        initViews();

        try {
            carrierCompany = P2Carrier.load(carrierAddr, web3, myCred, gasPrice, gasLimit);
            managerAddr = carrierCompany.getOwner().send();
        } catch (Exception e){
            managerAddr="";
        }

        if (!managerAddr.equals(myAddr)){
            debugTxt.setText("You are not The Carrier company manager, can't add carriers");
        } else{
            isManager = true;
        }

        addCarrierBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isManager) return;
                try{
                    String Carrier2Add = addrTxt.getText().toString();
                    carrierCompany.addDeliveryStation(Carrier2Add);
                    debugTxt.setText("Carrier " + Carrier2Add + "\nadded");
                }
                catch (Exception e){
                    debugTxt.setText("Couldn't add Carrier: " + e.getMessage());
                }
            }
        });

    }

    private void initViews() {
        addCarrierBt = (Button) findViewById(R.id.addDeliveryGuyBt);
        debugTxt = (TextView) findViewById(R.id.addDeliveryGuyDebugText);
        addrTxt = (TextView) findViewById(R.id.addDeliveryGuyAddrText);
    }
}
