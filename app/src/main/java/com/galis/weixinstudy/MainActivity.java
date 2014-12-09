package com.galis.weixinstudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private UITabBottom mUiTabBottom;
    private UIViewPager mUiViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        mUiTabBottom = (UITabBottom) findViewById(R.id.home_tab_bottom);
        mUiViewPager = (UIViewPager) findViewById(R.id.home_view_page);
        mUiTabBottom.setmViewPager(mUiViewPager);
        mUiViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mUiViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pageIndex, float v, int i2) {
                mUiTabBottom.scroll(pageIndex, v);
            }

            @Override
            public void onPageSelected(int i) {
                mUiTabBottom.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void initFragments() {
        for (int i = 0; i < 4; i++) {
            fragments.add(CustomFragment.newInstance(i));
        }
    }


    public static class CustomFragment extends Fragment {

        private int position;
        private boolean isShown;

        public static CustomFragment newInstance(int pos) {
            CustomFragment fragment = new CustomFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", pos);
            fragment.setArguments(bundle);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.e("fragment" + position, "onCreateView");
            super.onCreateView(inflater, container, savedInstanceState);
            View layout = inflater.inflate(R.layout.m_fragment, null);
             View progress = layout.findViewById(R.id.progressContainer);
            View content = layout.findViewById(R.id.listContainer);
            return layout;
        }
    }
}
