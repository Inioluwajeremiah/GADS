package com.example.gads;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.gads.learning_leaders_frag.LearningLeaders;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView submit_text_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("LEADER BOARD");

        viewPager = findViewById(R.id.view_pager);
        setupViewpager(viewPager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        submit_text_btn = findViewById(R.id.submit_btn);
        submit_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nintent = new Intent(MainActivity.this, SubmitForm.class);
                startActivity(nintent);
            }
        });
    }

    private void setupViewpager (ViewPager viewPager) {
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.addFragment(new LearningLeaders(), "Learning Leaders");
        viewpagerAdapter.addFragment(new IQleaders(), "Skill IQ Leaders");
        viewPager.setAdapter(viewpagerAdapter);
    }
}
