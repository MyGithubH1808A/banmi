package com.example.lenovo.demo29.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseApp;
import com.example.lenovo.demo29.base.BaseFragment;
import com.example.lenovo.demo29.base.Constants;
import com.example.lenovo.demo29.presenter.VerifyPresenter;
import com.example.lenovo.demo29.ui.main.activity.HomeActivity;
import com.example.lenovo.demo29.ui.main.activity.LoginActivity;
import com.example.lenovo.demo29.utils.Logger;
import com.example.lenovo.demo29.view.VerifyIView;
import com.example.lenovo.demo29.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/7
 * 项目工作空间：Demo29
 */
public class VerifyFragment extends BaseFragment<VerifyIView,VerifyPresenter> implements VerifyIView{

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_send_again)
    TextView mTvSendAgain;
    @BindView(R.id.icv)
    IdentifyingCodeView mIcv;
    @BindView(R.id.tv_wait)
    TextView mTvWait;

    private int mTime;
    public static VerifyFragment newIntance(String code){
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }
    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }


    @OnClick({R.id.iv_back, R.id.tv_send_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.tv_send_again:
                //调用它是有条件的
                if (mTime == 0){
                    mPresenter.getVerifyCode();
                    //重新发起倒计时
                    LoginMainFragment fragment = (LoginMainFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    fragment.countDown();
                }
                break;
        }
    }

    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //获取回退栈中碎片的数量
        /*int backStackEntryCount = manager.getBackStackEntryCount();
        Logger.println("回退栈Fragmnet数量:"+backStackEntryCount);*/
        //弹栈
        manager.popBackStack();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setData(String data) {
        if (!TextUtils.isEmpty(data) && mTvWait != null) {
            mTvWait.setText(BaseApp.getRes().getString(R.string.verify_code)+data);
        }
    }

    @Override
    protected void initListener() {
        mIcv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        Logger.println(mIcv.getTextContent());
        if (mIcv.getTextContent().length()>=4){
            //自动登录
            toastShort("自动登录");
            mIcv.setBackgroundEnter(false);
            mTvWait.setText(BaseApp.getRes().getString(R.string.wait_please));
            showLoading();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
    }
    public void setCountDownTime(int time) {
        mTime = time;
        if (mTvSendAgain != null){
            if (time != 0){
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                mTvSendAgain.setText(format);
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
            }else {
                mTvSendAgain.setText(getResources().getString(R.string.send_again));
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }
    @Override
    protected void initView() {
        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
    }
}
