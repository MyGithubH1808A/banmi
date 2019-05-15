package com.example.lenovo.demo29.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.HomeBean;
import com.example.lenovo.demo29.net.HomeApi;
import com.example.lenovo.demo29.ui.main.activity.HomeParticularsActivity;
import com.example.lenovo.demo29.ui.main.adapter.RlvHomeAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/5
 * 项目工作空间：Demo29
 */
public class HomesFragment extends Fragment {
    private View view;
    private Banner mBanner;
    private RecyclerView mRlv;
    private ArrayList<HomeBean.ResultBean.RoutesBean> mList;
    private ArrayList<HomeBean.ResultBean.BannersBean> mListBanner;
    private RlvHomeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hoems, null, false);
        initView(inflate);
        initBannerData();
        initHomeData();
        return inflate;
    }

    private void initHomeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HomeApi.Url)
                .build();
        HomeApi homeApi = retrofit.create(HomeApi.class);
        Observable<HomeBean> homeData = homeApi.getHomeData(1);
        homeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean value) {
                        List<HomeBean.ResultBean.RoutesBean> list = value.getResult().getRoutes();
                        mList.addAll(list);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initBannerData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeApi homeApi = retrofit.create(HomeApi.class);
        Observable<HomeBean> data = homeApi.getHomeData(1);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean value) {
                        List<HomeBean.ResultBean.BannersBean> data = value.getResult().getBanners();
                        mListBanner.addAll(data);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        mBanner = (Banner) inflate.findViewById(R.id.banner);
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRlv.setLayoutManager(manager);
        mList = new ArrayList<>();
        mListBanner = new ArrayList<>();
        mAdapter = new RlvHomeAdapter(getActivity(), mList,mListBanner);
        mRlv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RlvHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getContext(),HomeParticularsActivity.class);
//                bundle.putString("cardURL",mList.get(position-1).getCardURL());
//                bundle.putString("title",mList.get(position).getTitle());
//                bundle.putString("intro",mList.get(position).getIntro());
//                bundle.putString("city",mList.get(position).getCity());
                intent.putExtra("id",mList.get(position).getId()-1);
                getActivity().startActivity(intent);
            }
        });
    }
}
