package com.example.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter2  extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {

    Context mContext;
    List<Share> mData;

    public RecyclerViewAdapter2(Context mContext, List<Share> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //ClientService.sendMessage("h");
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.share_list_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_challenge.setText(mData.get(position).getChallenge());
        holder.img.setImageResource(mData.get(position).getPhoto());
        holder.img_share.setImageBitmap(mData.get(position).getSharePhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_share;
        private TextView tv_name;
        private TextView tv_challenge;
        private ImageView img;
        private ImageView img_share;

        public MyViewHolder(View itemView){
            super(itemView);

            item_share = (LinearLayout) itemView.findViewById(R.id.share_item);
            tv_name = (TextView) itemView.findViewById(R.id.share_name);
            tv_challenge = (TextView) itemView.findViewById(R.id.share_challenge);
            img = (ImageView) itemView.findViewById(R.id.img_share_head);
            img_share = (ImageView) itemView.findViewById(R.id.img_share);

        }
    }
}
