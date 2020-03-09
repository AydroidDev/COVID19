package com.know.Virus.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.know.Virus.R;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_fragment, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        final NewsFragment newsFragment=new NewsFragment();
        final PreventionFragment preventionFragment=new PreventionFragment();
        final MythsFragment mythsFragment=new MythsFragment();
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 1: return newsFragment;
                    case 2: return mythsFragment;
                    default: return preventionFragment;
                }
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 1: return "News";
                    case 2: return "Myths";
                    default: return "Prevention";
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        TabLayout tabLayout=view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

