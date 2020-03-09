package com.Corona.Virus.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Corona.Virus.Activities.DetailedPrevention;
import com.Corona.Virus.ModelClasses.Myths;
import com.Corona.Virus.ModelClasses.Prevention;
import com.Corona.Virus.R;
import com.Corona.Virus.Utils.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class PreventionFragment extends Fragment {
    private RecyclerView mRec;
    private AVLoadingIndicatorView progressLoading;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.prevention_fragment,container,false);

        mRec = view.findViewById(R.id.preventionFragmentRecycler);
        progressLoading = view.findViewById(R.id.progressLoading);
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));


        Query query = FirebaseDatabase.getInstance().getReference("prevention").orderByChild("Rank");
        FirebaseRecyclerOptions<Prevention> options = new FirebaseRecyclerOptions.Builder<Prevention>().setQuery(query,Prevention.class).build();
        FirebaseRecyclerAdapter<Prevention,myViewHolder> adapter = new FirebaseRecyclerAdapter<Prevention,myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Prevention model) {
                holder.setData(model.getTitle(),model.getRank(),model);
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.prevention_row,parent,false);
                return new myViewHolder(v);
            }
        };


        mRec.setAdapter(adapter);
        try {
            adapter.startListening();
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private Prevention mObj;
        private TextView rank;
        private String hexcode;
        private GradientDrawable drawable;
        private CircleImageView imageNewsRecycler;
        @RequiresApi(api = Build.VERSION_CODES.N)
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            progressLoading.setVisibility(View.GONE);
            imageNewsRecycler = itemView.findViewById(R.id.rank);
            title = itemView.findViewById(R.id.titlePrevention);
            rank = itemView.findViewById(R.id.ranktext);
            itemView.setOnClickListener(v -> {
                Intent i=new Intent(getContext(), DetailedPrevention.class);
                i.putExtra("obj",mObj);
                i.putExtra("hexcode",hexcode+"");
                startActivity(i);
            });
        }

        private void setData(String title,long rank,Prevention obj){
            this.title.setText(title);
           // int res = getResources().getIdentifier("@drawable/_"+rank,"drawable",getContext().getPackageName());
            Random r = new Random();
            int val = r.nextInt(Utils.Colors.length);
            String rankString = "#"+rank;
             drawable = (GradientDrawable) imageNewsRecycler.getBackground();
             hexcode = Utils.Colors[val];
            drawable.setColor(Color.parseColor(Utils.Colors[val]));
            this.rank.setText(rankString);
            mObj = obj;
        }

    }
}
