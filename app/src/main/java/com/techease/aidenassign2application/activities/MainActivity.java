package com.techease.aidenassign2application.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.techease.aidenassign2application.adapter.InformationScreenViewPagerAdapter;
import com.techease.aidenassign2application.fragments.AllPatientsFragment;
import com.techease.aidenassign2application.fragments.MyPatientsFragment;
import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.genrelUtils.ZoomOutPageTransformer;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton addPatient, addTextPatient;
    ImageView ivSearch;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        addPatient = findViewById(R.id.addPatient);
        addTextPatient = findViewById(R.id.addTest);
        viewPager = findViewById(R.id.viewPager);
        ivSearch=findViewById(R.id.iv_search_m);
        tabLayout = findViewById(R.id.sliding_tabs);
        setupViewPager(viewPager);


        addPatient.setOnClickListener(this);
        addTextPatient.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

    }

    private void setupViewPager(final ViewPager viewPager) {

        viewPager.setPageTransformer(true, new

                ZoomOutPageTransformer());
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(viewPager);


        InformationScreenViewPagerAdapter adapter = new InformationScreenViewPagerAdapter(getSupportFragmentManager(), 3);
        adapter.addFragment(new MyPatientsFragment(), "MY PATIENTS");
        adapter.addFragment(new AllPatientsFragment(), "ALL PATIENTS");
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewPager.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addPatient:
                startActivity(new Intent(MainActivity.this, AddPatientsActivity.class));
                break;
            case R.id.addTest:
                startActivity(new Intent(MainActivity.this, AddTestActivity.class));
                break;
            case R.id.iv_search_m:
                startActivity(new Intent(MainActivity.this, SearchFilterActivity.class));
                break;
        }

    }
}
