package com.example.lenovo.demo29.presenter;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseApp;
import com.example.lenovo.demo29.base.BasePresenter;
import com.example.lenovo.demo29.bean.VerifyCodeBean;
import com.example.lenovo.demo29.model.LoginMainModel;
import com.example.lenovo.demo29.model.LoginModel;
import com.example.lenovo.demo29.net.ApiService;
import com.example.lenovo.demo29.net.ResultCallBack;
import com.example.lenovo.demo29.view.VerifyIView;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/7
 * 项目工作空间：Demo29
 */
public class VerifyPresenter extends BasePresenter<VerifyIView> {

    private LoginModel mLoginMainModel;

    @Override
    protected void initModel() {
        mLoginMainModel = new LoginModel();
        mModels.add(mLoginMainModel);
    }
    public void getVerifyCode() {
        mLoginMainModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null && bean.getCode() == ApiService.SUCCESS_CODE){
                    if (mMvpView != null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
