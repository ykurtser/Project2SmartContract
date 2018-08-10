// CODING: TODO
// DESING: TODO

package com.example.chaimovy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaimovy.P2Package;
import com.example.chaimovy.P2PackageManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyPackages extends Web3Activity {

    Spinner pkgsSpinner;
    Spinner  pkgTrajectory;

    Button showPkgBt;
    Button   addPkgBt;

    TextView pkgSellerAddr;
    TextView pkgCarrierAddr;
    TextView pkgBuyerAddr;
    TextView pkgDispResolvAddr;
    TextView pkgSellerLeftToPay;
    TextView pkgBuyerLeftToPay;
    TextView pkgCarrierLeftToPay;
    TextView pkgShippingFee;
    TextView pkgMerchVal;
    TextView pkgState;
    TextView debugText;
    TextView pkgNumber;

    P2PackageManager pMan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_packages);

        try {
            initViews();
            pMan = P2PackageManager.load(getString(R.string.packageManagerAddrRopsten), web3, myCred, gasPrice, gasLimit);

            debugText.setText("addr: " + myAddr + " key: " + myKey);

            fillSpinner();

            showPkgBt.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    String chosenPkg = pkgsSpinner.getSelectedItem().toString();
                    SharedPreferences SharedPrefpkgInfo = getSharedPreferences(chosenPkg, Context.MODE_PRIVATE);

                    String SellerAddr = SharedPrefpkgInfo.getString("SellerAddr", "");
                    String CarrierAddr = SharedPrefpkgInfo.getString("CarrierAddr", "");
                    String BuyerAddr = SharedPrefpkgInfo.getString("BuyerAddr", "");
                    String DispResolvAddr = SharedPrefpkgInfo.getString("DispResolvAddr", "");
                    String ShippingFee = SharedPrefpkgInfo.getString("ShippingFee", "");
                    String MerchVal = SharedPrefpkgInfo.getString("MerchVal", "");

                    pkgSellerAddr.setText(SellerAddr);
                    pkgCarrierAddr.setText(CarrierAddr);
                    pkgBuyerAddr.setText(BuyerAddr);
                    pkgDispResolvAddr.setText(DispResolvAddr);
                    pkgShippingFee.setText(ShippingFee);
                    pkgMerchVal.setText(MerchVal);

                    try {
                        P2Package pkg = P2Package.load(chosenPkg, web3, myCred, gasPrice, gasLimit);

                        String SellerLeftToPay = pkg.getAmmountSeller().toString();
                        String BuyerLeftToPay = pkg.getAmmountBuyer().toString();
                        String CarrierLeftToPay = pkg.getAmmountCarrier().toString();
                        String State = getStateString(Integer.parseInt(pkg.getState().toString()));

                        //pkgTrajectory = pkg.; TODO deploy contract with get traj.

                        pkgSellerLeftToPay.setText(SellerLeftToPay);
                        pkgBuyerLeftToPay.setText(BuyerLeftToPay);
                        pkgCarrierLeftToPay.setText(CarrierLeftToPay);
                        pkgState.setText(State);
                    } catch (Error e) {
                        pkgState.setText("Delivered");
                    }
                }
            });

            addPkgBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String pkgAddress = pkgNumber.getText().toString();

                    try {
                        P2Package pkg = P2Package.load(pkgAddress, web3, myCred, gasPrice, gasLimit);

                        SharedPreferences.Editor pkgInfoEditor = getSharedPreferences(pkgAddress, Context.MODE_PRIVATE).edit();

                        String sellerAddr = pkg.getSeller().toString();
                        String carrierAddr = pkg.getCarrier().toString();
                        String buyerAddr = pkg.getBuyer().toString();
                        String dispResolvAddr = pkg.getDisputeResolver().toString();
                        String shippingFee = pkg.getShippingFee().toString();
                        String merchVal = pkg.getMerchVal().toString();

                        pkgInfoEditor.putString("sellerAddr", sellerAddr);
                        pkgInfoEditor.putString("carrierAddr", carrierAddr);
                        pkgInfoEditor.putString("buyerAddr", buyerAddr);
                        pkgInfoEditor.putString("dispResolvAddr", dispResolvAddr);
                        pkgInfoEditor.putString("shippingFee", shippingFee);
                        pkgInfoEditor.putString("merchVal", merchVal);

                        SharedPreferences SharedPrefPkgs = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);
                        SharedPreferences.Editor pkgsEditor = SharedPrefPkgs.edit();

                        Set<String> pkgs = Collections.emptySet();
                        pkgs = SharedPrefPkgs.getStringSet(myAddr, pkgs);
                        pkgs.add(pkgAddress);
                        pkgsEditor.putStringSet(myAddr, pkgs);
                        pkgsEditor.apply();
                    } catch (Error e) {
                        Toast toast = Toast.makeText(getBaseContext(), "couldn't load pkg with that addr", Toast.LENGTH_LONG);
                    }
                }
            });

        }
        catch (Error e){
            debugText = findViewById(R.id.DebugMyPkgsText);
            debugText.setText(e.getMessage());
        }

    }

    private void initViews() {
        pkgsSpinner         = findViewById(R.id.pkgsSpinner);
        pkgTrajectory       = findViewById(R.id.pkgsSpinner);

        showPkgBt           = findViewById(R.id.showPkgBt);
        addPkgBt              = findViewById(R.id.AddPkgBt);

        pkgSellerAddr       = findViewById(R.id.PKGSellerAddrText);
        pkgCarrierAddr      = findViewById(R.id.PKGCarrierAddrText);
        pkgBuyerAddr        = findViewById(R.id.PKGBuyerAddrText);
        pkgDispResolvAddr   = findViewById(R.id.PKGDispResAddrText);
        pkgSellerLeftToPay  = findViewById(R.id.PKGSellerLeftToPayText);
        pkgBuyerLeftToPay   = findViewById(R.id.PKGBuyerLeftToPayText);
        pkgCarrierLeftToPay = findViewById(R.id.PKGCarrierLeftToPayText);
        pkgShippingFee      = findViewById(R.id.PKGShippingFeeText);
        pkgMerchVal         = findViewById(R.id.PKGMerchValText);
        pkgState            = findViewById(R.id.PKGStateText);
        pkgNumber           = findViewById(R.id.MyPkgsAddrText);
        debugText           = findViewById(R.id.DebugMyPkgsText);


    }

    private String getStateString(Integer state) {
        switch (state) {
            case 0: return "Waiting for stakes in";
            case 1: return "On the way to buyer";
            case 2: return "on the way back to seller";
            case 3: return "under dispute";
        }
        return "";
    }

    protected void fillSpinner(){
        SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);

        Set<String> pkgs = new HashSet<>();
        pkgs = SharedPref.getStringSet(myAddr,pkgs);
        String pkgsList[] = new String[pkgs.size()];

        pkgs.toArray();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,pkgsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pkgsSpinner.setAdapter(dataAdapter);

    }




}
