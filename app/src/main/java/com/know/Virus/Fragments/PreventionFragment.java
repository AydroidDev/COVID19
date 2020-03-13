package com.know.Virus.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.know.Virus.Activities.DetailedPrevention;
import com.know.Virus.ModelClasses.Prevention;
import com.know.Virus.ModelClasses.Stories;
import com.know.Virus.R;
import com.know.Virus.Utils.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import xute.storyview.StoryModel;
import xute.storyview.StoryView;

public class PreventionFragment extends Fragment {
    private RecyclerView mRec,mStories;
    private AVLoadingIndicatorView progressLoading;
    private TextView virusesLabel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.prevention_fragment,container,false);
        mRec = view.findViewById(R.id.preventionFragmentRecycler);
        progressLoading = view.findViewById(R.id.progressLoading);
        mStories = view.findViewById(R.id.storiesRecycler);
        virusesLabel = view.findViewById(R.id.virusesLabel);

        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        ViewCompat.setNestedScrollingEnabled(mRec,false);

        Query queryStories = FirebaseDatabase.getInstance().getReference("Stories").orderByChild("timestamp");
        mStories.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        FirebaseRecyclerOptions<Stories> option = new FirebaseRecyclerOptions.Builder<Stories>().setQuery(queryStories,Stories.class).build();
        FirebaseRecyclerAdapter<Stories,StoriesViewHolder> adapterStories = new FirebaseRecyclerAdapter<Stories, StoriesViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull StoriesViewHolder holder, int position, @NonNull Stories model) {
                holder.setData(model.getUrl(),model.getName(),model.getTimestamp(),model.getImageUrl(),model);
            }

            @NonNull
            @Override
            public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.story_row,parent,false);
                return new StoriesViewHolder(v);
            }
        };


        Query query = FirebaseDatabase.getInstance().getReference("prevention").orderByChild("Rank");
        FirebaseRecyclerOptions<Prevention> options = new FirebaseRecyclerOptions.Builder<Prevention>().setQuery(query,Prevention.class).build();
        FirebaseRecyclerAdapter<Prevention,myViewHolder> adapter = new FirebaseRecyclerAdapter<Prevention,myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Prevention model) {
                holder.setData(model.getTitle(),model.getRank(),model);
            }

            @SuppressLint("NewApi")
            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.prevention_row,parent,false);
                return new myViewHolder(v);
            }
        };

        mStories.setAdapter(adapterStories);

        mRec.setAdapter(adapter);
        try {
            adapterStories.startListening();
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
            mStories.setBackgroundColor(Color.parseColor("#ffffff"));
            virusesLabel.setBackgroundColor(Color.parseColor("#ffffff"));
            virusesLabel.setText("Viruses");
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

    public class StoriesViewHolder extends RecyclerView.ViewHolder{
        private StoryView storyImage;
        private Stories mObj;

        public StoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.storyView);
            storyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(getContext(), DetailedStory.class);
//                    i.putExtra("obj",mObj);
//                    startActivity(i);
//                    storyView.resetStoryVisits();

                }
            });
        }

        private void setData(List<String> url, String name, long timestamp, String imageurl, Stories mObj){

            ArrayList<StoryModel> uris = new ArrayList<>();
            Calendar c = Calendar.getInstance();
            long time = -timestamp;
            time = time *1000;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar calobj = Calendar.getInstance();
            calobj.setTimeInMillis(time);
            Log.e("VALUES IN CLICK",mObj.getUrl()+" "+mObj.getName()+" "+mObj.getTimestamp());
            for (String Url: mObj.getUrl()) {
                uris.add(new StoryModel(Url, mObj.getName(), df.format(calobj.getTime())));
            }
            storyImage.setImageUris(uris);
            Log.e("VALUES",url+" "+name+" "+timestamp);
            this.mObj = mObj;
        }
    }
}
