package com.Corona.Virus.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Corona.Virus.Activities.DetailedNews;
import com.Corona.Virus.ModelClasses.Article;
import com.Corona.Virus.ModelClasses.Example;
import com.Corona.Virus.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<Article> mList;

    public NewsAdapter(Context context, List<Article> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.news_view_recycler,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getTitle(),mList.get(position).getDescription(),mList.get(position).getPublishedAt(),
                mList.get(position).getUrlToImage(),mList.get(position),position+1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleNewsRecycler,descriptionNewsRecycler,publishedDateRecycler;
        private ImageView imageNewsRecycler;
        private View mView;
        int position;
        Article example;
        private AVLoadingIndicatorView imageProgress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            titleNewsRecycler = itemView.findViewById(R.id.titleNewsRecycler);
            descriptionNewsRecycler = itemView.findViewById(R.id.descriptionNewsRecycler);
            publishedDateRecycler = itemView.findViewById(R.id.publishedDateRecycler);
            imageNewsRecycler = itemView.findViewById(R.id.imageNewsRecycler);
            imageProgress = itemView.findViewById(R.id.imageProgress);

            mView.setOnClickListener(v->{
                Intent i=new Intent(context, DetailedNews.class);
                i.putExtra("obj",example);
                i.putExtra("position",position+"");
                context.startActivity(i);
            });
        }

        void setData(String title, String desc, String published, String url, Article example,int position){
            imageProgress.show();
            String[] dateNTime = published.split("T");
            dateNTime[1] = dateNTime[1].substring(0,dateNTime[1].length()-1);
            titleNewsRecycler.setText(title);
            descriptionNewsRecycler.setText(desc);
            publishedDateRecycler.setText(dateNTime[0]+"~"+dateNTime[1]);
            Picasso.get().load(url).placeholder(R.color.grey).into(imageNewsRecycler, new Callback() {
                @Override
                public void onSuccess() {
                    imageProgress.hide();
                }

                @Override
                public void onError(Exception e) {
                    imageProgress.show();
                }
            });
            this.example = example;
            this.position = position;
        }
    }
}
