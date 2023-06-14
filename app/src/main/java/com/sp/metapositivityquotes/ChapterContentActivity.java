package com.sp.metapositivityquotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.*;

import androidx.appcompat.app.AppCompatActivity;

public class ChapterContentActivity extends AppCompatActivity implements View.OnLongClickListener {

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    private TextView tvChapterTitle;
    private TextView tvChapterDescription;
    private TextView tvChapterContent;
    String chapterTitle, chapterDescription, chapterContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_content);
        tvChapterTitle = findViewById(R.id.tvChapterTitle);
        tvChapterDescription = findViewById(R.id.tvChapterDescription);
        tvChapterContent = findViewById(R.id.tvChapterContent);
        chapterTitle = getIntent().getStringExtra("CHAPTER_TITLE");
        chapterDescription = getIntent().getStringExtra("CHAPTER_DESC");
        chapterContent = getIntent().getStringExtra("CHAPTER_CONTENT");
        tvChapterTitle.setText(chapterTitle);
        tvChapterDescription.setText(chapterDescription);
        tvChapterContent.setText(chapterContent);

        tvChapterContent.setOnLongClickListener(this);

        //Ads
        mInterstitialAd = new InterstitialAd(this, getString(R.string.fb_interstitial));
        mAdView = new AdView(this, getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(mAdView);

        // Request an ad
        mAdView.loadAd();

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        mInterstitialAd.loadAd();

//        if (mInterstitialAd.isAdLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            return;
//        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.tvChapterContent:
                String chapterTitle = getIntent().getStringExtra("CHAPTER_CONTENT");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", chapterTitle);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(ChapterContentActivity.this, "Copied to Clipboard", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //Facebook
        if (mInterstitialAd.isAdLoaded()) {
            mInterstitialAd.show();
        } else {
            super.onBackPressed();
        }

    }
}
