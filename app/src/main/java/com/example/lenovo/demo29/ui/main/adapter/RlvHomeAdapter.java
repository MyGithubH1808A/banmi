package com.example.lenovo.demo29.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/6
 * 项目工作空间：Demo29
 */
public class RlvHomeAdapter extends RecyclerView.Adapter{
    private FragmentActivity mContext;
    public ArrayList<HomeBean.ResultBean.RoutesBean> mList;
    private ArrayList<HomeBean.ResultBean.BannersBean> mListBanner;
    private OnItemClickListener mListener;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_FOOTER = 2;
    private ItemHolder mItemHolder;

    public RlvHomeAdapter(FragmentActivity activity, ArrayList<HomeBean.ResultBean.RoutesBean> list, ArrayList<HomeBean.ResultBean.BannersBean> listBanner) {
        this.mContext = activity;
        this.mList = list;
        this.mListBanner = listBanner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new BannerHolder(View.inflate(mContext, R.layout.item_banner, null));
        } else if (viewType == VIEW_TYPE_ITEM){
            return new ItemHolder(View.inflate(mContext, R.layout.item_home, null));
        }else {
            return new BundleHolder(View.inflate(mContext, R.layout.item_home, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            bannerHolder.banner.setImages(mListBanner)
                    .setDelayTime(3000)
                    .setImageLoader(new com.youth.banner.loader.ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            HomeBean.ResultBean.BannersBean path1 = (HomeBean.ResultBean.BannersBean) path;
                            Glide.with(context).load(path1.getImageURL()).into(imageView);
                        }
                    })
                    .start();
        }
        else if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            mItemHolder = (ItemHolder) holder;
            int index = position;
            if (mListBanner.size() > 0) {
                index -= 1;
            }
            HomeBean.ResultBean.RoutesBean routesBean = mList.get(index);

            mItemHolder.mPurchasedTime.setText(routesBean.getPurchasedTimes()+"");
            mItemHolder.mTv_two.setText(routesBean.getIntro());
            mItemHolder.mCity.setText(routesBean.getCity());
//            if (routesBean.isIsPurchased()) {
//                itemHolder.mBtn.setBackgroundColor(mContext.getResources().getColor(R.color.c_cecece));
//                itemHolder.mBtn.setText("已购买");
//            }
            mItemHolder.mTv_two.setText(routesBean.getCity());
            mItemHolder.mTv_one.setText(routesBean.getTitle());
            RoundedCorners roundedCorners = new RoundedCorners(3);
            RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.mipmap.zhanweitu_home_kapian)
                    .error(R.mipmap.zhanweitu_home_kapian)
                    .override(300, 300);

            Glide.with(mContext)
                    .load(routesBean.getCardURL())
                    .apply(options1)
                    .into(mItemHolder.mIv_home);
        mItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        mListener.onItemClick(v,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListBanner.size() > 0) {
            return mList.size() + 1;
        } else {
            return mList.size();
        }
    }

    /**
     *
     *
     *
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mListBanner.size() > 0) {
            return VIEW_TYPE_HEADER;
        } else {
            int index = position;
            if (mListBanner.size() > 0) {
                index -= 1;
            }
            if (mList.get(index).getType().equals("bundle")) {
                return VIEW_TYPE_FOOTER;
            } else {
                return VIEW_TYPE_ITEM;
            }
        }
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            //不显示指示器
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv_home;
        private final TextView mTv_one;
        private final TextView mTv_two;
        private final TextView mCity;
        private final TextView mPurchasedTime;
        private final Button mBtn;

        public ItemHolder(View itemView) {
            super(itemView);
            mIv_home = itemView.findViewById(R.id.iv_home);
            mTv_one = itemView.findViewById(R.id.tv_one);
            mTv_two = itemView.findViewById(R.id.tv_two);
            mCity = itemView.findViewById(R.id.city);
            mPurchasedTime = itemView.findViewById(R.id.purchasedTime);
            mBtn = itemView.findViewById(R.id.btn);
        }
    }

    class BundleHolder extends RecyclerView.ViewHolder {
        public BundleHolder(View itemView) {
            super(itemView);
        }
    }
    //接口回调
        //1.写个接口
        public interface OnItemClickListener{
           void onItemClick(View v,int position);
        }

        //2.写个方法,将OnItemClickListener设置到Adapter中
        public void setOnItemClickListener(OnItemClickListener listener){
            this.mListener = listener;
        }
}
