package com.example.tablayout_test_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tablayout_test_2.adapter.FragmentAdapter;
import com.example.tablayout_test_2.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public List<NhanVien> list = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new FragmentAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_list);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        floatingActionButton = findViewById(R.id.fab);
    }
}