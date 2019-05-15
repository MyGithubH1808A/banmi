package com.example.lenovo.demo29.net;

import com.example.lenovo.demo29.bean.BanMiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/6
 * 项目工作空间：Demo29
 */
public interface BanMiApi {

    String Url = "http://api.banmi.com/api/3.0/";

    @GET("banmi?")
    @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    Observable<BanMiBean> getBanMainData(@Query("page") int page);
}
