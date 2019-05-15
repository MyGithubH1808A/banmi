package com.example.lenovo.demo29.ui.main.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.HomeParticularsBean;
import com.example.lenovo.demo29.net.HomeParticularsApi;
import com.example.lenovo.demo29.ui.main.adapter.HomeParticularsAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class HomeParticularsActivity extends AppCompatActivity {

    private static final String TAG = HomeParticularsActivity.class.getName();
    private ImageView mIv;
    private Toolbar mToolBar;
    private Toolbar mToobar;
    private ArrayList<HomeParticularsBean.ResultBean> mList;
    private HomeParticularsAdapter mAdapter;
    private RecyclerView mRlv;
    private int mId;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_particulars);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HomeParticularsApi.Url)
                .build();
        HomeParticularsApi homeParticularsApi = retrofit.create(HomeParticularsApi.class);
        Observable<HomeParticularsBean> homeDatas = homeParticularsApi.getHomeDatas(mId);
        homeDatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeParticularsBean value) {
//                        HomeParticularsBean.ResultBean.RouteBean routeBean = value.getResult().getRoute();
                        mList.add(value.getResult());
//                        mLists.add(value.getResult().getBanmi());
//                        mList.addAll((Collection<? extends HomeParticularsBean.ResultBean.RouteBean>) routeBean);
                        mAdapter = new HomeParticularsAdapter(HomeParticularsActivity.this,mList);

                        mAdapter.setList(mList);
                        mRlv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);

        Intent intent = getIntent();
        mId = intent.getIntExtra("id",0);
//        String intro = intent.getStringExtra("intro");
//        String city = intent.getStringExtra("city");
//        String title = intent.getStringExtra("title");
//        RoundedCorners roundedCorners = new RoundedCorners(6);
//        RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//        Glide.with(this).load(name).apply(options1).into(mIv);

        mToobar = (Toolbar) findViewById(R.id.toobar);
        mToobar.setTitle("");
        mToobar.setNavigationIcon(R.drawable.share);
        setSupportActionBar(mToolBar);
        mToobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle("");
        mToolBar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToobar = (Toolbar) findViewById(R.id.toobar);
//        mTouxiang = (ImageView) findViewById(R.id.touxiang);
//        mTvName = (TextView) findViewById(R.id.tv_name);
//        mTvIntro = (TextView) findViewById(R.id.tv_intro);
//        mTvName.setText(title);
//        mTvIntro.setText(intro);
//
//        mCity = (TextView) findViewById(R.id.city);
//        mCity.setText(city);

        mRlv = findViewById(R.id.rlva);
        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRlv.setLayoutManager(manager);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        // 查询所有可以分享的Activity
        List<ResolveInfo> resInfo = this.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (!resInfo.isEmpty()) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo info : resInfo) {
                Intent targeted = new Intent(Intent.ACTION_SEND);
                targeted.setType("text/plain");
                ActivityInfo activityInfo = info.activityInfo;
                Log.v("logcat", "packageName=" + activityInfo.packageName + "Name=" + activityInfo.name);
                // 分享出去的内容
                targeted.putExtra(Intent.EXTRA_TEXT, "这是我的分享内容" + this.getPackageName());
                // 分享出去的标题
                targeted.putExtra(Intent.EXTRA_SUBJECT, "主题");
                targeted.setPackage(activityInfo.packageName);
                targeted.setClassName(activityInfo.packageName, info.activityInfo.name);
                PackageManager pm = this.getApplication().getPackageManager();
                // 微信有2个怎么区分-。- 朋友圈还有微信
                if (info.activityInfo.applicationInfo.loadLabel(pm).toString().equals("微信")) {
                    targetedShareIntents.add(targeted);
                }
            }
            // 选择分享时的标题
            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "选择分享");
            if (chooserIntent == null) {
                return;
            }
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
            try {
                startActivity(chooserIntent);
            } catch (ActivityNotFoundException ex) {

                Toast.makeText(this, "找不到该分享应用组件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
