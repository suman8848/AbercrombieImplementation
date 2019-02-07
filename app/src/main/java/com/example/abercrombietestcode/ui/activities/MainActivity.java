package com.example.abercrombietestcode.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.abercrombietestcode.R;
import com.example.abercrombietestcode.base.BaseActivity;
import com.example.abercrombietestcode.ui.fragments.CategoriesFragment;
import com.example.abercrombietestcode.ui.fragments.ExploreFragment;
import com.example.abercrombietestcode.ui.fragments.NewArrivalsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** MainActivity as a launcher activity */
public class MainActivity extends BaseActivity {

  @Override
  protected void onViewReady(Bundle savedInstanceState, Intent intent) {
    super.onViewReady(savedInstanceState, intent);
    setToolbar();
    Objects.requireNonNull(getSupportActionBar()).setTitle("Abercrombie & Fitch");
    // attach swipe views as fragment
    ViewPager viewPager = findViewById(R.id.pager);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new CategoriesFragment(), "Categories");
    adapter.addFragment(new ExploreFragment(), "Explore");
    adapter.addFragment(new NewArrivalsFragment(), "New Arrivals");
    viewPager.setAdapter(adapter);
    viewPager.setCurrentItem(1);

    TabLayout tabLayout = findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }


  @Override
  protected int getContentView() {
    return R.layout.activity_main;
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

     void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }
}
