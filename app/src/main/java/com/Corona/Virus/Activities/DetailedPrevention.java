package com.Corona.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.Corona.Virus.ModelClasses.Prevention;
import com.Corona.Virus.R;
import com.Corona.Virus.Utils.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedPrevention extends AppCompatActivity {
    private ImageView detailedRecyclerItemPreventionImage,increaseSize,decreaseSize;
    private AVLoadingIndicatorView imageProgress;
    private CircleImageView detailedPreventionImageViewRank;
    private AdView preventionAd;
    private float TextSize;
TextView detailedPreventionTitle,detailedRecyclerItemPreventionDescription,ranktext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_prevention);


        detailedPreventionImageViewRank=findViewById(R.id.detailedPreventionImageViewRank);
        ranktext = findViewById(R.id.ranktext);
        imageProgress = findViewById(R.id.imageProgress);
        imageProgress.smoothToShow();
        detailedRecyclerItemPreventionImage=findViewById(R.id.detailedRecyclerItemPreventionImage);
        detailedPreventionTitle=findViewById(R.id.detailedPreventionTitle);
        detailedRecyclerItemPreventionDescription=findViewById(R.id.detailedRecyclerItemPreventionDescription);
        increaseSize  = findViewById(R.id.increaseSize);
        decreaseSize  = findViewById(R.id.decreaseSize);
        Prevention prevention=new Prevention();
        prevention= (Prevention) getIntent().getSerializableExtra("obj");
        assert prevention != null;
        String d = getIntent().getStringExtra("hexcode");
        Log.e("VAL", "onCreate: "+d);
        GradientDrawable drawable = (GradientDrawable) detailedPreventionImageViewRank.getBackground();
        drawable.setColor(Color.parseColor(d));
        Picasso.get().load(prevention.getImage()).into(detailedRecyclerItemPreventionImage, new Callback() {
            @Override
            public void onSuccess() {
                imageProgress.smoothToHide();
            }

            @Override
            public void onError(Exception e) {
                imageProgress.smoothToHide();
            }
        });
        this.ranktext.setText("#"+prevention.getRank());
        TextSize =  detailedRecyclerItemPreventionDescription.getTextSize();
        Log.e("Text Size: ",TextSize+"");


//        Picasso.get().load(prevention.getImage()).into(detailedRecyclerItemPreventionImage);
        detailedPreventionTitle.setText(prevention.getTitle());
        detailedRecyclerItemPreventionDescription.setText(prevention.getPrevention());

        increaseSize.setOnClickListener(v->{
            Log.e("Clicked","Increased");
            if (TextSize <100) {
                if (TextSize == 44.0){
                    TextSize = 18;
                    Log.e("inside if", "Increased to " + TextSize);
                    detailedRecyclerItemPreventionDescription.setTextSize(TextSize);
                }
                else {
                    TextSize++;
                    Log.e("inside if", "Increased to " + TextSize);
                    detailedRecyclerItemPreventionDescription.setTextSize(TextSize);
                }

            }
        });

        decreaseSize.setOnClickListener(v->{
            Log.e("Clicked","Decreased");
            if (TextSize>=18){
                TextSize--;
                Log.e("inside if","Decreased to "+TextSize);
                detailedRecyclerItemPreventionDescription.setTextSize(TextSize);
            }

        });


        // AdMob Implementation

        preventionAd = findViewById(R.id.preventionAd);
        MobileAds.initialize(getApplicationContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        preventionAd.setAdSize(AdSize.BANNER);
        preventionAd.setAdUnitId("ca-app-pub-3451872773610798/1437090930");
        preventionAd.loadAd(adRequest);
        preventionAd.setAdListener(new AdListener(){


            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // Toast.makeText(getApplicationContext(), "failed"+errorCode, Toast.LENGTH_SHORT).show();
                Log.e("ADMOB ERROR",errorCode+"");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }
}
