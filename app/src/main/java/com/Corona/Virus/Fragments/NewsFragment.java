package com.Corona.Virus.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Corona.Virus.Adapters.NewsAdapter;
import com.Corona.Virus.Interfaces.NewsFeatures;
import com.Corona.Virus.ModelClasses.Article;
import com.Corona.Virus.R;
import com.Corona.Virus.SingletonClasses.RetrofitInstance;
import com.Corona.Virus.Utils.Utils;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragment,container,false);
        initNews(view);

        return view;
    }

    private void initNews(final View view) {
        // API call on every refresh swipe
        NewsFeatures mNewsFeatures = RetrofitInstance.getRetrofit().create(NewsFeatures.class);
        Call<List<Article>> mCall = mNewsFeatures.getNews(Utils.THEME,Utils.PAGE_SIZE,Utils.LANGUAGE, Utils.SORT_BY,Utils.API_KEY);
        mCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                addtorecycler(view,response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.e("FAILED",t.getMessage()+"");
            }
        });

    }

    private void addtorecycler(View view, List<Article> mList) {
        RecyclerView recyclerView = view.findViewById(R.id.newsFragmentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter adapter = new NewsAdapter(getContext(),mList);
        recyclerView.setAdapter(adapter);
    }
}
