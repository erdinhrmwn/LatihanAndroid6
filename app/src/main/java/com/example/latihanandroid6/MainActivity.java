package com.example.latihanandroid6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.latihanandroid6.allAdapter.NavBottomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigationViewPager viewPager;
    AHBottomNavigation bottomNavigation;
    private Fragment currentFragment;
    private NavBottomAdapter navBottomAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigation = findViewById(R.id.bottomNav);

        initUI();
    }

    private void initUI() {
        boolean useMenuResources = true;
        if (useMenuResources) {
            int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(MainActivity.this, R.menu.bottom_menu);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
        }else {
            AHBottomNavigationItem item1 = new AHBottomNavigationItem("City List", R.mipmap.ic_launcher_round, R.color.colorBottomNavigationAccent);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem("Book List", R.mipmap.ic_launcher_round, R.color.colorBottomNavigationAccent);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem("Profile", R.mipmap.ic_launcher_round, R.color.colorBottomNavigationAccent);

            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);
            bottomNavigationItems.add(item3);

            bottomNavigation.addItems(bottomNavigationItems);
        }

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment == null){
                    currentFragment = navBottomAdapter.getCurrentFragment();
                }

                viewPager.setCurrentItem(position, true);

                if (currentFragment == null){
                    return true;
                }
                currentFragment = navBottomAdapter.getCurrentFragment();
                return true;
            }
        });

        viewPager.setOffscreenPageLimit(4);
        navBottomAdapter = new NavBottomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(navBottomAdapter);

        currentFragment = navBottomAdapter.getCurrentFragment();
    }
}
