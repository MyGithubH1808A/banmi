package com.example.lenovo.demo29.presenter;

import com.example.lenovo.demo29.base.BasePresenter;
import com.example.lenovo.demo29.bean.VerifyCodeBean;
import com.example.lenovo.demo29.model.LoginModel;
import com.example.lenovo.demo29.net.ResultCallBack;
import com.example.lenovo.demo29.view.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
