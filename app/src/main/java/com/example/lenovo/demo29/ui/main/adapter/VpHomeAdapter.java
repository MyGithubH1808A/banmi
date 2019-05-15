package com.example.lenovo.demo29.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/5
 * 项目工作空间：Demo29
 */
public class VpHomeAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragment;
    private ArrayList<String> mTitle;

    public VpHomeAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.mFragment = fragments;
        this.mTitle = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
