package com.Corona.Virus.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.Corona.Virus.Adapters.NewsAdapter;
import com.Corona.Virus.Interfaces.NewsFeatures;
import com.Corona.Virus.ModelClasses.Article;
import com.Corona.Virus.ModelClasses.Example;
import com.Corona.Virus.R;
import com.Corona.Virus.SingletonClasses.RetrofitInstance;
import com.Corona.Virus.Utils.Utils;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRec;
    private SwipeRefreshLayout mSwipe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragment,container,false);
        mSwipe = view.findViewById(R.id.swiperefresh);
        mRec = view.findViewById(R.id.newsFragmentRecycler);
        mRec.setLayoutManager(new LinearLayoutManager(view.getContext()));
        initNews();
        mSwipe.setOnRefreshListener(this);
        return view;
    }

    private void initNews() {
        // API call on every refresh swipe
        NewsFeatures mNewsFeatures = RetrofitInstance.getRetrofit().create(NewsFeatures.class);
        Call<Example> mCall = mNewsFeatures.getNews(Utils.THEME,Utils.PAGE_SIZE,Utils.LANGUAGE, Utils.SORT_BY,Utils.API_KEY);
        mCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                addtorecycler(response.body());
                Log.e("SUCCESS","DATA FOUND ");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("FAILED",t.getMessage()+"");
            }
        });

    }

    private void addtorecycler(Example Obj) {
        List<Article> mList = Obj.getArticles();
//        for (Article a: mList){
//            Log.e("DATA",a.getTitle());
//        }
        NewsAdapter adapter = new NewsAdapter(getContext(),mList);
        mRec.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        Log.e("Refreshed","");
        initNews();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(false);
            }
        },2000);


    }
}
