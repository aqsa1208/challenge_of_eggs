package com.example.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FragmentShop extends Fragment {

    //private ClientThread clientThread;
    //private Thread thread;
    Dialog myDialog;
    Context mContext;
    View v;
    private Button btn;
    private Object Drawable;
    int num = 14,money_int=0;
    String money ;
    TextView money_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);
        //thread.start();

        v = inflater.inflate(R.layout.fragment_shop, container, false);
        btn = v.findViewById(R.id.buy_btn);
        money_tv = v.findViewById(R.id.tv_money_shop);

       //clientThread.sendMessage("m"+"how much");
        //ClientService.sendMessage("m");
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        money = Data.get_money();

        //money_int = Integer.parseInt(money);
        money_tv.setText(money);

        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.buy_egg);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//按下buy，要扣錢

                //ClientThread.sendMessage("g");
                ClientService.sendMessage("g");

                //money_int = money_int - 20;//買蛋要扣的錢
                //money = Integer.toString(money_int);
                //money_tv.setText(money);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //clientThread.sendMessage("k" + money);

                String eggnum = Data.get_eggnum();
                num = Integer.parseInt(eggnum);
                money = Data.get_money();

                money_tv.setText(String.valueOf(money));
                myDialog.setCancelable(false);
                Button button = (Button) myDialog.findViewById(R.id.btn_ok);
                ImageView img= myDialog.findViewById(R.id.buy_img);
                TextView egg_name = myDialog.findViewById(R.id.buy_name);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("eggimage", Context.MODE_PRIVATE);
                img.setImageResource(sharedPreferences.getInt(String.valueOf(num),0)); //從server得到的蛋編號的圖片
                SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("eggname", Context.MODE_PRIVATE);
                egg_name.setText(sharedPreferences1.getString(String.valueOf(num),""));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //按下ok

                        Intent intent = new Intent(getActivity(), EggHome.class);
                        String flag = Data.get_eggflag();
                        int flag_int = Integer.parseInt(flag);

                        if (flag_int == 0) {
                            intent.putExtra("plus_count", 0);//如果沒有一樣的蛋
                        }
                        else{
                            intent.putExtra("plus_count", 100);//如果有一樣的蛋
                        }

                        //intent.putExtra("buyegg", bytes);   //傳送random圖片，將上面random出來的圖片id傳過去
                        intent.putExtra("buyegg", num);
                        startActivity(intent);
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });
        return v;
    }

    /*public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            ClientService.sendMessage("m");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }*/


   /* @Override
    public void onStart() {
        super.onStart();
        ClientService.sendMessage("m");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

}