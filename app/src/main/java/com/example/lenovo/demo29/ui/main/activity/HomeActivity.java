package com.example.lenovo.demo29.ui.main.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseActivity;
import com.example.lenovo.demo29.presenter.HomeMainPresenter;
import com.example.lenovo.demo29.ui.main.adapter.VpHomeAdapter;
import com.example.lenovo.demo29.ui.main.fragment.BanMiFragment;
import com.example.lenovo.demo29.ui.main.fragment.HomeMainFragment;
import com.example.lenovo.demo29.ui.main.fragment.HomesFragment;
import com.example.lenovo.demo29.utils.ToastUtil;
import com.example.lenovo.demo29.view.HomeMainIView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity<HomeMainIView, HomeMainPresenter> implements HomeMainIView {

////    @BindView(R.id.nv)
//    NavigationView nv;
//
//    private Toolbar mToolbar;
    private DrawerLayout mDl;
    private Toolbar mToolbar;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;
    private ViewPager vp;
    private TabLayout tab;
    private NavigationView nv;
    private ActionBarDrawerToggle mToggle;
    private ImageView mImag;
    private TextView mBianji;

    @Override
    protected HomeMainPresenter initPresenter() {
        return new HomeMainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        vp =findViewById(R.id.vp);
        tab =findViewById(R.id.tab);
        nv =findViewById(R.id.nv);
        mImag = nv.getHeaderView(0).findViewById(R.id.images);
        mBianji = nv.getHeaderView(0).findViewById(R.id.bianji);
        mBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,EditActivity.class));
            }
        });
        mImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("头像");
            }
        });

        mDl = findViewById(R.id.dl);
        mToolbar = findViewById(R.id.toolBar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.about, R.string.about);
        mDl.addDrawerListener(mToggle);
        mToggle.syncState();

        mFragments = new ArrayList<>();
        mFragments.add(new HomesFragment());
        mFragments.add(new BanMiFragment());
        mTitles = new ArrayList<>();
        mTitles.add("首页");
        mTitles.add("伴米");

        VpHomeAdapter adapter = new VpHomeAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override    //NavigationView的点击侦听，这里监听了菜单的点击
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                updateItemCheckedView();
                switch (item.getItemId()){
                    case R.id.me_kaquan:
                        Toast.makeText(HomeActivity.this,"点击了卡劵",Toast.LENGTH_SHORT).show();
                        item.setChecked(true);
                        break;
                    case R.id.me_yigou:
                        Toast.makeText(HomeActivity.this,"点击了行程",Toast.LENGTH_SHORT).show();
                        item.setChecked(true);
                        break;
                    case R.id.me_shoucang:
                        Toast.makeText(HomeActivity.this,"点击了收藏",Toast.LENGTH_SHORT).show();
                        item.setChecked(true);
                        break;
                    case R.id.me_guanzh:
                        Toast.makeText(HomeActivity.this,"点击了关注",Toast.LENGTH_SHORT).show();
                        item.setChecked(true);
                        break;
                }
                return true;
            }
        });

        TabLayout.Tab tabAt = tab.getTabAt(0);
        tabAt.setIcon(R.drawable.home);
        TabLayout.Tab tabAt2 = tab.getTabAt(1);
        tabAt2.setIcon(R.drawable.banmis);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定退出吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void updateItemCheckedView(){  //当点击侧滑栏选项时，将其余
        for(int i = 0; i < 4; i ++){     // 选项设置成未选中
            nv.getMenu().getItem(i).setChecked(false);
        }
    }
}
