package com.example.lenovo.demo29.ui.main.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.demo29.R;

public class SplashActivity extends AppCompatActivity {

    //是否为第一次使用
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    //读取SharedPreferences中需要的数据
                    SharedPreferences preferences = getSharedPreferences("isFirst", MODE_WORLD_READABLE);
                    isFirst = preferences.getBoolean("isFirst", true);
                    if (isFirst) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));//引导界面
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));//登录界面
                    }
                    finish();
                    //实例化Editor对象
                    SharedPreferences.Editor editor = preferences.edit();
                    //存入数据
                    editor.putBoolean("isFirst", false);
                    //提交修改
                    editor.commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
