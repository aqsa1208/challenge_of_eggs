package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentFriend extends Fragment {

    //private ClientThread clientThread;
    //private Thread thread;
    View v;
    private RecyclerView myrecyclerview;
    private List<Friend> listFriend;
    private FloatingActionButton addfriend, mail;
    String addfriend_name = "Hi";
    int addfriend_mark = 10;
    int friendnum = 4;//好友總數
    int[] mark;// = {2, 9, 8, 14};
    int[] addfriend_eggtotal;
    String[] name; //= {"Pig", "Merry", "Candy", "KK"},
    String[] id;// = {"0003", "0015", "00023", "0089"};

    public FragmentFriend(){

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);
        //thread.start();

        v = inflater.inflate(R.layout.fragment_friend, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.friend_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), listFriend);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        addfriend = v.findViewById(R.id.add_friend_btn);
        mail = v.findViewById(R.id.mail_btn);



        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPhraseDialogBox();
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientService.sendMessage("w");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChooseMail.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        friendnum = Integer.parseInt(Data.get_totalfriend());
        String[] mark_s = new String[friendnum];
        String[] friendeggnum = new String[friendnum];
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("eggimage", Context.MODE_PRIVATE);
        Log.d("friendnum", String.valueOf(friendnum));

        if(friendnum>0){
            name = Data.get_eachfriendname();
            id = Data.get_eachfriendid();
            mark_s = Data.get_eachfriendmarkegg();
            friendeggnum = Data.get_eachfriendeggnum();

            mark = new int[friendnum];
            addfriend_eggtotal = new int[friendnum];

            for (int i = 0; i < friendnum; i++){
                Log.d("name", name[i]);
                Log.d("id", id[i]);
                Log.d("mark", mark_s[i]);
                Log.d("eggnum", friendeggnum[i]);
                mark[i] = Integer.parseInt(mark_s[i]);
                addfriend_eggtotal[i] = Integer.parseInt(friendeggnum[i]);
            }


            listFriend = new ArrayList<>();
            for(int i = 0; i < friendnum; i++) {
                //如果沒有mark蛋server會傳-1
                listFriend.add(new Friend(name[i], id[i], sharedPreferences.getInt(mark_s[i]/*String.valueOf(mark[i])*/,0), addfriend_eggtotal[i]));  //圖片為固定的彈頭貼
            }


        }else{
            listFriend = new ArrayList<>();
        }

        //for(int i = 0; i < friendnum; i++) {
          //  listFriend.add(new Friend(name[i], id[i], sharedPreferences.getInt(String.valueOf(mark[i]),0), addfriend_eggtotal));  //圖片為固定的彈頭貼
        //}
    }

    private void addPhraseDialogBox(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.MyDialog);//不確定
        alertDialog.setTitle("Add Friend");
        final EditText input = new EditText(getContext());
        alertDialog.setView(input);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("eggimage", Context.MODE_PRIVATE);
        alertDialog.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String friend_id = input.getText().toString();  //傳回server確定有此id

                if(friend_id != "") {
                   // clientThread.sendMessage("r"+friend_id);
                    ClientService.sendMessage("r"+friend_id);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int checkfriend = Integer.parseInt(Data.get_chechfriendid());
                    Log.d("check", Data.get_chechfriendid());

                    if(checkfriend == 1){
                        //跳出toast說你已經加了某某成為朋友,等待他的回覆
                        //有此人，和server索取該人的姓名、id、展示的蛋
                        //listFriend.add(new Friend(addfriend_name, friend_id, sharedPreferences.getInt(String.valueOf(addfriend_mark), 0), addfriend_eggtotal));//server回傳展示的蛋
                    }
                    else{
                        //無此人
                        Log.d("check", "Can not find this user,please enter again");
                        Toast.makeText(getContext(), "Can not find this user,please enter again", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getContext(), "ID field can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alert = alertDialog.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setLayout(800, 600);
        Toast.makeText(getContext(), "friend added", Toast.LENGTH_SHORT).show();
    }
}