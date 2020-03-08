package com.Corona.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Corona.Virus.ModelClasses.Myths;
import com.Corona.Virus.R;
import com.squareup.picasso.Picasso;

public class DetailedMyth extends AppCompatActivity {
TextView detailedRecyclerMythTitle,detailedRecyclerItemMythDescription;
ImageView detailedRecyclerItemMythImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_myth);
        detailedRecyclerMythTitle=findViewById(R.id.detailedRecyclerMythTitle);
        detailedRecyclerItemMythDescription=findViewById(R.id.detailedRecyclerItemMythDescription);
        detailedRecyclerItemMythImage=findViewById(R.id.detailedRecyclerItemMythImage);
        Myths myths=new Myths();
        myths= (Myths) getIntent().getSerializableExtra("obj");
        assert myths != null;
        detailedRecyclerMythTitle.setText(myths.getMyth());
        detailedRecyclerItemMythDescription.setText(myths.getReality());
        Picasso.get().load(myths.getImage()).into(detailedRecyclerItemMythImage);


    }
}
