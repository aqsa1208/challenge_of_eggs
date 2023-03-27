package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentHome extends Fragment {

    //private ClientThread clientThread;
    //private Thread thread;
    private View v;
    private ImageView iv, iv_egg;
    Context context;
    AnimationDrawable reAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);
       // thread.start();

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        iv = (ImageView) v.findViewById(R.id.re_close);
        iv_egg = (ImageView) v.findViewById(R.id.have_egg);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打開冰箱，等點完蛋後，冰箱關上
                //iv.setImageResource(R.drawable.openre);

                iv.setImageResource(R.drawable.open_re_animation);
                reAnimation = (AnimationDrawable) iv.getDrawable();
                reAnimation.start();
                iv.setImageResource(R.drawable.openre);
                //reAnimation.stop();
                iv_egg.setVisibility(View.VISIBLE);
                iv_egg.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        //clientThread.sendMessage("a"+".");
                        ClientService.sendMessage("a"+".");

                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getActivity(), ChooseEgg.class);
                        startActivity(intent);
                    }
                });
            }
        });
        return v;
    }
}