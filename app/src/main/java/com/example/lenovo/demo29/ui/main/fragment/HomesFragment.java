package com.example.lenovo.demo29.ui.main.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.HomeBean;
import com.example.lenovo.demo29.bean.VerisonBean;
import com.example.lenovo.demo29.net.HomeApi;
import com.example.lenovo.demo29.net.VerisonApi;
import com.example.lenovo.demo29.ui.main.activity.HomeParticularsActivity;
import com.example.lenovo.demo29.ui.main.adapter.RlvHomeAdapter;
import com.example.lenovo.demo29.utils.InstallUtil;
import com.example.lenovo.demo29.utils.ToastUtil;
import com.example.lenovo.demo29.utils.Tools;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
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
    private static final String TAG = HomesFragment.class.getName();
    private View view;
    private Banner mBanner;
    private RecyclerView mRlv;
    private ArrayList<HomeBean.ResultBean.RoutesBean> mList;
    private ArrayList<HomeBean.ResultBean.BannersBean> mListBanner;
    private RlvHomeAdapter mAdapter;
    private String mVersion;
    private int mCode;
    private File sd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hoems, null, false);
        initView(inflate);
        initBannerData();
        initHomeData();
        initTools();
        initVerison();
        initSD();
        return inflate;
    }

    private void initSD() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            openSD();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager
                .PERMISSION_GRANTED) {
            openSD();
        }
    }

    private void openSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }

    private void initVerison() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VerisonApi.Url)
                .build();
        VerisonApi verisonApi = retrofit.create(VerisonApi.class);
        Observable<VerisonBean> dataVerison = verisonApi.getDataVerison();
        dataVerison.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerisonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerisonBean value) {
                        mCode = value.getCode();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initTools() {
        int versionCode = Tools.getVersionCode();
        if (Tools.getVersionName().equals(mCode)){
            ToastUtil.showShort("当前是已最新版本");
        }else {
            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(getContext());
            normalDialog.setTitle("版本检测");
            normalDialog.setMessage("修复bug");
            normalDialog.setPositiveButton("下载最新版",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            downLoad();
                        }
                    });
            normalDialog.setNegativeButton("忽略此版本",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //...To-do
                        }
                    });
            // 显示
            normalDialog.show();
        }
    }

    private void retrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VerisonApi.Urls)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        VerisonApi verisonApi = retrofit.create(VerisonApi.class);

        Observable<ResponseBody> data = verisonApi.getDataVerisons();

        data.subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        long max = responseBody.contentLength();
                        saveFile(inputStream, sd + "/" + "abc456.apk", max);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveFile(InputStream inputStream, final String string, long max) {

        //读写的进度
        long count = 0;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(string));

            int length = -1;
            byte[] bys = new byte[1024 * 10];

            while ((length = inputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, length);

                count += length;

                Log.d(TAG, "progress: " + count + "    max:" + max);
            }

            inputStream.close();
            outputStream.close();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();

                    InstallUtil.installApk(getActivity(), string);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void downLoad() {
        retrofit();
        ToastUtil.showShort("正在下载");
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
