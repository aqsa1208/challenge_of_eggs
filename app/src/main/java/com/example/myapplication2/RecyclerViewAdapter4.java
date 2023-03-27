package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class RecyclerViewAdapter4 extends RecyclerView.Adapter<RecyclerViewAdapter4.MyViewHolder> {
    Context mContext;
    List<Egg> mData;

    public RecyclerViewAdapter4(Context mContext, List<Egg> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter4.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.egg_list_item, parent, false);
        final RecyclerViewAdapter4.MyViewHolder vHolder = new RecyclerViewAdapter4.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(mData.get(position).getName());
        holder.img_egg.setImageResource(mData.get(position).getPhoto());
        holder.item_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EggHome.class);
                intent.putExtra("name",mData.get(position).getName());
                intent.putExtra("level",mData.get(position).getLevel());
                intent.putExtra("image",mData.get(position).getPhoto());
                intent.putExtra("bar",mData.get(position).getBar());
                intent.putExtra("num",mData.get(position).getNum());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_egg;
        private ImageView img_egg;
        private TextView name;

        public MyViewHolder(View itemView){
            super(itemView);

            item_egg = (LinearLayout) itemView.findViewById(R.id.egg_item);
            img_egg = (ImageView) itemView.findViewById(R.id.img_egg_item);
            name = (TextView) itemView.findViewById(R.id.egg_name);
        }
    }
}
