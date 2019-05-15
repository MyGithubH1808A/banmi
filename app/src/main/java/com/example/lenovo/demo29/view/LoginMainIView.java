package com.example.lenovo.demo29.view;

import android.app.Activity;

import com.example.lenovo.demo29.base.BaseMvpView;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/4
 * 项目工作空间：Demo29
 */
public interface LoginMainIView extends BaseMvpView {

    String getPhone();
    Activity getAct();

    void go2MainActivity();

    void setData(String code);
}
