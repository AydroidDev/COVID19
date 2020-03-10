package com.know.Virus.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.know.Virus.Adapters.NewsAdapter;
import com.know.Virus.Interfaces.NewsFeatures;
import com.know.Virus.ModelClasses.Article;
import com.know.Virus.ModelClasses.Example;
import com.know.Virus.R;
import com.know.Virus.SingletonClasses.RetrofitInstance;
import com.know.Virus.Utils.Utils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRec;
    private SwipeRefreshLayout mSwipe;
    private AVLoadingIndicatorView progressLoading;
    private RelativeLayout nointernetView;
    private Button tryagain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        mSwipe = view.findViewById(R.id.swiperefresh);
        mRec = view.findViewById(R.id.newsFragmentRecycler);
        progressLoading = view.findViewById(R.id.progressLoading);
        nointernetView = view.findViewById(R.id.nointernetView);
        tryagain = nointernetView.findViewById(R.id.tryagain);
        nointernetView.setVisibility(View.GONE);
        mRec.setVisibility(View.GONE);
        mRec.setLayoutManager(new LinearLayoutManager(view.getContext()));
        initNews();
        mSwipe.setOnRefreshListener(this);
        return view;
    }

    private void initNews() {
        progressLoading.setVisibility(View.VISIBLE);
        // API call on every refresh swipe
        NewsFeatures mNewsFeatures = RetrofitInstance.getRetrofit().create(NewsFeatures.class);
        Call<Example> mCall = mNewsFeatures.getNews(Utils.THEME, Utils.PAGE_SIZE, Utils.LANGUAGE, Utils.SORT_BY, Utils.API_KEY);
        mCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                addtorecycler(response.body());
                progressLoading.setVisibility(View.GONE);
                mRec.setVisibility(View.VISIBLE);
                Log.e("SUCCESS", "DATA FOUND ");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("FAILED", t.getMessage() + "");
                progressLoading.setVisibility(View.GONE);
                nointernetView.setVisibility(View.VISIBLE);
            }
        });


        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNews();
                nointernetView.setVisibility(View.GONE);
            }
        });

    }

    private void addtorecycler(Example Obj) {
        List<Article> mList = Obj.getArticles();
//        for (Article a: mList){
//            Log.e("DATA",a.getTitle());
//        }
        NewsAdapter adapter = new NewsAdapter(getContext(), mList);
        mRec.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        mRec.setVisibility(View.GONE);
        Log.e("Refreshed", "");
        initNews();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(false);
            }
        }, 2000);


    }
}
