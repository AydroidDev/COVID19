package com.Corona.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Corona.Virus.ModelClasses.Prevention;
import com.Corona.Virus.R;
import com.squareup.picasso.Picasso;

public class DetailedPrevention extends AppCompatActivity {
ImageView detailedPreventionImageViewRank,detailedRecyclerItemPreventionImage;
TextView detailedPreventionTitle,detailedRecyclerItemPreventionDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_prevention);
        detailedPreventionImageViewRank=findViewById(R.id.detailedPreventionImageViewRank);
        detailedRecyclerItemPreventionImage=findViewById(R.id.detailedRecyclerItemPreventionImage);
        detailedPreventionTitle=findViewById(R.id.detailedPreventionTitle);
        detailedRecyclerItemPreventionDescription=findViewById(R.id.detailedRecyclerItemPreventionDescription);
        Prevention prevention=new Prevention();
        prevention= (Prevention) getIntent().getSerializableExtra("obj");
        assert prevention != null;
        Picasso.get().load(prevention.getImage()).into(detailedRecyclerItemPreventionImage);
        //Picasso.get().load(prevention.getRank()).into(detailedPreventionImageViewRank);
        detailedPreventionTitle.setText(prevention.getTitle());
        detailedRecyclerItemPreventionDescription.setText(prevention.getPrevention());
    }
}
