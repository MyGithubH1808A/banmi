package com.example.lenovo.demo29.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.ui.main.adapter.VpGuideAdapter;
import com.example.lenovo.demo29.widget.PreviewIndicator;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;
    private ArrayList<View> mList;
    private static final int[] pics = {R.drawable.guide_01, R.drawable.guide_02,
            R.drawable.guide_03};
    private LinearLayout mLl;
    /**
     * 立即体验
     */
    private Button mButton;
    private PreviewIndicator mPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initGuide();
    }

    private void initGuide() {

    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);

        mList = new ArrayList<>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            mList.add(iv);
        }
        VpGuideAdapter adapter = new VpGuideAdapter(mList);
        mVp.setAdapter(adapter);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    mPi.setVisibility(View.INVISIBLE);
                    mButton.setVisibility(View.VISIBLE);
                } else {
                    mPi.setVisibility(View.VISIBLE);
                    mButton.setVisibility(View.INVISIBLE);
                }
                mPi.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mLl = (LinearLayout) findViewById(R.id.ll);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mPi = (PreviewIndicator) findViewById(R.id.pi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
