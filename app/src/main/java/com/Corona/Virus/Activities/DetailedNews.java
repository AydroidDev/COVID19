package com.Corona.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.Corona.Virus.ModelClasses.Article;
import com.Corona.Virus.ModelClasses.Example;
import com.Corona.Virus.R;
import com.Corona.Virus.Utils.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedNews extends AppCompatActivity {
TextView rankingTextNews,detailedRecyclerNewsTitle,detailedRecyclerItemNewsDescription;
CircleImageView rankingImageNews;
ImageView detailedRecyclerItemNewsImage,increaseSizeNews,decreaseSizeNews;
AdView NewsAd;
float TextSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
        rankingTextNews=findViewById(R.id.rankingTextNews);
        detailedRecyclerNewsTitle=findViewById(R.id.detailedRecyclerNewsTitle);
        detailedRecyclerItemNewsDescription=findViewById(R.id.detailedRecyclerItemNewsDescription);
        rankingImageNews=findViewById(R.id.rankingImageNews);
        detailedRecyclerItemNewsImage=findViewById(R.id.detailedRecyclerItemNewsImage);
        increaseSizeNews=findViewById(R.id.increaseSizeNews);
        decreaseSizeNews=findViewById(R.id.decreaseSizeNews);
        NewsAd=findViewById(R.id.newsAd);
        MobileAds.initialize(DetailedNews.this);
        AdRequest adRequest = new AdRequest.Builder().build();
        NewsAd.setAdSize(AdSize.BANNER);
        NewsAd.setAdUnitId("ca-app-pub-3451872773610798/3667119719");
        NewsAd.loadAd(adRequest);
        NewsAd.setAdListener(new AdListener(){
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
        Article example=new Article();
        example= (Article) getIntent().getSerializableExtra("obj");
        int rank=Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("position")));
        rankingTextNews.setText("#"+rank);
        GradientDrawable drawable = (GradientDrawable) rankingImageNews.getBackground();
        drawable.setColor(Color.parseColor(Utils.Colors[new Random().nextInt(Utils.Colors.length)]));
        detailedRecyclerNewsTitle.setText(example.getTitle());
        detailedRecyclerItemNewsDescription.setText(example.getDescription());
        Picasso.get().load(example.getUrlToImage()).into(detailedRecyclerItemNewsImage);
        TextSize =  detailedRecyclerItemNewsDescription.getTextSize();
        Log.e("Text Size: ",TextSize+"");
        increaseSizeNews.setOnClickListener(v->{
            Log.e("Clicked","Increased");
            if (TextSize <100) {
                if (TextSize == 44.0){
                    TextSize = 18;
                    Log.e("inside if", "Increased to " + TextSize);
                    detailedRecyclerItemNewsDescription.setTextSize(TextSize);
                }
                else {
                    TextSize++;
                    Log.e("inside if", "Increased to " + TextSize);
                    detailedRecyclerItemNewsDescription.setTextSize(TextSize);
                }

            }
        });

        decreaseSizeNews.setOnClickListener(v->{
            Log.e("Clicked","Decreased");
            if (TextSize>=18){
                TextSize--;
                Log.e("inside if","Decreased to "+TextSize);
                detailedRecyclerItemNewsDescription.setTextSize(TextSize);
            }else{
                TextSize=17;
            }

        });
    }
}
