package com.example.lenovo.demo29.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.BanMiParticularsBean;
import com.example.lenovo.demo29.net.BanmiParticularsApi;
import com.example.lenovo.demo29.ui.main.adapter.BanMiParticularsAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanmiParticularsActivity extends AppCompatActivity {

    private RecyclerView mRlvb;
    private ImageView mIv;
    private ArrayList<BanMiParticularsBean.ResultBean.ShareBean> mList;
    private BanMiParticularsAdapter mAdapter;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banmi_particulars);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BanmiParticularsApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        BanmiParticularsApi api = retrofit.create(BanmiParticularsApi.class);
        Observable<BanMiParticularsBean> banMiDatas = api.getBanMiDatas(mId);
        banMiDatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiParticularsBean value) {
                        String photo = value.getResult().getBanmi().getPhoto();
                        RoundedCorners roundedCorners = new RoundedCorners(10);
                                RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(140,100);
                                Glide.with(BanmiParticularsActivity.this).load(photo).apply(options1).into(mIv);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRlvb = (RecyclerView) findViewById(R.id.rlvb);
        mIv = (ImageView) findViewById(R.id.iv);
        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRlvb.setLayoutManager(manager);
        mAdapter = new BanMiParticularsAdapter();
        mRlvb.setAdapter(mAdapter);
    }
}
