package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EggHome extends AppCompatActivity {

    //private ClientThread clientThread;
    //private Thread thread;
    private List<Friend> listFriend;
    private Button show, update, plus;
    private TextView money_tv, eggName, description, level;
    private ImageView egg, bar;
    int plus_count = 90, image, num = 1, upgrade_num = 13, money_int=9453;
    String money ;
    Context context;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_home);



        //lientThread = new ClientThread();
       // thread = new Thread(clientThread);
     //   thread.start();

        show = findViewById(R.id.show_btn);
        update = findViewById(R.id.upgrade_btn);
        plus = findViewById(R.id.plus_btn);
        money_tv = findViewById(R.id.tv_money);
        eggName = findViewById(R.id.egg_name);
        description = findViewById(R.id.description);
        egg = findViewById(R.id.img_egg);
        bar = findViewById(R.id.bar_img);
        level = findViewById(R.id.level);

        description.setVisibility(View.INVISIBLE);

      //  clientThread.sendMessage("m"+"how much");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        money = Data.get_money();

        money_int = Integer.parseInt(money);
        money_tv.setText(money);

        SharedPreferences sharedPreferences3 = getSharedPreferences("eggbar", Context.MODE_PRIVATE);
        plus_count = sharedPreferences3.getInt(String.valueOf(num),0);

        setBarImage();

        if(getIntent().getExtras().containsKey("buyegg")) {
            num = getIntent().getExtras().getInt("buyegg");
            SharedPreferences sharedPreferences = getSharedPreferences("eggimage", Context.MODE_PRIVATE);
            egg.setImageResource(sharedPreferences.getInt(String.valueOf(num),0));
            SharedPreferences sharedPreferences1 = getSharedPreferences("eggname", Context.MODE_PRIVATE);
            eggName.setText(sharedPreferences1.getString(String.valueOf(num),""));
            SharedPreferences sharedPreferences2 = getSharedPreferences("egglevel", Context.MODE_PRIVATE);
            level.setText(sharedPreferences2.getString(String.valueOf(num),""));
            SharedPreferences sharedPreferences4 = getSharedPreferences("eggbar", Context.MODE_PRIVATE);
            plus_count = sharedPreferences4.getInt(String.valueOf(num),0);
            setBarImage();
        }

        if(getIntent().getExtras().containsKey("plus_count")) {
            plus_count = getIntent().getExtras().getInt("plus_count");
            if (plus_count == 0) {
                bar.setImageResource(R.drawable.bar0);
            }
            if (plus_count == 100) {
                bar.setImageResource(R.drawable.bar100);
                update.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().getExtras().containsKey("firstegg")) {
            num = getIntent().getExtras().getInt("firstegg");
            SharedPreferences sharedPreferences = getSharedPreferences("eggimage", Context.MODE_PRIVATE);
            egg.setImageResource(sharedPreferences.getInt(String.valueOf(num),0));
            SharedPreferences sharedPreferences1 = getSharedPreferences("eggname", Context.MODE_PRIVATE);
            eggName.setText(sharedPreferences1.getString(String.valueOf(num),""));
            SharedPreferences sharedPreferences2 = getSharedPreferences("egglevel", Context.MODE_PRIVATE);
            level.setText(sharedPreferences2.getString(String.valueOf(num),""));
            SharedPreferences sharedPreferences4 = getSharedPreferences("eggbar", Context.MODE_PRIVATE);
            plus_count = sharedPreferences4.getInt(String.valueOf(num),0);
            setBarImage();
        }
        if(getIntent().getExtras().containsKey("name")) {
            ChooseEgg.instance.finish();
            text = getIntent().getExtras().getString("name");
            eggName.setText(text);
        }
        if(getIntent().getExtras().containsKey("level")) {
            text = getIntent().getExtras().getString("level");
            level.setText(text);
        }
        if(getIntent().getExtras().containsKey("image")) {
            image = getIntent().getExtras().getInt("image");
            egg.setImageResource(image);
        }
        if(getIntent().getExtras().containsKey("bar")) {
            plus_count = getIntent().getExtras().getInt("bar");
            setBarImage();
        }
        if(getIntent().getExtras().containsKey("num")) {
            num = getIntent().getExtras().getInt("num");
        }
    }

    public void setBarImage(){
        switch (plus_count){ //依進度條值決定該對應的趴數圖片
            case 10:
                bar.setImageResource(R.drawable.bar10);
                break;
            case 20:
                bar.setImageResource(R.drawable.bar20);
                break;
            case 30:
                bar.setImageResource(R.drawable.bar30);
                break;
            case 40:
                bar.setImageResource(R.drawable.bar40);
                break;
            case 50:
                bar.setImageResource(R.drawable.bar50);
                break;
            case 60:
                bar.setImageResource(R.drawable.bar60);
                break;
            case 70:
                bar.setImageResource(R.drawable.bar70);
                break;
            case 80:
                bar.setImageResource(R.drawable.bar80);
                break;
            case 90:
                bar.setImageResource(R.drawable.bar90);
                break;
            case 100:
                bar.setImageResource(R.drawable.bar100);
                update.setVisibility(View.VISIBLE);
                plus.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void onShow(View view){
        ClientService.sendMessage("k"+String.valueOf(num));
        Toast.makeText(EggHome.this,"Mark it!",Toast.LENGTH_SHORT).show();
        //傳給server mark值, 哪顆蛋
        ClientService.sendMessage("t");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int friendnum = Integer.parseInt(Data.get_totalfriend());
        String[] mark_s = new String[friendnum];
        String[] friendeggnum = new String[friendnum];
        SharedPreferences sharedPreferences = this.getSharedPreferences("eggimage", Context.MODE_PRIVATE);
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
                listFriend.add(new Friend(name[i], id[i], sharedPreferences.getInt(mark_s[i]/*String.valueOf(mark[i])*/,0), addfriend_eggtotal[i]));  //圖片為固定的彈頭貼
            }


        }else{
            listFriend = new ArrayList<>();
        }
    }

    public void onUpgrade(View view){
       //random進化的蛋
      //  clientThread.sendMessage("u"+num);
        ClientService.sendMessage("u"+String.valueOf(num));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String upeggnum = Data.get_upeggnum();
        upgrade_num = Integer.parseInt(upeggnum);
        num = upgrade_num;

        SharedPreferences sharedPreferences = getSharedPreferences("eggimage", Context.MODE_PRIVATE);
        update.setVisibility(View.INVISIBLE);  //進化完再把update隱藏
        plus.setVisibility(View.VISIBLE);
        egg.setImageResource(sharedPreferences.getInt(String.valueOf(upgrade_num),0));  //改成進化後的蛋，依random值
        SharedPreferences sharedPreferences1 = getSharedPreferences("eggname", Context.MODE_PRIVATE);
        eggName.setText(sharedPreferences1.getString(String.valueOf(upgrade_num),""));
        SharedPreferences sharedPreferences2 = getSharedPreferences("egglevel", Context.MODE_PRIVATE);
        level.setText(sharedPreferences2.getString(String.valueOf(upgrade_num),""));
        bar.setImageResource(R.drawable.bar0);  //新的蛋的進度條為0
        plus_count = 0;

        //要記得把蛋清單更新
    }

    public void onPlus(View view){
        //和server要進度條的值(plus_count)
        int ee = 0;
        plus.setVisibility(View.INVISIBLE);  //按一下隱藏，等收到request再顯示出來
      //  clientThread.sendMessage("e"+num);
        String num_s = String.valueOf(num);
        ClientService.sendMessage("e"+num_s);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String eggplus = Data.get_eggplus();

        plus_count = Integer.parseInt(eggplus);

        SharedPreferences sharedPreferences= getSharedPreferences("eggbar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(num), plus_count);
        editor.commit();

        money = Data.get_money();
        money_tv.setText(money);
        String flag = Data.get_server_check_challenge_start();
        ee = Integer.parseInt(flag);

        if(ee!=0){
            plus.setVisibility(View.VISIBLE);
        }
        //plus_count+=10;
        setBarImage();
        if(num == 15 && plus_count == 100){
            update.setVisibility(View.INVISIBLE);
            plus.setVisibility(View.INVISIBLE);
        }
    }
/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            ChooseEgg.instance.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}