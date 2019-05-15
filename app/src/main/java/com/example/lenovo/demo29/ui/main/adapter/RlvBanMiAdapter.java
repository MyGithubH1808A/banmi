package com.example.lenovo.demo29.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.demo29.R;
import com.example.lenovo.demo29.bean.BanMiBean;

import java.util.ArrayList;

import static com.example.lenovo.demo29.utils.UIUtils.getColor;
import static com.example.lenovo.demo29.utils.UIUtils.getResources;

/**
 * 作者：周子强
 * 邮箱：1670375515@qq.com
 * 时间：2019/5/6
 * 项目工作空间：Demo29
 */
public class RlvBanMiAdapter extends RecyclerView.Adapter{
    private FragmentActivity mContext;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> mList;
    private OnItemClickListener mListener;
    private TextView mTvSendAgain;

    public RlvBanMiAdapter(FragmentActivity activity, ArrayList<BanMiBean.ResultBean.BanmiBean> list) {
        this.mContext = activity;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_banmi, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        BanMiBean.ResultBean.BanmiBean bean = mList.get(position);
        String photo = bean.getPhoto();
        String name = bean.getName();
        String location = bean.getLocation();
        String occupation = bean.getOccupation();
        int following = bean.getFollowing();
        Glide.with(mContext).load(photo).into(viewHolder.mIv);
        viewHolder.mTv_one.setText(name);
        viewHolder.mTv_two.setText(location);
        viewHolder.mTv_three.setText(occupation);
        viewHolder.mBanmi_four.setText(following+"人关注");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mIv;
        private final TextView mTv_one;
        private final TextView mTv_two;
        private final TextView mTv_three;
        private final TextView mBanmi_four;
        private final Button mBtns;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv_one = itemView.findViewById(R.id.tv_banmi_one);
            mTv_two = itemView.findViewById(R.id.tv_banmi_two);
            mTv_three = itemView.findViewById(R.id.tv_banmi_three);
            mBanmi_four = itemView.findViewById(R.id.banmi_four);
            mBtns = itemView.findViewById(R.id.btns);
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
