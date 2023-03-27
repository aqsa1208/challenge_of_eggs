package com.example.myapplication2;

public class Friend {
    private String Name;
    private String ID;
    private int Photo;
    private int EggTotal;

    public Friend(){

    }

    public Friend(String name, String id, int photo, int eggTotal){
        Name = name;
        ID = id;
        Photo = photo;
        EggTotal = eggTotal;
    }

    public String getName(){
        return Name;
    }

    public String getID(){
        return ID;
    }

    public int getPhoto(){
        return Photo;
    }

    public int getEggTotal(){
        return EggTotal;
    }

    public void setName(String name){
        Name = name;
    }

    public void setID(String id){
        ID = id;
    }

    public void setPhoto(int photo){
        Photo = photo;
    }

    public void setEggTotal(int eggTotal){
        EggTotal = eggTotal;
    }
}
