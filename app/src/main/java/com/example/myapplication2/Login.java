package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    //private ClientThread clientThread;
    //private Thread thread;
    private TextView user_id;
    private EditText user_name;
    private ImageView imgView;
    public static Handler messageHandler;

    Button ok, start;
    String idFlag, id = " ", name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(Login.this,ClientService.class);
        startService(intent);


        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);
        //thread.start();

        //讀取id
        if(read()==""){
            idFlag = "-1";  //傳回給server表示使用者是第一次使用
            //id = "003";  //server回傳id
            SharedPreferences sharedPreferences= getSharedPreferences("eggimage", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("1",R.drawable.harley_quinn);
            editor.putInt("2",R.drawable.iron_man);
            editor.putInt("3",R.drawable.seal);
            editor.putInt("4",R.drawable.dynamic_superman);
            editor.putInt("5",R.drawable.luffy);
            editor.putInt("6",R.drawable.zoro);
            editor.putInt("7",R.drawable.winnie);
            editor.putInt("8",R.drawable.piglet);
            editor.putInt("9",R.drawable.eeyore);
            editor.putInt("10",R.drawable.captain_american);
            editor.putInt("11",R.drawable.black_panther);
            editor.putInt("12",R.drawable.hulk);
            editor.putInt("13",R.drawable.batman);
            editor.putInt("14",R.drawable.spiderman);
            editor.putInt("15",R.drawable.hawkeye);
            //editor.clear();
            //editor.apply();
            editor.commit();
            SharedPreferences sharedPreferences1= getSharedPreferences("egglevel", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            editor1.putString("1","D");
            editor1.putString("2","D");
            editor1.putString("3","D");
            editor1.putString("4","D");
            editor1.putString("5","D");
            editor1.putString("6","C");
            editor1.putString("7","C");
            editor1.putString("8","C");
            editor1.putString("9","C");
            editor1.putString("10","B");
            editor1.putString("11","B");
            editor1.putString("12","B");
            editor1.putString("13","A");
            editor1.putString("14","A");
            editor1.putString("15","S");
            //editor1.clear();
            //editor1.apply();
            editor1.commit();
            SharedPreferences sharedPreferences2= getSharedPreferences("eggname", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
            editor2.putString("1","Harley Quinn");
            editor2.putString("2","Iron Man");
            editor2.putString("3","Seal");
            editor2.putString("4","Dynamic Superman");
            editor2.putString("5","Luffy");
            editor2.putString("6","Zoro");
            editor2.putString("7","Winnie");
            editor2.putString("8","Piglet");
            editor2.putString("9","Eeyore");
            editor2.putString("10","Captain American");
            editor2.putString("11","Black Panther");
            editor2.putString("12","Hulk");
            editor2.putString("13","Batman");
            editor2.putString("14","Spiderman");
            editor2.putString("15","Hawkeye");
            //editor2.clear();
            //editor2.apply();
            editor2.commit();
            SharedPreferences sharedPreferences3= getSharedPreferences("eggbar", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor3 = sharedPreferences3.edit();
            for(int i = 1; i <= 15; i++){
                editor3.putInt(String.valueOf(i),0);
            }
            editor3.commit();
            SharedPreferences sharedPreferences4= getSharedPreferences("eggdiscript", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor4 = sharedPreferences4.edit();
            editor4.putString("1","Party time ~~~~");
            editor4.putString("2","Love you 3000…….");
            editor4.putString("3","Seal");
            editor4.putString("4","Dynamic Superman");
            editor4.putString("5"," I'm gonna be king of the Pirates !");
            editor4.putString("6","Scars on the back are a swordsman’s shame.");
            editor4.putString("7","Winnie");
            editor4.putString("8","Piglet");
            editor4.putString("9","Eeyore");
            editor4.putString("10"," I can do this all day !");
            editor4.putString("11","Wakanda Forever !");
            editor4.putString("12","I'm always angry !");
            editor4.putString("13","I’m The Goddamn Batman.");
            editor4.putString("14","Mr. Stark? I don’t feel so good…I don’t know what’s happening. I don’t know… I don’t want to go……");
            editor4.putString("15","The city is flying, we’re fighting an army of robots, and I have a bow and arrow. None of this makes sense.");
            //editor4.clear();
            //editor4.apply();
            editor4.commit();
            SharedPreferences sharedPreferences5= getSharedPreferences("challenge", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor5 = sharedPreferences5.edit();
            editor5.putString("1","30 push-ups");
            editor5.putString("2","50 push-ups");
            editor5.putString("3","80 push-ups");
            editor5.putString("4","Jogging for an hour");
            editor5.putString("5","Run 800 meters");
            editor5.putString("6","Run 1600 meters");
            editor5.putString("7","Go mountain climbing");
            editor5.putString("8","Exercise for an hour");
            editor5.putString("9","Exercise for two hour");
            editor5.putString("10","Go to gym for an hour");
            editor5.putString("11","Do the laundry");
            editor5.putString("12","Cook meals by yourself today");
            editor5.putString("13","Keep a diary");
            editor5.putString("14","No smartphone today");
            editor5.putString("15","Tidy up the room");
            editor5.putString("16","Clean up the kitchen");
            editor5.putString("17","Make breakfast by yourself");
            editor5.putString("18","Mop the floor");
            editor5.putString("19","Cook dinner for your family");
            editor5.putString("20","Throw the garbage");
            //editor5.clear();
            //editor5.apply();
            editor5.commit();
        }
        //傳送idFlag給server
        else{
            idFlag = read();
            id = idFlag;
            //clientThread.sendMessage("0"+idFlag);
            ClientService.sendMessage("0"+idFlag);
            //ClientService.message_send = "0"+idFlag;
        } //從server得到id，寫進檔案
        //clear();

        user_id = findViewById(R.id.login_id);
        user_name = findViewById(R.id.login_name);
        ok = findViewById(R.id.login_ok);
        start = findViewById(R.id.login_start);
        imgView = (ImageView)findViewById(R.id.logo);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int first = 1;
                name = user_name.getText().toString();
                //user_name.setText(null);
                if(!name.equals("")){
                    ClientService.sendMessage("n" + name);
                    //ClientService.message_send = "n" + name;
                    write(id, name, first);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("indexLogin",1);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Login.this, "name field can not be empty\nplease enter again", Toast.LENGTH_SHORT);
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按下start
                //clientThread.sendMessage("0" + idFlag);

                /*messageHandler = new Handler(){
                    public void handleMessage(Message msg){
                        super.handleMessage(msg);

                    }
                };*/
                ClientService.sendMessage("0" + idFlag);

                //ClientService.message_send = "0" + idFlag;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                id = Data.get_id();

                if(idFlag.equals("-1")){
                    Toast.makeText(Login.this, idFlag + " " + name, Toast.LENGTH_SHORT).show();
                    user_id.setText(id);
                    start.setVisibility(View.INVISIBLE);
                    imgView .setVisibility(View.GONE);
                }
                else if(!(idFlag.equals("-1"))){
                    SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                    name = sharedPreferences.getString("name","no");
                    Toast.makeText(Login.this, id+" "+idFlag+" "+name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("indexLogin",1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean write(String s, String name, int first){
        if (s.length() == 0) return false;
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",s);
        editor.putString("name",name);
        editor.putInt("first",first);
        return editor.commit();
    }

    private String read(){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("id","");
    }

    private void clear(){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}