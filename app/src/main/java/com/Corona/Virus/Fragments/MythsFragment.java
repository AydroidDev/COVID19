package com.Corona.Virus.Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Corona.Virus.Activities.DetailedMyth;
import com.Corona.Virus.ModelClasses.Myths;
import com.Corona.Virus.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

public class MythsFragment extends Fragment {
    private RecyclerView mRec;
    private AVLoadingIndicatorView progressLoading;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myths_fragment,container,false);

        mRec = view.findViewById(R.id.mythsFragmentRecycler);
        progressLoading = view.findViewById(R.id.progressLoading);

        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = FirebaseDatabase.getInstance().getReference("myths").orderByChild("Rank");
        FirebaseRecyclerOptions<Myths> options = new FirebaseRecyclerOptions.Builder<Myths>().setQuery(query,Myths.class).build();
        FirebaseRecyclerAdapter<Myths,MyViewHolder> adapter = new FirebaseRecyclerAdapter<Myths, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Myths model) {
            holder.setData(model.getMyth(),model.getImage(),model,position+1);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.myth_row,parent,false);
                return new MyViewHolder(v);
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

     class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleNewsRecycler;
        private ImageView imageNewsRecycler;
        private Myths mObj;
        private int position;
        private AVLoadingIndicatorView imageProgress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progressLoading.setVisibility(View.GONE);
            imageNewsRecycler = itemView.findViewById(R.id.imageNewsRecycler);
            titleNewsRecycler = itemView.findViewById(R.id.titleNewsRecycler);
            imageProgress = itemView.findViewById(R.id.imageProgress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getContext(), DetailedMyth.class);
                    i.putExtra("obj",mObj);
                    i.putExtra("position",position+"");
                    startActivity(i);
                }
            });
        }

        private void setData(String Myth,String image,Myths obj,int _position){
            imageProgress.show();
            titleNewsRecycler.setText(Myth);
            position  = _position;
            Picasso.get().load(image).placeholder(R.color.grey).into(imageNewsRecycler, new Callback() {
                @Override
                public void onSuccess() {
                    imageProgress.hide();
                }

                @Override
                public void onError(Exception e) {
                imageProgress.hide();
                }
            });
            mObj = obj;

        }

    }
}
