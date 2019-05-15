package com.example.lenovo.demo29.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.base.BaseApp;
import com.example.lenovo.demo29.base.BasePresenter;
import com.example.lenovo.demo29.base.Constants;
import com.example.lenovo.demo29.bean.LoginInfo;
import com.example.lenovo.demo29.bean.VerifyCodeBean;
import com.example.lenovo.demo29.model.LoginMainModel;
import com.example.lenovo.demo29.model.LoginModel;
import com.example.lenovo.demo29.net.ApiService;
import com.example.lenovo.demo29.net.ResultCallBack;
import com.example.lenovo.demo29.utils.SpUtil;
import com.example.lenovo.demo29.view.LoginMainIView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import static com.umeng.socialize.net.dplus.CommonNetImpl.TAG;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/4
 * 项目工作空间：Demo29
 */
public class LoginMainPresenter extends BasePresenter<LoginMainIView> {
    private LoginMainModel mLoginOrBindModel;
    private LoginModel mLoginMainModel;

    @Override
    protected void initModel() {
        mLoginOrBindModel = new LoginMainModel();
        mModels.add(mLoginOrBindModel);
        mLoginMainModel = new LoginModel();
        mModels.add(mLoginMainModel);
    }

    public void oauthLogin(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.getAct());
        umShareAPI.getPlatformInfo(mMvpView.getAct(), type, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            logMap(data);
            //只写微博的,微信的成功不了
            if (platform == SHARE_MEDIA.SINA) {
                loginSina(data.get("uid"));
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mMvpView.getAct(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mMvpView.getAct(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private void loginSina(String uid) {
        mLoginOrBindModel.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                //登录成功了,需要做什么
                //1.跳转主页面
                //2.保存用户信息
                if (bean.getResult() != null) {
                    saveUserInfo(bean.getResult());
                    if (mMvpView != null) {
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        mMvpView.go2MainActivity();
                    }
                } else {
                    if (mMvpView != null) {
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.toastShort(msg);
                }
            }
        });
    }

    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN, result.getToken());
        SpUtil.setParam(Constants.DESC, result.getDescription());
        SpUtil.setParam(Constants.USERNAME, result.getUserName());
        SpUtil.setParam(Constants.GENDER, result.getGender());
        SpUtil.setParam(Constants.EMAIL, result.getEmail());
        SpUtil.setParam(Constants.PHOTO, result.getPhoto());
        SpUtil.setParam(Constants.PHONE, result.getPhone());
    }

    private void logMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            Log.d(TAG, "logMap: " + entry.getKey() + "," + entry.getValue());
        }
    }

    public void getVerifyCode() {
        mLoginMainModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null && bean.getCode() == ApiService.SUCCESS_CODE) {
                    if (mMvpView != null) {
                        mMvpView.setData(bean.getData());
                    }
                } else {
                    if (mMvpView != null) {
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
