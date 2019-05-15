package com.example.lenovo.demo29.model;

import com.example.lenovo.demo29.base.BaseModel;
import com.example.lenovo.demo29.bean.LoginInfo;
import com.example.lenovo.demo29.bean.VerifyCodeBean;
import com.example.lenovo.demo29.net.ApiService;
import com.example.lenovo.demo29.net.BaseObserver;
import com.example.lenovo.demo29.net.EveryWhereService;
import com.example.lenovo.demo29.net.HttpUtils;
import com.example.lenovo.demo29.net.ResultCallBack;
import com.example.lenovo.demo29.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/4
 * 项目工作空间：Demo29
 */
public class LoginMainModel extends BaseModel {
    public void loginSina(String uid, final ResultCallBack<LoginInfo> callBack) {
        EveryWhereService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereService.BASE_URL, EveryWhereService.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo != null){
                            if (loginInfo.getCode() == EveryWhereService.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getDesc());
                            }
                        }
                    }
                });
    }
}
