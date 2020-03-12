package com.know.Virus.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.know.Virus.Adapters.GridAdapter;
import com.know.Virus.ModelClasses.gridContent;
import com.know.Virus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPage extends AppCompatActivity {
GridView gridView;
ArrayList<gridContent>arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);
        gridView=findViewById(R.id.categoryGrid);
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));
        arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"))
        ;arrayList.add(new gridContent(R.drawable.logo,"This is The virus"));
        arrayList.add(new gridContent(R.drawable.covid194,"This is a virus"));

        GridAdapter adapter=new GridAdapter(this,R.layout.custom_category_grid,arrayList);
        gridView.setAdapter(adapter);
    }
}

