package com.example.lenovo.demo29.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.HomeParticularsBean;
import com.example.lenovo.demo29.ui.main.activity.HomeParticularsActivity;

import java.util.ArrayList;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/14
 * 项目工作空间：Demo29
 */
public class HomeParticularsAdapter extends RecyclerView.Adapter{
    private HomeParticularsActivity mContext;
    private ArrayList<HomeParticularsBean.ResultBean> mList;

    public HomeParticularsAdapter(HomeParticularsActivity homeParticularsActivity, ArrayList<HomeParticularsBean.ResultBean> list) {
        this.mContext = homeParticularsActivity;
        this.mList = list;
    }

    public void setList(ArrayList<HomeParticularsBean.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_particulars_bean, null, false);
            return new ViewHolder(inflate);
        }else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_touxiang, null, false);
            return new ViewHolder2(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            ViewHolder viewHolder = (ViewHolder) holder;
            HomeParticularsBean.ResultBean.RouteBean route = mList.get(position).getRoute();
            String cardURL = route.getCardURL();
            Glide.with(mContext).load(cardURL).placeholder(R.mipmap.zhanweitu_home_kapian).into(viewHolder.mHomes);
        }else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            HomeParticularsBean.ResultBean.BanmiBean banmi = mList.get(position).getBanmi();
            String photo = banmi.getPhoto();
            String name = banmi.getName();
            String location = banmi.getLocation();
            String introduction = banmi.getIntroduction();
            String occupation = banmi.getOccupation();
            viewHolder2.mTv_Names.setText(name);
            viewHolder2.mTv_r.setText(location);
            viewHolder2.mTv_x.setText(occupation);
            viewHolder2.mTv_xx.setText(introduction);
            Glide.with(mContext).load(photo).placeholder(R.mipmap.zhanweitu_home_kapian).into(viewHolder2.mTouXiang);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mHomes;

        public ViewHolder(View itemView) {
            super(itemView);
            mHomes = itemView.findViewById(R.id.iv_homes);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{

        private final ImageView mTouXiang;
        private final TextView mTv_Names;
        private final TextView mTv_x;
        private final TextView mTv_r;
        private final TextView mTv_xx;

        public ViewHolder2(View itemView) {
            super(itemView);
            mTouXiang = itemView.findViewById(R.id.iv_touxiang);
            mTv_x = itemView.findViewById(R.id.tv_x);
            mTv_r = itemView.findViewById(R.id.tv_r);
            mTv_xx = itemView.findViewById(R.id.tv_xx);
            mTv_Names = itemView.findViewById(R.id.tv_names);
        }
    }
}
