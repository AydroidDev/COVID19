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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myths_fragment,container,false);

        mRec = view.findViewById(R.id.mythsFragmentRecycler);
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = FirebaseDatabase.getInstance().getReference("myths");
        FirebaseRecyclerOptions<Myths> options = new FirebaseRecyclerOptions.Builder<Myths>().setQuery(query,Myths.class).build();
        FirebaseRecyclerAdapter<Myths,MyViewHolder> adapter = new FirebaseRecyclerAdapter<Myths, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Myths model) {
            holder.setData(model.getMyth(),model.getImage(),model);
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
        private AVLoadingIndicatorView imageProgress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNewsRecycler = itemView.findViewById(R.id.imageNewsRecycler);
            titleNewsRecycler = itemView.findViewById(R.id.titleNewsRecycler);
            imageProgress = itemView.findViewById(R.id.imageProgress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getContext(), DetailedMyth.class);
                    i.putExtra("obj",mObj);
                    startActivity(i);
                    // is file me i.putExtra("obj",mObj);
                    //detailed file me getIntent().getSerializableExtra("obj");
                }
            });
        }

        private void setData(String Myth,String image,Myths obj){
            imageProgress.show();
            titleNewsRecycler.setText(Myth);
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
