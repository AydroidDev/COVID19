package com.know.Virus.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.know.Virus.Fragments.ViewPagerFragment;
import com.know.Virus.R;

public class HomeActivity extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.homeActivityToolbar);
        setSupportActionBar(toolbar);

        ViewPagerFragment viewPagerFragmentSaved= (ViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(viewPagerFragmentSaved==null){
            ViewPagerFragment viewPagerFragment=new ViewPagerFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment,viewPagerFragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.categories,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.categoriesMenu){
            Intent intent=new Intent(HomeActivity.this,CategoriesPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
