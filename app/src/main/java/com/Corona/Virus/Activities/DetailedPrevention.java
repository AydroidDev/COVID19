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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedPrevention extends AppCompatActivity {
ImageView detailedRecyclerItemPreventionImage;
CircleImageView detailedPreventionImageViewRank;
TextView detailedPreventionTitle,detailedRecyclerItemPreventionDescription,ranktext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_prevention);


        detailedPreventionImageViewRank=findViewById(R.id.detailedPreventionImageViewRank);
        ranktext = findViewById(R.id.ranktext);
        detailedRecyclerItemPreventionImage=findViewById(R.id.detailedRecyclerItemPreventionImage);
        detailedPreventionTitle=findViewById(R.id.detailedPreventionTitle);
        detailedRecyclerItemPreventionDescription=findViewById(R.id.detailedRecyclerItemPreventionDescription);
        Prevention prevention=new Prevention();
        prevention= (Prevention) getIntent().getSerializableExtra("obj");
        assert prevention != null;
        String d = getIntent().getStringExtra("hexcode");
        Log.e("VAL", "onCreate: "+d);
        GradientDrawable drawable = (GradientDrawable) detailedPreventionImageViewRank.getBackground();
        drawable.setColor(Color.parseColor(d));
        this.ranktext.setText("#"+prevention.getRank());



//        Picasso.get().load(prevention.getImage()).into(detailedRecyclerItemPreventionImage);
        detailedPreventionTitle.setText(prevention.getTitle());
        detailedRecyclerItemPreventionDescription.setText(prevention.getPrevention());
    }
}
