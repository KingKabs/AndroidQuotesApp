package com.sp.metapositivityquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.sp.metapositivityquotes.other.Prefs;

import java.util.ArrayList;
import java.util.List;

public class PurchasesActivity extends AppCompatActivity {
    BillingClient billingClient;
    TextView clicks;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11;
    Prefs prefs;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        prefs = new Prefs(this);

        initViews();


        billingClient = BillingClient.newBuilder(getApplicationContext())
                .setListener((billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                        for (Purchase purchase : list) {
                            Log.d("TestA2d", "" + list);
                            verifyPayment(purchase);
                        }
                    }

                })
                .enablePendingPurchases()
                .build();

        connectGooglePlayBilling();
    }

    private void initViews() {

        clicks = findViewById(R.id.clicks);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_10 = findViewById(R.id.btn_10);
        btn_11 = findViewById(R.id.btn_11);

        clicks.setText("You have " + prefs.getInt("clicks", 0) + " click(s)");
    }

    void connectGooglePlayBilling() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                connectGooglePlayBilling();
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    getProducts();
                    Log.d("BILLING", "onBillingSetupFinished");
                }

            }
        });
    }


    void getProducts() {
        List<String> products = new ArrayList<>();
        products.add("purchase");
        products.add("purchase1");
        products.add("purchase2");
        products.add("purchase3");
        products.add("purchase4");
        products.add("purchase5");
        products.add("purchase6");
        products.add("purchase7");
        products.add("purchase8");
        products.add("purchase9");
        products.add("purchase10");
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(products).setType(BillingClient.SkuType.INAPP);

        Log.d("BILLING", "getProducts");

        billingClient.querySkuDetailsAsync(params.build(), (billingResult, list) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {

                Log.d("BILLING", "" + billingResult.toString());

                for (SkuDetails skuDetails : list) {
                    if (skuDetails.getSku().equals("purchase")) {
                        btn_1.setText("Support Option 1 (" + skuDetails.getPrice() + ")");
                        btn_1.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase1")) {
                        btn_2.setText("Support Option 2 (" + skuDetails.getPrice() + ")");
                        btn_2.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase2")) {
                        btn_3.setText("Support Option 3 (" + skuDetails.getPrice() + ")");
                        btn_3.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase3")) {
                        btn_4.setText("Support Option 4 (" + skuDetails.getPrice() + ")");
                        btn_4.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase4")) {
                        btn_5.setText("Support Option 5 (" + skuDetails.getPrice() + ")");
                        btn_5.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase5")) {
                        btn_6.setText("Support Option 6 (" + skuDetails.getPrice() + ")");
                        btn_6.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase6")) {
                        btn_7.setText("Support Option 7 (" + skuDetails.getPrice() + ")");
                        btn_7.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase7")) {
                        btn_8.setText("Support Option 8 (" + skuDetails.getPrice() + ")");
                        btn_8.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase8")) {
                        btn_9.setText("Support Option 9 (" + skuDetails.getPrice() + ")");
                        btn_9.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase9")) {
                        btn_10.setText("Support Option 10 (" + skuDetails.getPrice() + ")");
                        btn_10.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    } else if (skuDetails.getSku().equals("purchase10")) {
                        btn_11.setText("Support Option 11 (" + skuDetails.getPrice() + ")");
                        btn_11.setOnClickListener(view -> {
                            launchPurchaseFlow(skuDetails);
                        });
                    }
                }

            }
        });

    }

    void launchPurchaseFlow(SkuDetails skuDetails) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();
        billingClient.launchBillingFlow(this, billingFlowParams);
    }

    void verifyPayment(Purchase purchase) {


        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();

        billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), (billingResult, s) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Log.d("TestA2d", "Item consumed");
                Toast.makeText(PurchasesActivity.this, "Item Consumed", Toast.LENGTH_SHORT).show();

                if (purchase.getSkus().get(0).equals("purchase")) {
                    updateClicks(5);
                } else if (purchase.getSkus().get(0).equals("purchase1")) {
                    updateClicks(10);
                } else if (purchase.getSkus().get(0).equals("purchase2")) {
                    updateClicks(15);
                } else if (purchase.getSkus().get(0).equals("purchase3")) {
                    updateClicks(20);
                } else if (purchase.getSkus().get(0).equals("purchase4")) {
                    updateClicks(25);
                } else if (purchase.getSkus().get(0).equals("purchase5")) {
                    updateClicks(30);
                } else if (purchase.getSkus().get(0).equals("purchase6")) {
                    updateClicks(35);
                } else if (purchase.getSkus().get(0).equals("purchase7")) {
                    updateClicks(40);
                } else if (purchase.getSkus().get(0).equals("purchase8")) {
                    updateClicks(45);
                } else if (purchase.getSkus().get(0).equals("purchase9")) {
                    updateClicks(50);
                } else if (purchase.getSkus().get(0).equals("purchase10")) {
                    updateClicks(55);
                }

            }
        });
    }

    @SuppressLint("SetTextI18n")
    void updateClicks(int v) {
        prefs.setInt("clicks", v);
        //clicks.setText("You have " + prefs.getInt("clicks", 0) + " click(s)");
        clicks.setText("Thank You for your support!");
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    protected void onResume() {
        super.onResume();
        //clicks.setText("You have " + prefs.getInt("clicks", 0) + " click(s)");
        clicks.setText("Thank You for your support!");
        billingClient.queryPurchasesAsync(
                BillingClient.SkuType.INAPP,
                (billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                verifyPayment(purchase);
                            }
                        }
                    }
                }
        );
    }
}