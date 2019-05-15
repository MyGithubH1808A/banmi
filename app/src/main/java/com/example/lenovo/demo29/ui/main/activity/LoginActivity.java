package com.example.lenovo.demo29.ui.main.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseActivity;
import com.example.lenovo.demo29.base.Constants;
import com.example.lenovo.demo29.presenter.LoginMainPresenter;
import com.example.lenovo.demo29.presenter.LoginPresenter;
import com.example.lenovo.demo29.ui.main.fragment.LoginMainFragment;
import com.example.lenovo.demo29.utils.Tools;
import com.example.lenovo.demo29.view.LoginMainIView;
import com.example.lenovo.demo29.view.LoginView;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView{

    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_BIND = 1;
    private int mType;
    public static String TAG = "loginFragment";

    public static void startAct(Context context , int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    protected void initView() {
        Tools.addShortcut(this,R.drawable.qq,this);
        getIntentData();
        FragmentManager manager = getSupportFragmentManager();
        LoginMainFragment fragment = LoginMainFragment.newIntance(mType);
        manager.beginTransaction().add(R.id.fl_container,fragment,TAG).commit();
    }
    private void getIntentData() {
        mType = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
}
