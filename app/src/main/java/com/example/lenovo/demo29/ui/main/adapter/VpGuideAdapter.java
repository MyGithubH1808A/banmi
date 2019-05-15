package com.example.lenovo.demo29.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/8
 * 项目工作空间：Demo29
 */
public class VpGuideAdapter extends PagerAdapter{
    private ArrayList<View> mList;

    public VpGuideAdapter(ArrayList<View> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        if (mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ((ViewPager) container).addView(mList.get(position), 0);
        return mList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView(mList.get(position));
    }
}
