package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    //private ClientThread clientThread;
    //private Thread thread;
    private Button start, giveup;
    private TextView tv, tvTime, tvCongra, tvTime24;
    private ImageButton sharebtn;
    int challenge_num, time;
    String one = "1";
    String zero = "0";
    String negone = "-1";
    String three = "3";
    String two = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //clientThread = new ClientThread();
       // thread = new Thread(clientThread);
       // thread.start();
        //判斷是否還在挑戰中
        ClientService.sendMessage("c");

        start = findViewById(R.id.start_finish);
        tv = findViewById(R.id.challenge_name_start_tv);
        tvCongra = findViewById(R.id.congradulation);
        tvTime24 = findViewById(R.id.time_24);
        sharebtn = findViewById(R.id.share_btn);
        giveup = findViewById(R.id.give_up_btn);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String flag = Data.get_challengeflag();

        if(flag.equals(one)){
            start.setVisibility(View.VISIBLE);
            start.setText("finish");
        }else if(flag.equals(zero)){
            tvCongra.setText("Is Challenging");
            giveup.setVisibility(View.VISIBLE);
            start.setVisibility(View.INVISIBLE);
        }else if(flag.equals(negone)){
            tvCongra.setText("Fail!");
            tvTime24.setText("I'm so sorry about to hear that T_T");
            start.setVisibility(View.INVISIBLE);
            giveup.setVisibility(View.INVISIBLE);
        }else if(flag.equals(three)){
            tvCongra.setText("Congratulation!");
            tvTime24.setText("Please wait 24 hours for next challenge");
        }else if(flag.equals(two)){
            start.setVisibility(View.VISIBLE);
            start.setText("start");
        }


        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this, Camera.class);
                startActivity(intent);
                finish();
            }
        });

        tv.setText(getIntent().getStringExtra("challenge"));
    };

    public void onStart(View view){
        //按start後變finish
        giveup.setVisibility(View.VISIBLE);
        String text = start.getText().toString();
        String s = "start", f = "finish";
        if(text.equals(s)) {
            SharedPreferences sharedPreferences2 = getSharedPreferences("challenge", Context.MODE_PRIVATE);
            challenge_num = sharedPreferences2.getInt("num",0);  //傳送challenge編號
            time = sharedPreferences2.getInt("time",0);  //傳送短中長期
            start.setVisibility(View.INVISIBLE);
            start.setText("finish");
            //clientThread.sendMessage("p"+challenge_num+"."+time);
            //ClientService.message_send = "p"+challenge_num+"."+time;
            ClientService.sendMessage("p"+challenge_num+"."+time);
            // 等收到server傳回來的可以按finish的訊號顯示出finish按鈕
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Data.get_challengeflag()=="1"){
                start.setVisibility(View.VISIBLE);
            }
        }
        if(text.equals(f)) {
            giveup.setVisibility(View.VISIBLE);
            start.setVisibility(view.INVISIBLE);
            start.setText("start");
            SharedPreferences sharedPreferences2 = getSharedPreferences("challenge", Context.MODE_PRIVATE);
            challenge_num = sharedPreferences2.getInt("num",0);
            ClientService.sendMessage("f"+challenge_num);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String flag = Data.get_allfinishflag();
            if(flag.equals(zero)){
                tvTime24.setText("Great!You get 20 dollars today!");
            }
            else if(flag.equals(one)){
                tvCongra.setText("Congratulation!");
                tvTime24.setText("Please wait 24 hours for next challenge");
                giveup.setVisibility(View.INVISIBLE);
                sharebtn.setVisibility(view.VISIBLE);
            }
        }
    }
    public void onGiveUp(View view){
        ClientService.sendMessage("v");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Start.this, MainActivity.class);
        intent.putExtra("indexLogin",1);
        startActivity(intent);
        finish();
    }
}