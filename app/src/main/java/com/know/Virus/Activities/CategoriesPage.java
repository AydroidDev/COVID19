package com.know.Virus.Activities;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.know.Virus.Adapters.GridAdapter;
import com.know.Virus.ModelClasses.gridContent;
import com.know.Virus.R;

import java.util.ArrayList;

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

