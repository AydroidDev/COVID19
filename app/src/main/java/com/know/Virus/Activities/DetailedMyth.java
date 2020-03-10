package com.know.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.know.Virus.ModelClasses.Myths;
import com.know.Virus.R;
import com.know.Virus.Utils.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedMyth extends AppCompatActivity {
    private TextView detailedRecyclerMythTitle,detailedRecyclerItemMythDescription,rankingText;
    private ImageView detailedRecyclerItemMythImage,increaseSize,decreaseSize;
    private CircleImageView rankingImage;
    private AdView mythAd;
    private float TextSize=18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_myth);
        detailedRecyclerMythTitle=findViewById(R.id.detailedRecyclerMythTitle);
        detailedRecyclerItemMythDescription=findViewById(R.id.detailedRecyclerItemMythDescription);
        detailedRecyclerItemMythImage=findViewById(R.id.detailedRecyclerItemMythImage);
        rankingImage = findViewById(R.id.rankingImage);
        rankingText  = findViewById(R.id.rankingText);
        increaseSize  = findViewById(R.id.increaseSize);
        decreaseSize  = findViewById(R.id.decreaseSize);
        mythAd = findViewById(R.id.mythAd);

        MobileAds.initialize(getApplicationContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        mythAd.setAdSize(AdSize.BANNER);
        mythAd.setAdUnitId("ca-app-pub-3451872773610798/3667119719");
        mythAd.loadAd(adRequest);
        mythAd.setAdListener(new AdListener(){


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

        Myths myths=new Myths();
        int Rank = Integer.parseInt(getIntent().getStringExtra("position"));
        myths= (Myths) getIntent().getSerializableExtra("obj");
        assert myths != null;
        rankingText.setText("#"+Rank);
        GradientDrawable drawable = (GradientDrawable) rankingImage.getBackground();
        drawable.setColor(Color.parseColor(Utils.Colors[new Random().nextInt(Utils.Colors.length)]));
        detailedRecyclerMythTitle.setText(myths.getMyth());
        detailedRecyclerItemMythDescription.setText(myths.getReality());
        Picasso.get().load(myths.getImage()).into(detailedRecyclerItemMythImage);
        Log.e("Text Size: ",TextSize+"");

        increaseSize.setOnClickListener(v->{
            Log.e("Clicked","Increased");
            if (TextSize < 30) {
                TextSize++;
                Log.e("inside if", "Increased to " + TextSize);
                detailedRecyclerItemMythDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,TextSize);
            }
        });

        decreaseSize.setOnClickListener(v->{
            Log.e("Clicked","Decreased");
            if (TextSize > 18){
                TextSize--;
                Log.e("inside if","Decreased to "+TextSize);
                detailedRecyclerItemMythDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,TextSize);
            }

        });
    }
}
