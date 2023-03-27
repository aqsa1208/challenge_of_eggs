package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseEgg extends AppCompatActivity {

    View v;
    public static ChooseEgg instance = null;
    private RecyclerView myrecyclerview;
    private List<Egg> listEgg;
    int eggtotal = 4;
    int level = 10; //level從server拿到的該蛋進度條
    String[] eggnum;
    String[] egglevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_egg);

        instance = this;

        String total = Data.get_totaleggnum();
        eggtotal = Integer.parseInt(total);

        eggnum = Data.get_eacheggnum();
        egglevel = Data.get_eachegglevel();
        int[] eggnum_int;//= {2, 6, 8, 12};
        eggnum_int = new int[15];
        int[] level_int;//= {30, 50, 20, 30};
        level_int = new int[15];

        for(int i=0;i<eggtotal;i++){
            eggnum_int[i] = Integer.parseInt(eggnum[i]);
            level_int[i] = Integer.parseInt(egglevel[i]);
        }


        SharedPreferences sharedPreferences2= getSharedPreferences("eggbar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        for(int i = 0; i < eggtotal; i++) {
            editor.putInt(String.valueOf(eggnum_int[i]), level_int[i]);
        }
        editor.commit();

        listEgg = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("eggimage", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("eggname", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences("egglevel", Context.MODE_PRIVATE);

        for(int i = 0; i < eggtotal; i++) { //i為第幾顆蛋, num蛋的編號
            listEgg.add(new Egg(sharedPreferences1.getString(String.valueOf(eggnum_int[i]),""), sharedPreferences3.getString(String.valueOf(eggnum_int[i]),""), sharedPreferences.getInt(String.valueOf(eggnum_int[i]),0),sharedPreferences2.getInt(String.valueOf(eggnum_int[i]),0), eggnum_int[i]));
        }

        myrecyclerview = findViewById(R.id.egg_recyclerview);
        RecyclerViewAdapter4 recyclerAdapter = new RecyclerViewAdapter4(ChooseEgg.this, listEgg);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(recyclerAdapter);
    }
}