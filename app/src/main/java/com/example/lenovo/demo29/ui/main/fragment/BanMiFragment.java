package com.example.lenovo.demo29.ui.main.fragment;

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

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.BanMiBean;
import com.example.lenovo.demo29.net.BanMiApi;
import com.example.lenovo.demo29.ui.main.activity.BanmiParticularsActivity;
import com.example.lenovo.demo29.ui.main.activity.HomeParticularsActivity;
import com.example.lenovo.demo29.ui.main.adapter.RlvBanMiAdapter;

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
public class BanMiFragment extends Fragment {
    private View view;
    private RecyclerView mRlv;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> mList;
    private RlvBanMiAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_banmi, null, false);
        initView(inflate);
        initBanMiData();
        return inflate;
    }

    private void initBanMiData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanMiApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        BanMiApi banMiApi = retrofit.create(BanMiApi.class);
        Observable<BanMiBean> banMainData = banMiApi.getBanMainData(1);
        banMainData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean value) {
                        List<BanMiBean.ResultBean.BanmiBean> list = value.getResult().getBanmi();
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

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRlv.setLayoutManager(manager);
        mList = new ArrayList<>();
        mAdapter = new RlvBanMiAdapter(getActivity(),mList);
        mRlv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RlvBanMiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(),BanmiParticularsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("https://api.banmi.com/api/3.0/banmi/57",mList.get(position).getId()+"");
//                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }
}
