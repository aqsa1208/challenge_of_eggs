package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    //private ClientThread clientThread;
    //private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);
        //thread.start();

        //clientThread.sendMessage("m"+"how much");
        /*ClientService.sendMessage("m");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClientService.sendMessage("h");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClientService.sendMessage("t");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        ClientService.sendMessage("m");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } ClientService.sendMessage("t");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } ClientService.sendMessage("h");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new FragmentShop(), "Shop");
        vpAdapter.addFragment(new FragmentHome(), "Home");
        vpAdapter.addFragment(new FragmentChallenge(), "Challenge");
        vpAdapter.addFragment(new FragmentShare(), "Share");
        vpAdapter.addFragment(new FragmentFriend(), "Friend");
        viewPager.setAdapter(vpAdapter);

        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(R.drawable.egg_buttom1);}

        if(getIntent().getExtras().containsKey("indexLogin")) {
            viewPager.setCurrentItem(2);
        }

        if(getIntent().getExtras().containsKey("index")) {
            viewPager.setCurrentItem(3);
        }

        if(getIntent().getExtras().containsKey("friend")) {
            viewPager.setCurrentItem(4);
        }
    }
}