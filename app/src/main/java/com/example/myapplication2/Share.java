package com.example.myapplication2;

import android.graphics.Bitmap;

public class Share {
    private String Name;
    private String Challenge;
    private int Photo;
    //private int SharePhoto;
    private Bitmap SharePhoto;

    public Share(){

    }

    public Share(String name, String challenge, int photo, Bitmap sharePhoto){
        Name = name;
        Challenge = challenge;
        Photo = photo;
        SharePhoto = sharePhoto;
    }

    public String getName(){
        return Name;
    }

    public String getChallenge(){
        return Challenge;
    }

    public int getPhoto(){
        return Photo;
    }

    public Bitmap getSharePhoto(){
        return SharePhoto;
    }

    public void setName(String name){
        Name = name;
    }

    public void setChallenge(String challenge){
        Challenge = challenge;
    }

    public void setPhoto(int photo){
        Photo = photo;
    }

    public void setSharePhoto(Bitmap sharePhoto){
        SharePhoto = sharePhoto;
    }

}
