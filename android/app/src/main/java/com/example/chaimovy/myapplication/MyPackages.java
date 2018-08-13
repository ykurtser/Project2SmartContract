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

import org.web3j.utils.Convert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyPackages extends Web3Activity {

    Spinner pkgsSpinner;
    Spinner pkgTrajectory;

    Button showPkgBt;
    Button addPkgBt;

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

            fillSpinner();

            showPkgBt.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    String chosenPkg = pkgsSpinner.getSelectedItem().toString();
                    SharedPreferences SharedPrefpkgInfo = getSharedPreferences(chosenPkg, Context.MODE_PRIVATE);

                    String SellerAddr = SharedPrefpkgInfo.getString("sellerAddr", "");
                    String CarrierAddr = SharedPrefpkgInfo.getString("carrierAddr", "");
                    String BuyerAddr = SharedPrefpkgInfo.getString("buyerAddr", "");
                    String DispResolvAddr = SharedPrefpkgInfo.getString("dispResolvAddr", "");
                    String ShippingFeeWei = SharedPrefpkgInfo.getString("shippingFee", "");
                    String MerchValWei = SharedPrefpkgInfo.getString("merchVal", "");

                    String ShippingFeeEther = Convert.fromWei(ShippingFeeWei, Convert.Unit.ETHER).toString();
                    String MerchValEther = Convert.fromWei(MerchValWei, Convert.Unit.ETHER).toString();

                    pkgSellerAddr.setText("Seller addr: " + SellerAddr);
                    pkgCarrierAddr.setText("Carrier addr: " + CarrierAddr);
                    pkgBuyerAddr.setText("Buyer addr: " + BuyerAddr);
                    pkgDispResolvAddr.setText("Dispute resolver addr: " + DispResolvAddr);
                    pkgShippingFee.setText("Shipping fee: " + ShippingFeeEther + " Ether");
                    pkgMerchVal.setText("Merchedise value: " + MerchValEther + " Ether");

                    try {
                        P2Package pkg = P2Package.load(chosenPkg, web3, myCred, gasPrice, gasLimit);
                        String SellerLeftToPayWei = pkg.getSellerStake().send().subtract(pkg.getAmmountSeller().send()).toString();
                        String BuyerLeftToPayWei = pkg.getBuyerStake().send().subtract(pkg.getAmmountBuyer().send()).toString();
                        String CarrierLeftToPayWei = pkg.getCarrierStake().send().subtract(pkg.getAmmountCarrier().send()).toString();
                        String State = getStateString(Integer.parseInt(pkg.getState().send().toString()));

                        String SellerLeftToPayEther = Convert.fromWei(SellerLeftToPayWei, Convert.Unit.ETHER).toString();
                        String BuyerLeftToPayEther = Convert.fromWei(BuyerLeftToPayWei, Convert.Unit.ETHER).toString();
                        String CarrierLeftToPayEther = Convert.fromWei(CarrierLeftToPayWei, Convert.Unit.ETHER).toString();


                        //List<String> traj= pkg.getTrajectory().send();
                        //ArrayAdapter<String> dataAdapter = new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,traj.toArray());
                        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //pkgsSpinner.setAdapter(dataAdapter);


                        pkgSellerLeftToPay.setText("Seller to pay: " + SellerLeftToPayEther + " Ether");
                        pkgBuyerLeftToPay.setText("Buyer to pay: " + BuyerLeftToPayEther + " Ether");
                        pkgCarrierLeftToPay.setText("Carrier to pay: " + CarrierLeftToPayEther + " Ether");
                        pkgState.setText("Package state: " + State);

                    } catch (Error e) {
                        debugText.setText("Delivered");
                    } catch (Exception e) {
                        debugText.setText("Error: " + e.getMessage());
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
                        Toast.makeText(getBaseContext(), "couldn't load pkg with that addr", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Error e) {
            debugText = (TextView) findViewById(R.id.DebugMyPkgsText);
            debugText.setText(e.getMessage());
        }

    }

    private void initViews() {
        pkgsSpinner = (Spinner) findViewById(R.id.pkgsSpinner);
        pkgTrajectory = (Spinner) findViewById(R.id.trajectorySpinner);

        showPkgBt = (Button) findViewById(R.id.showPkgBt);
        addPkgBt = (Button) findViewById(R.id.AddPkgBt);

        pkgSellerAddr = (TextView) findViewById(R.id.PKGSellerAddrText);
        pkgCarrierAddr = (TextView) findViewById(R.id.PKGCarrierAddrText);
        pkgBuyerAddr = (TextView) findViewById(R.id.PKGBuyerAddrText);

        pkgDispResolvAddr = (TextView) findViewById(R.id.PKGDispResAddrText);
        pkgSellerLeftToPay = (TextView) findViewById(R.id.PKGSellerLeftToPayText);
        pkgBuyerLeftToPay = (TextView) findViewById(R.id.PKGBuyerLeftToPayText);
        pkgCarrierLeftToPay = (TextView) findViewById(R.id.PKGCarrierLeftToPayText);
        pkgShippingFee = (TextView) findViewById(R.id.PKGShippingFeeText);
        pkgMerchVal = (TextView) findViewById(R.id.PKGMerchValText);
        pkgState = (TextView) findViewById(R.id.PKGStateText);
        pkgNumber = (TextView) findViewById(R.id.MyPkgsAddrText);
        debugText = (TextView) findViewById(R.id.DebugMyPkgsText);


    }

    private String getStateString(Integer state) {
        switch (state) {
            case 0:
                return "Waiting for stakes in";
            case 1:
                return "On the way to buyer";
            case 2:
                return "on the way back to seller";
            case 3:
                return "under dispute";
        }
        return "";
    }

    protected void fillSpinner() {
        SharedPreferences SharedPref = getSharedPreferences("pkgTrek", Context.MODE_PRIVATE);

        Set<String> pkgs = new HashSet<>();
        pkgs = SharedPref.getStringSet(myAddr, pkgs);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, pkgs.toArray());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pkgsSpinner.setAdapter(dataAdapter);

    }


}
