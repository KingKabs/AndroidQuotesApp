package com.sp.metapositivityquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.sp.metapositivityquotes.app.AppController;
import com.sp.metapositivityquotes.other.BookChaptersDataModel;
import com.sp.metapositivityquotes.other.BookChaptersRVAdapter;
import com.sp.metapositivityquotes.other.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.ads.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private AdView adView;

    private String urlJsonArray;
    private static String TAG = QuotesActivity.class.getSimpleName();
    private static ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    ArrayList<BookChaptersDataModel> dataModelArrayList;
    private BookChaptersRVAdapter recyclerViewAdpater;
    private TextView tvQuoteCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        recyclerView = findViewById(R.id.recyclerView);
        tvQuoteCategory = findViewById(R.id.tvQuoteCategory);
        tvQuoteCategory.setText(getIntent().getStringExtra("CATEGORY") + " QUOTES");

        //Get Book Chapters
        if (isNetworkAvailable()) {
            //getBookChaptersJsonArrayRequest();
            getQuotes();
            //Setting up Recycler OnItemTouch Listener
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(QuotesActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    BookChaptersDataModel chapter = dataModelArrayList.get(position);
                    String chapterTitle = chapter.getChapterTitle();
                    String chapterDescription = chapter.getChapterDescription();
                    String chapterContent = chapter.getChapterContent();
                    Intent goToChapterContentActivity = new Intent(QuotesActivity.this, ChapterContentActivity.class);
                    goToChapterContentActivity.putExtra("CHAPTER_TITLE", chapterTitle);
                    goToChapterContentActivity.putExtra("CHAPTER_DESC", chapterDescription);
                    goToChapterContentActivity.putExtra("CHAPTER_CONTENT", chapterContent);
                    startActivity(goToChapterContentActivity);
                    //Toast.makeText(QuotesActivity.this, chapterTitle, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClick(View view, int position) {
                    BookChaptersDataModel chapter = dataModelArrayList.get(position);
                    String chapterTitle = chapter.getChapterTitle();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", chapterTitle);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(QuotesActivity.this, "Copied to Clipboard", Toast.LENGTH_LONG).show();
                }
            }));

            //Ads
            mInterstitialAd = new InterstitialAd(this, getString(R.string.fb_interstitial));
            adView = new AdView(this, getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);

            // Find the Ad Container
            LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

            // Add the ad view to your activity layout
            adContainer.addView(adView);

            // Request an ad
            adView.loadAd();

            // For auto play video ads, it's recommended to load the ad
            // at least 30 seconds before it is shown
            mInterstitialAd.loadAd();

//        if (mInterstitialAd.isAdLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            return;
//        }

        } else {
            Toast.makeText(QuotesActivity.this, "Please connect to the internet", Toast.LENGTH_LONG).show();
        }


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void getQuotes() {
        // Showing progress dialog while fetching JSON data.
        progressDialog = new ProgressDialog(QuotesActivity.this);
        progressDialog.setMessage("Fetching Quotes...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        urlJsonArray = getIntent().getStringExtra("QUOTES_URL");
        String category = getIntent().getStringExtra("CATEGORY");
        StringRequest req = new StringRequest(Request.Method.POST, urlJsonArray,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        ArrayList<BookChaptersDataModel> tennisModelArrayList = new ArrayList<>();
                        try {
                            // Parsing json array response
                            // loop through each json object
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                BookChaptersDataModel dataModel = new BookChaptersDataModel();
                                JSONObject chapter = (JSONObject) jsonArray.get(i);
                                dataModel.setChapterID(chapter.getString("chapter_id"));
                                dataModel.setChapterTitle(chapter.getString("chapter_title"));
                                dataModel.setChapterDescription(chapter.getString("chapter_description"));
                                dataModel.setChapterContent(chapter.getString("chapter_content"));
                                dataModel.setChapterAccess(chapter.getString("access"));
                                tennisModelArrayList.add(dataModel);
                            }
                            dataModelArrayList = tennisModelArrayList;
                            recyclerViewAdpater = new BookChaptersRVAdapter(QuotesActivity.this, dataModelArrayList);
                            recyclerView.setAdapter(recyclerViewAdpater);
                            recyclerView.setLayoutManager(new LinearLayoutManager(QuotesActivity.this, LinearLayoutManager.VERTICAL, false));
                            //txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(QuotesActivity.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        //hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(QuotesActivity.this,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("category", category);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }


    @Override
    public void onBackPressed() {
        //Facebook
        if (isNetworkAvailable()) {
            if (mInterstitialAd.isAdLoaded()) {
                mInterstitialAd.show();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
