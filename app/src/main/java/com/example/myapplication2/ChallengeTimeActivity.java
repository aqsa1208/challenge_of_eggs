package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChallengeTimeActivity extends AppCompatActivity {
    private Button btn, btn2, btn3;
    private TextView tv;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_time);

        tv = findViewById(R.id.challenge_name_tv);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        tv.setText(getIntent().getStringExtra("challenge"));
    }

    public void onDay(View view){
        time = 1;
        SharedPreferences sharedPreferences3= getSharedPreferences("challenge", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences3.edit();
        editor.putInt("time",time);
        editor.commit();
        String tvText = tv.getText().toString();
        Intent intent = new Intent(this, Start.class);
        intent.putExtra("challenge", tvText);
        startActivity(intent);
        finish();
    }

    public void onWeek(View view){
        time = 7;
        SharedPreferences sharedPreferences3= getSharedPreferences("challenge", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences3.edit();
        editor.putInt("time",time);
        editor.commit();
        String tvText = tv.getText().toString();
        Intent intent = new Intent(this, Start.class);
        intent.putExtra("challenge", tvText);
        startActivity(intent);
        finish();
    }

    public void onMonth(View view){
        time = 30;
        SharedPreferences sharedPreferences3= getSharedPreferences("challenge", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences3.edit();
        editor.putInt("time",time);
        editor.commit();
        String tvText = tv.getText().toString();
        Intent intent = new Intent(this, Start.class);
        intent.putExtra("challenge", tvText);
        startActivity(intent);
        finish();
    }
}