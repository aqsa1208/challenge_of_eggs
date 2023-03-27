package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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

public class RecyclerViewAdapter3  extends RecyclerView.Adapter<RecyclerViewAdapter3.MyViewHolder> {

    Context mContext;
    List<Challenge> mData;
    Dialog myDialog, myDialog2, myDialog3;
    int first_num = 3, first = 0;

    public RecyclerViewAdapter3(Context mContext, List<Challenge> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.challenge_list_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("eggimage", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = this.mContext.getSharedPreferences("eggname", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = this.mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        first = sharedPreferences2.getInt("first",0);

        if(first == 1){
            first = 0;
            SharedPreferences sharedPreferences3= this.mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences3.edit();
            editor.putInt("first",first);
            editor.commit();

            myDialog = new Dialog(mContext);
            myDialog.setContentView(R.layout.firsttime_egg);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView first_dialog_name = (TextView) myDialog.findViewById(R.id.first_dialog_name);
            TextView first_dialog_tv = (TextView) myDialog.findViewById(R.id.first_dialog_tv);
            ImageView first_dialog_img = (ImageView) myDialog.findViewById(R.id.first_dialog_img);
            Button first_dialog_ok = (Button) myDialog.findViewById(R.id.first_dialog_ok);

            first_dialog_name.setText(sharedPreferences1.getString(String.valueOf(first_num),""));
            first_dialog_img.setImageResource(sharedPreferences.getInt(String.valueOf(first_num),0));
            myDialog.show();
            first_dialog_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(mContext, EggHome.class);
                    //intent.putExtra("firstegg", first_num);
                    myDialog.dismiss();
                }
            });
        }
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_challenge.setText(mData.get(position).getChallenge());

    /*    myDialog2 = new Dialog(mContext);
        myDialog2.setContentView(R.layout.challenge_choose);
        myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView challenge_ask = (TextView) myDialog2.findViewById(R.id.challenge_ask);
        Button yes = (Button) myDialog2.findViewById(R.id.choose_yes);
        Button no = (Button) myDialog2.findViewById(R.id.choose_no);*/

        myDialog3 = new Dialog(mContext);
        myDialog3.setContentView(R.layout.can_not_pick_challenge);
        myDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView challenge_no = (TextView) myDialog3.findViewById(R.id.challenge_no);

        holder.item_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //position是0,1,2,3...
                //Toast.makeText(mContext, String.valueOf(position), Toast.LENGTH_SHORT).show();
                ClientService.sendMessage("c");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String num = Data.get_challengeflag();
                String nowchallengenum = Data.get_nowchallenge();

                String nownum_s = String.valueOf(position + 1);
                String zero = "0";
                String two = "2";
                String neg = "00";
                Log.d("num", Data.get_challengeflag());
                Log.d("nowchallengenum", Data.get_nowchallenge());

                if(nowchallengenum.equals(zero)&&num.equals(two)){
                    SharedPreferences sharedPreferences3= mContext.getSharedPreferences("challenge", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences3.edit();
                    editor.putInt("num",position + 1);
                    editor.commit();
                    Intent intent = new Intent(mContext, ChallengeTimeActivity.class);
                    intent.putExtra("challenge",mData.get(position).getChallenge());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }else if(nowchallengenum.equals(nownum_s)){
                    Intent intent = new Intent(mContext, Start.class);
                    intent.putExtra("challenge",mData.get(position).getChallenge());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }else if(nowchallengenum.equals(neg)&&num.equals(two)){
                    myDialog3.show();
                }
                else{
                    myDialog2 = new Dialog(mContext);
                    myDialog2.setContentView(R.layout.challenge_choose);
                    myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    TextView challenge_ask = (TextView) myDialog2.findViewById(R.id.challenge_ask);
                    Button yes = (Button) myDialog2.findViewById(R.id.choose_yes);
                    Button no = (Button) myDialog2.findViewById(R.id.choose_no);

                    challenge_ask.setText("You are still challenging~ Do you want to quit?");
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ClientService.sendMessage("v");
                            SharedPreferences sharedPreferences3= mContext.getSharedPreferences("challenge", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences3.edit();
                            editor.putInt("num",position + 1);
                            editor.commit();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(mContext, ChallengeTimeActivity.class);
                            intent.putExtra("challenge",mData.get(position).getChallenge());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                            myDialog2.dismiss();
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(mContext, "no", Toast.LENGTH_SHORT).show();
                            myDialog2.dismiss();
                        }
                    });
                    myDialog2.show();
               /*     yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
                            //放棄正在挑戰中的挑戰，改選目前選的挑戰
                            ClientService.sendMessage("v");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(mContext, ChallengeTimeActivity.class);
                            intent.putExtra("challenge",mData.get(position).getChallenge());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                            myDialog2.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "no", Toast.LENGTH_SHORT).show();
                            myDialog2.dismiss();
                        }
                    });*/
                }
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
        private LinearLayout item_challenge;
        private TextView tv_challenge;

        public MyViewHolder(View itemView){
            super(itemView);

            item_challenge = (LinearLayout) itemView.findViewById(R.id.challenge_item);
            tv_challenge = (TextView) itemView.findViewById(R.id.challenge);
        }
    }
}
