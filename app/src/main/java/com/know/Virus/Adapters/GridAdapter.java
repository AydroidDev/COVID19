package com.know.Virus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.know.Virus.ModelClasses.gridContent;
import com.know.Virus.R;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<gridContent> {
    ArrayList<gridContent> list=new ArrayList<>();
    Context context;
    int resource;


    public GridAdapter(@NonNull Context context, int resource, ArrayList<gridContent>list) {
        super(context, resource,list);
        this.context=context;
        this.resource=resource;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.custom_category_grid,null,false);
        gridContent grid=list.get(position);
        ImageView imageView=view.findViewById(R.id.gridImage);
        TextView textView=view.findViewById(R.id.gridText);
        imageView.setImageResource(grid.getImageOfTheVirus());
        textView.setText(grid.getTitleOfTheImage());
        return  view;
    }
}
