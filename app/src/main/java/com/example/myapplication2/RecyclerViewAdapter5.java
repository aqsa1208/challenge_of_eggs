package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.MyViewHolder> {
    Context mContext;
    public static List<Mail> mData;
    public static List<Friend> listFriend;

    public RecyclerViewAdapter5(Context mContext, List<Mail> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter5.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.mail_list_item, parent, false);
        final RecyclerViewAdapter5.MyViewHolder vHolder = new RecyclerViewAdapter5.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv.setText(mData.get(position).getName() + " wants to be your friend");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_mail;
        private TextView tv;
        private Button accept, delete;

        public MyViewHolder(View itemView){
            super(itemView);

            item_mail = (LinearLayout) itemView.findViewById(R.id.mail_item);
            tv = (TextView) itemView.findViewById(R.id.mail_context);
            accept = (Button) itemView.findViewById(R.id.accept_btn);
            delete = (Button) itemView.findViewById(R.id.delete_btn);

            itemView.findViewById(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getPosition();
                    Toast.makeText(item_mail.getContext(), "accept " + position + " " + mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                    //成功加好友//要送id給server
                    ClientService.sendMessage("i"+mData.get(position).getId());
                    mData.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mData.size());
                    if(mData.size() == 0){
                        //((Activity)mContext).finish();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.putExtra("friend", 1);
                        mContext.startActivity(intent);
                    }
               /*     try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ClientService.sendMessage("t");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int friendnum = Integer.parseInt(Data.get_totalfriend());
                    String[] mark_s = new String[friendnum];
                    String[] friendeggnum = new String[friendnum];
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("eggimage", Context.MODE_PRIVATE);
                    Log.d("friendnum", String.valueOf(friendnum));
                    if(friendnum>0){

                        String[] name = Data.get_eachfriendname();
                        String[] id = Data.get_eachfriendid();
                        mark_s = Data.get_eachfriendmarkegg();
                        friendeggnum = Data.get_eachfriendeggnum();

                        int[] mark = new int[friendnum];
                        int[] addfriend_eggtotal = new int[friendnum];
                        for (int i = 0; i < friendnum; i++){
                            Log.d("name", name[i]);
                            Log.d("id", id[i]);
                            mark[i] = Integer.parseInt(mark_s[i]);
                            addfriend_eggtotal[i] = Integer.parseInt(friendeggnum[i]);
                            Log.d("mark", mark_s[i]);
                        }


                        listFriend = new ArrayList<>();
                        for(int i = 0; i < friendnum; i++) {
                            //如果沒有mark蛋server會傳-1
                            listFriend.add(new Friend(name[i], id[i], sharedPreferences.getInt(mark_s[i],0), addfriend_eggtotal[i]));  //圖片為固定的彈頭貼
                        }


                    }else{
                        listFriend = new ArrayList<>();
                    }*/
                }
            });
        }
    }
}
