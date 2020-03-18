package com.know.Virus.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.know.Virus.ModelClasses.gridContent;
import com.know.Virus.R;
import com.know.Virus.Utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class GridAdapter extends ArrayAdapter<gridContent> {
    private ArrayList<gridContent> list;
    private Context context;
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

        View view=LayoutInflater.from(context).inflate(R.layout.custom_category_grid,null,false);
        gridContent grid=list.get(position);

        ImageView imageView=view.findViewById(R.id.gridImage);
        TextView textView=view.findViewById(R.id.gridText);
        imageView.setImageResource(grid.getImageOfTheVirus());
        textView.setText(grid.getTitleOfTheImage());
        View designView=view.findViewById(R.id.gridColorDesign);
        designView.setBackgroundColor(Color.parseColor(Utils.Colors[new Random().nextInt(Utils.Colors.length)]));
        view.setOnClickListener(v -> {
            Toast.makeText(context, "Clicked "+position, Toast.LENGTH_SHORT).show();
            Log.e("Error Handling in grid ","Clciked this "+position);
        });
        return  view;
    }
}
