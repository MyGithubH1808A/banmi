package com.example.lenovo.demo29.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseFragment;
import com.example.lenovo.demo29.presenter.HomeMainPresenter;
import com.example.lenovo.demo29.ui.main.adapter.VpHomeAdapter;
import com.example.lenovo.demo29.view.HomeMainIView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/5
 * 项目工作空间：Demo29
 */
public class HomeMainFragment extends BaseFragment<HomeMainIView, HomeMainPresenter> implements HomeMainIView {

    //    @BindView(R.id.toob)
//    Toolbar toob;


//    @BindView(R.id.vp)
//    ViewPager vp;
//    @BindView(R.id.toob)
//    Toolbar toob;
//    @BindView(R.id.tab)
//    TabLayout tab;
//    @BindView(R.id.nv)
//    NavigationView nv;
//    @BindView(R.id.dl)
//    DrawerLayout dl;
    Unbinder unbinder1;
//    private ArrayList<Fragment> mFragments;
//    private ArrayList<String> mTitles;
//    private Toolbar mToob;
//    private DrawerLayout mDl;
//    private ViewPager mVp;
//    private TabLayout mTab;

    @Override
    protected HomeMainPresenter initPresenter() {
        return new HomeMainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_main;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        mToob.setTitle("");
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToob);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDl, mToob, R.string.about, R.string.about);
//        mDl.addDrawerListener(toggle);
//        toggle.syncState();
//    }

    @Override
    protected void initView() {

//        mToob = getActivity().findViewById(R.id.toob);
//        mDl = getActivity().findViewById(R.id.dls);
//        vp = getActivity().findViewById(R.id.vp);
//        tab = getActivity().findViewById(R.id.tab);


//        mFragments = new ArrayList<>();
//        mFragments.add(new HomesFragment());
//        mFragments.add(new BanMiFragment());
//        mTitles = new ArrayList<>();
//        mTitles.add("首页");
//        mTitles.add("伴米");


//        VpHomeAdapter adapter = new VpHomeAdapter(getChildFragmentManager(), mFragments, mTitles);
//        vp.setAdapter(adapter);
//        tab.setupWithViewPager(vp);
    }
}
