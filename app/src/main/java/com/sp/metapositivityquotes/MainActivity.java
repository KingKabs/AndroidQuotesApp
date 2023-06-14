package com.sp.metapositivityquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.ads.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    private Button btnCategory1, btnCategory2, btnCategory3, btnCategory4, btnCategory5, btnCategory6, btnCategory7, btnCategory8, btnCategory9, btnCategory10, btnCategory11, btnCategory12, btnCategory13;
    //Button btnInAppPurchases;
    private String category1QuotesURL = "";
    private String category2QuotesURL = "";
    private String category3QuotesURL = "";
    private String category4QuotesURL = "";
    //String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCategory1 = findViewById(R.id.btnCategory1);
        btnCategory1.setOnClickListener(this);
        btnCategory2 = findViewById(R.id.btnCategory2);
        btnCategory2.setOnClickListener(this);
        btnCategory3 = findViewById(R.id.btnCategory3);
        btnCategory3.setOnClickListener(this);
        btnCategory4 = findViewById(R.id.btnCategory4);
        btnCategory4.setOnClickListener(this);
        btnCategory5 = findViewById(R.id.btnCategory5);
        btnCategory5.setOnClickListener(this);
        btnCategory6 = findViewById(R.id.btnCategory6);
        btnCategory6.setOnClickListener(this);
        btnCategory7 = findViewById(R.id.btnCategory7);
        btnCategory7.setOnClickListener(this);
        btnCategory8 = findViewById(R.id.btnCategory8);
        btnCategory8.setOnClickListener(this);
        btnCategory9 = findViewById(R.id.btnCategory9);
        btnCategory9.setOnClickListener(this);
        btnCategory10 = findViewById(R.id.btnCategory10);
        btnCategory10.setOnClickListener(this);
        btnCategory11 = findViewById(R.id.btnCategory11);
        btnCategory11.setOnClickListener(this);
        btnCategory12 = findViewById(R.id.btnCategory12);
        btnCategory12.setOnClickListener(this);
        btnCategory13 = findViewById(R.id.btnCategory13);
        btnCategory13.setOnClickListener(this);

        //btnInAppPurchases = findViewById(R.id.btnInAppPurchases);
        //btnInAppPurchases.setOnClickListener(this);


        AudienceNetworkAds.initialize(this);

        //Ads
//        mInterstitialAd = new InterstitialAd(this,  getString(R.string.fb_interstitial));
        mAdView = new AdView(this, getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(mAdView);

        // Request an ad
        mAdView.loadAd();

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
//        mInterstitialAd.loadAd();

//        if (mInterstitialAd.isAdLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            return;
//        }


        /*ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.quotes_category_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCategory1:
                Intent goToQuotesActivityC1 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC1.putExtra("CATEGORY", "GENERAL LIFE");
                goToQuotesActivityC1.putExtra("QUOTES_URL", category1QuotesURL);
                startActivity(goToQuotesActivityC1);
                break;

            case R.id.btnCategory2:
                Intent goToQuotesActivityC2 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC2.putExtra("CATEGORY", "LOVE");
                goToQuotesActivityC2.putExtra("QUOTES_URL", category2QuotesURL);
                startActivity(goToQuotesActivityC2);
                break;

            case R.id.btnCategory3:
                Intent goToQuotesActivityC3 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC3.putExtra("CATEGORY", "ENTREPRENEURSHIP");
                goToQuotesActivityC3.putExtra("QUOTES_URL", category3QuotesURL);
                startActivity(goToQuotesActivityC3);
                break;

            case R.id.btnCategory4:
                Intent goToQuotesActivityC4 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC4.putExtra("CATEGORY", "SUCCESS");
                goToQuotesActivityC4.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC4);
                break;

            case R.id.btnCategory5:
                Intent goToQuotesActivityC5 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC5.putExtra("CATEGORY", "INSPIRATION");
                goToQuotesActivityC5.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC5);
                break;

            case R.id.btnCategory6:
                Intent goToQuotesActivityC6 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC6.putExtra("CATEGORY", "MOTIVATION");
                goToQuotesActivityC6.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC6);
                break;

            case R.id.btnCategory7:
                Intent goToQuotesActivityC7 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC7.putExtra("CATEGORY", "WISDOM");
                goToQuotesActivityC7.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC7);
                break;

            case R.id.btnCategory8:
                Intent goToQuotesActivityC8 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC8.putExtra("CATEGORY", "ATTITUDE");
                goToQuotesActivityC8.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC8);
                break;

            case R.id.btnCategory9:
                Intent goToQuotesActivityC9 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC9.putExtra("CATEGORY", "IMAGINATION");
                goToQuotesActivityC9.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC9);
                break;

            case R.id.btnCategory10:
                Intent goToQuotesActivityC10 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC10.putExtra("CATEGORY", "HAPPINESS");
                goToQuotesActivityC10.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC10);
                break;

            case R.id.btnCategory11:
                Intent goToQuotesActivityC11 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC11.putExtra("CATEGORY", "COURAGE");
                goToQuotesActivityC11.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC11);
                break;

            case R.id.btnCategory12:
                Intent goToQuotesActivityC12 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC12.putExtra("CATEGORY", "SCIENCE");
                goToQuotesActivityC12.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC12);
                break;

            case R.id.btnCategory13:
                Intent goToQuotesActivityC13 = new Intent(MainActivity.this, QuotesActivity.class);
                goToQuotesActivityC13.putExtra("CATEGORY", "NATURE");
                goToQuotesActivityC13.putExtra("QUOTES_URL", category4QuotesURL);
                startActivity(goToQuotesActivityC13);
                break;

            /*case R.id.btnInAppPurchases:
                Intent goToInAppPurchases = new Intent(MainActivity.this, PurchasesActivity.class);
                startActivity(goToInAppPurchases);
                break;*/
        }
    }
}
