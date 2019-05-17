package com.example.lenovo.demo29.net;

import com.example.lenovo.demo29.bean.VerisonBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/16
 * 项目工作空间：Demo29
 */
public interface VerisonApi {

    String Url = "https://api.banmi.com/api/app/common/";

    @GET("getVersionInfo?operating_system=android")
    Observable<VerisonBean> getDataVerison();

    String Urls = "http://cdn.banmi.com/banmiapp/";

    @GET("apk/banmi_330.apk")
    Observable<ResponseBody> getDataVerisons();
}
