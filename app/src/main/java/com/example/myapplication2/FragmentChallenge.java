package com.example.myapplication2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentChallenge extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Challenge> listChallenge;

    public FragmentChallenge(){

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout fo
        //    @Overrider this fragment
        v = inflater.inflate(R.layout.fragment_challenge, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.challenge_recyclerview);
        RecyclerViewAdapter3 recyclerAdapter = new RecyclerViewAdapter3(getContext(), listChallenge);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        listChallenge = new ArrayList<>();
        listChallenge.add(new Challenge("30 push-ups"));
        listChallenge.add(new Challenge("50 push-ups"));
        listChallenge.add(new Challenge("80 push-ups"));
        listChallenge.add(new Challenge("Jogging for an hour"));
        listChallenge.add(new Challenge("Run 800 meters"));
        listChallenge.add(new Challenge("Run 1600 meters"));
        listChallenge.add(new Challenge("Go mountain climbing"));
        listChallenge.add(new Challenge("Exercise for an hour"));
        listChallenge.add(new Challenge("Exercise for two hour"));
        listChallenge.add(new Challenge("Go to gym for an hour"));
        listChallenge.add(new Challenge("Do the laundry"));
        listChallenge.add(new Challenge("Cook meals by yourself today"));
        listChallenge.add(new Challenge("Keep a diary"));
        listChallenge.add(new Challenge("No smartphone today"));
        listChallenge.add(new Challenge("Tidy up the room"));
        listChallenge.add(new Challenge("Clean up the kitchen"));
        listChallenge.add(new Challenge("Make breakfast by yourself"));
        listChallenge.add(new Challenge("Mop the floor"));
        listChallenge.add(new Challenge("Cook dinner for your family"));
        listChallenge.add(new Challenge("Throw the garbage"));
    }
}