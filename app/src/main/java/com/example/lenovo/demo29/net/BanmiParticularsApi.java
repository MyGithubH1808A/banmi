package com.example.lenovo.demo29.net;

import com.example.lenovo.demo29.bean.BanMiParticularsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/15
 * 项目工作空间：Demo29
 */
public interface BanmiParticularsApi {
//    https://api.banmi.com/api/3.0/banmi/57
    String Url = "https://api.banmi.com/api/3.0/banmi/57";

    @GET("{routeId}")
    Observable<BanMiParticularsBean> getBanMiDatas(@Path("routeId") int routeId);
}
