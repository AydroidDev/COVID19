package com.Corona.Virus.Fragments;

import android.content.Intent;
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

import com.Corona.Virus.ModelClasses.Myths;
import com.Corona.Virus.ModelClasses.Prevention;
import com.Corona.Virus.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

public class PreventionFragment extends Fragment {
    private RecyclerView mRec;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.prevention_fragment,container,false);
        mRec = view.findViewById(R.id.preventionFragmentRecycler);
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = FirebaseDatabase.getInstance().getReference("prevention");
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
        private ImageView imageNewsRecycler;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNewsRecycler = itemView.findViewById(R.id.rank);
            title = itemView.findViewById(R.id.titlePrevention);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // intent.putExtra("obj",mObj);
                    //getIntent().getSerializableExtra("obj");
                }
            });
        }

        private void setData(String title,long rank,Prevention obj){
            this.title.setText(title);
            int res = getResources().getIdentifier("@drawable/_"+rank,"drawable",getContext().getPackageName());
            imageNewsRecycler.setImageResource(res);
            mObj = obj;
        }

    }
}
