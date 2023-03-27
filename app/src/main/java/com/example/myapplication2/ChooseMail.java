package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseMail extends AppCompatActivity {
    View v;
    private RecyclerView myrecyclerview;
    private int mailnum;
    private List<Mail> listMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mail);

        mailnum = Integer.parseInt(Data.get_allmailnum());

        if(mailnum>0){
            listMail = new ArrayList<>();
            String[] name = Data.get_eachmailname();//{"Hawkeye", "Kevin"};
            String[] id = Data.get_eachmailid();
            for(int i = 0; i < name.length; i++) {
                listMail.add(new Mail(name[i],id[i]));
            }
        }
        else{
            listMail = new ArrayList<>();
        }


        myrecyclerview = findViewById(R.id.mail_recyclerview);
        RecyclerViewAdapter5 recyclerAdapter = new RecyclerViewAdapter5(ChooseMail.this, listMail);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myrecyclerview.setAdapter(recyclerAdapter);
    }
}