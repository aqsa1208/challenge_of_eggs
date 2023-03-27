package com.example.myapplication2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentShare extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Share> listShare;
    Bitmap bmp;
    int shareTotal = 4;
    //String[] name = {"Hoho", "Haha", "Hihi", "Hehe"};
    String name;
    String[] challenge = {"Go mountain climbing", "Keep a diary", "Cook meals by yourself today", "Throw the garbage"};

    public FragmentShare(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout fo
        //    @Overrider this fragment

        v = inflater.inflate(R.layout.fragment_share, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.share_recyclerview);
        RecyclerViewAdapter2 recyclerAdapter = new RecyclerViewAdapter2(getContext(), listShare);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        shareTotal = Integer.parseInt(Data.get_posttotalnum());



        if(shareTotal>0){
            //接收圖片

            /*if (stringArray != null) {
                String[] split = stringArray.substring(1, stringArray.length()-1).split(", ");
                byte[] array = new byte[split.length];
                for (int i = 0; i < split.length; i++) {
                    array[i] = Byte.parseByte(split[i]);
                }
                bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
            }*/

            //name = new String[shareTotal];
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            name = sharedPreferences.getString("name","");
            challenge = new String[shareTotal];

            listShare = new ArrayList<>();

            for(int i = 0; i < shareTotal; i++){

                //name[i] = Data.get_postname();
                challenge[i] = Data.get_postnum();
                String stringArray;
                //stringArray = Data.get_imgstring().substring(5);
                //Log.d("name", name[i]);
                Log.d("challenge", challenge[i]);
                //Log.d("stringarray", stringArray);

             /*   try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


                SharedPreferences settings1 = this.getActivity().getSharedPreferences("challenge", 0);
                SharedPreferences settings = this.getActivity().getSharedPreferences("ShareImage", 0);
                //SharedPreferences.Editor editor = settings.edit();
                //editor.putString("ChallengeName",stringArray);
                //editor.commit();

                stringArray = settings.getString(String.valueOf(i), null);

                //editor.clear();
                //editor.apply();

                if (stringArray != null) {
                    String[] split = stringArray.substring(1, stringArray.length()-1).split(", ");
                    byte[] array = new byte[split.length];
                    for (int j = 0; j < split.length; j++) {
                        array[j] = Byte.parseByte(split[j]);
                    }
                    bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
                }
                listShare.add(new Share(name, settings1.getString(challenge[i], ""), R.drawable.egg_buttom1, bmp));

            }
        }else{
            listShare = new ArrayList<>();
        }


    }
}