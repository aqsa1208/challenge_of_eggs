package com.example.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Friend> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Friend> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        int num = 0;
        //ClientService.sendMessage("t");

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.friend_information);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name);
                TextView dialog_id_tv = (TextView) myDialog.findViewById(R.id.dialog_id);
                ImageView dialog_img = (ImageView) myDialog.findViewById(R.id.dialog_img);
                Button dialog_btn = (Button) myDialog.findViewById(R.id.dialog_btn_egg);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_id_tv.setText(mData.get(vHolder.getAdapterPosition()).getID());
                dialog_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());//這邊會跟server要mark的蛋
                dialog_btn.setText("have "  + String.valueOf(mData.get(vHolder.getAdapterPosition()).getEggTotal()) + " eggs");
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_id.setText(mData.get(position).getID());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_friend;
        private TextView tv_name;
        private TextView tv_id;
        private ImageView img;

        public MyViewHolder(View itemView){
            super(itemView);

            item_friend = (LinearLayout) itemView.findViewById(R.id.friend_item);
            tv_name = (TextView) itemView.findViewById(R.id.friend_name);
            tv_id = (TextView) itemView.findViewById(R.id.friend_id);
            img = (ImageView) itemView.findViewById(R.id.img_friend_item);
        }
    }
}
