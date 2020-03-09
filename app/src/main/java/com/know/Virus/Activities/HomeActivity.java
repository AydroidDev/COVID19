package com.know.Virus.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.know.Virus.Fragments.ViewPagerFragment;
import com.know.Virus.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewPagerFragment viewPagerFragmentSaved= (ViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(viewPagerFragmentSaved==null){
            ViewPagerFragment viewPagerFragment=new ViewPagerFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment,viewPagerFragment);
            fragmentTransaction.commit();
        }
    }
}
