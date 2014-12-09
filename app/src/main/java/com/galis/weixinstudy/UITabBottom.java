package com.galis.weixinstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Author: galis
 * Date: 2014/9/15 18:49
 * Version: 1.4
 * Describe:主界面底部标签栏
 */


public class UITabBottom extends LinearLayout implements View.OnClickListener {

    public static interface OnUITabChangeListener {
        public void onTabChange(int index);
    }

    private UITabItem tab0;
    private UITabItem tab1;
    private UITabItem tab2;
    private UITabItem tab3;
    private UIViewPager mViewPager;
    private OnUITabChangeListener changeListener;


    private int colorClick;
    private int colorUnclick;
    private int R1;//未选中的Red值
    private int G1;//未选中的Green值
    private int B1;//未选中的Blue值
    private int R2;//选中的Red值
    private int G2;//选中的Green值
    private int B2;//选中的Blue值
    private int Rm = R2 - R1;//Red的差值
    private int Gm = G2 - G1;//Green的差值
    private int Bm = B2 - B1;//Blue的差值

    private int mIndex;

    public UITabBottom(Context context) {
        super(context);
    }

    public UITabBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UIViewPager getmViewPager() {
        return mViewPager;
    }

    public void setmViewPager(UIViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    private UITabItem newChildItem(int i) {

        UITabItem tabItem = new UITabItem();
        tabItem.parent = LayoutInflater.from(getContext()).inflate(R.layout.m_tabitem, null);
        tabItem.iconView = (TabIconView) tabItem.parent.findViewById(R.id.mTabIcon);
        tabItem.labelView = (TextView) tabItem.parent.findViewById(R.id.mTabText);
        tabItem.tipView = tabItem.parent.findViewById(R.id.mTabTip);
        tabItem.parent.setTag(i);
        tabItem.parent.setOnClickListener(this);
        return tabItem;
    }

    public void init() {

        colorClick = getResources().getColor(R.color.select);
        colorUnclick = getResources().getColor(R.color.unselect);

        int tabBottomHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        ;
        setOrientation(LinearLayout.HORIZONTAL);
        //tab0
        tab0 = newChildItem(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, tabBottomHeight);
        layoutParams.weight = 1;
        tab0.labelView.setText("首页");
        tab0.labelView.setTextColor(colorClick);
        tab0.iconView.init(R.drawable.tabbar_home_selected, R.drawable.tabbar_home);
        addView(tab0.parent, layoutParams);
        //tab1
        tab1 = newChildItem(1);
        layoutParams = new LinearLayout.LayoutParams(0, tabBottomHeight);
        layoutParams.weight = 1;
        tab1.labelView.setText("消息");
        tab1.labelView.setTextColor(colorUnclick);
        tab1.iconView.init(R.drawable.tabbar_message_center_selected, R.drawable.tabbar_message_center);
        addView(tab1.parent, layoutParams);
        //tab2
        tab2 = newChildItem(2);
        layoutParams = new LinearLayout.LayoutParams(0, tabBottomHeight);
        layoutParams.weight = 1;
        tab2.labelView.setText("发现");
        tab2.labelView.setTextColor(colorUnclick);
        tab2.iconView.init(R.drawable.tabbar_discover_selected, R.drawable.tabbar_discover);
        addView(tab2.parent, layoutParams);
        //tab3
        tab3 = newChildItem(3);
        layoutParams = new LinearLayout.LayoutParams(0, tabBottomHeight);
        layoutParams.weight = 1;
        tab3.labelView.setText("我");
        tab3.labelView.setTextColor(colorUnclick);
        tab3.iconView.init(R.drawable.tabbar_profile_selected, R.drawable.tabbar_profile);
        addView(tab3.parent, layoutParams);

        R1 = (colorClick & 0xff0000) >> 16;
        G1 = (colorClick & 0xff00) >> 8;
        B1 = (colorClick & 0xff);
        R2 = (colorUnclick & 0xff0000) >> 16;
        G2 = (colorUnclick & 0xff00) >> 8;
        B2 = (colorUnclick & 0xff);
        Rm = R1 - R2;
        Gm = G1 - G2;
        Bm = B1 - B2;

        mIndex = 0;
    }

    private OnUITabChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(OnUITabChangeListener changeListener) {
        this.changeListener = changeListener;
    }


    public void setTabRedDot(int index,int state)
    {
        if(index==2)
        {
            tab2.tipView.setVisibility(state);
        }
        if(index==3)
        {
            tab3.tipView.setVisibility(state);
        }

    }


    public void selectTab(int index) {

        if (mIndex == index) {
            return;
        }

        mIndex = index;
        if (changeListener != null) {
            changeListener.onTabChange(mIndex);
        }
        //mIndex表示处于mIndex到mIndex+1页面之间
        switch (mIndex) {
            case 0:
                tab0.iconView.setmAlpha(255);
                tab1.iconView.setmAlpha(0);
                tab2.iconView.setmAlpha(0);
                tab3.iconView.setmAlpha(0);
                tab0.labelView.setTextColor(colorClick);
                tab1.labelView.setTextColor(colorUnclick);
                tab2.labelView.setTextColor(colorUnclick);
                tab3.labelView.setTextColor(colorUnclick);
                break;
            case 1:
                tab0.iconView.setmAlpha(0);
                tab1.iconView.setmAlpha(255);
                tab2.iconView.setmAlpha(0);
                tab3.iconView.setmAlpha(0);
                tab0.labelView.setTextColor(colorUnclick);
                tab1.labelView.setTextColor(colorClick);
                tab2.labelView.setTextColor(colorUnclick);
                tab3.labelView.setTextColor(colorUnclick);
                break;
            case 2:
                tab0.iconView.setmAlpha(0);
                tab1.iconView.setmAlpha(0);
                tab2.iconView.setmAlpha(255);
                tab3.iconView.setmAlpha(0);
                tab0.labelView.setTextColor(colorUnclick);
                tab1.labelView.setTextColor(colorUnclick);
                tab2.labelView.setTextColor(colorClick);
                tab3.labelView.setTextColor(colorUnclick);
                break;
            case 3:
                tab0.iconView.setmAlpha(0);
                tab1.iconView.setmAlpha(0);
                tab2.iconView.setmAlpha(0);
                tab3.iconView.setmAlpha(255);
                tab0.labelView.setTextColor(colorUnclick);
                tab1.labelView.setTextColor(colorUnclick);
                tab2.labelView.setTextColor(colorUnclick);
                tab3.labelView.setTextColor(colorClick);
                break;
        }

    }

    /**
     * 拼成颜色值
     * @param f
     * @return
     */
    private int getColorInt(float f) {
        int R = (int) (R2 + f * Rm);
        int G = (int) (G2 + f * Gm);
        int B = (int) (B2 + f * Bm);
        return 0xff << 24 | R << 16 | G << 8 | B;

    }


    /**
     * location为最左边页面的index,e.g.,fragment0到fragment1,传入location=0
     * f为滑动距离的百分比
     *
     * @param location
     * @param f
     */
    public void scroll(int location, float f) {
        int leftAlpha = (int) (255 * (1 - f));
        int rightAlpha = (int) (255 * f);
        int leftColor = getColorInt(1 - f);
        int rightColor = getColorInt(f);
        switch (location) {
            case 0:
                tab0.iconView.setmAlpha(leftAlpha);
                tab1.iconView.setmAlpha(rightAlpha);

                tab0.labelView.setTextColor(leftColor);
                tab1.labelView.setTextColor(rightColor);
                break;
            case 1:
                tab1.iconView.setmAlpha(leftAlpha);
                tab2.iconView.setmAlpha(rightAlpha);

                tab1.labelView.setTextColor(leftColor);
                tab2.labelView.setTextColor(rightColor);
                break;
            case 2:
                tab2.iconView.setmAlpha(leftAlpha);
                tab3.iconView.setmAlpha(rightAlpha);

                tab2.labelView.setTextColor(leftColor);
                tab3.labelView.setTextColor(rightColor);

                break;
        }
    }

    @Override
    public void onClick(View v) {
        int i = ((Integer) v.getTag()).intValue();
        mViewPager.setCurrentItem(i, false);
        selectTab(i);
    }
}
