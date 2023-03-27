package com.example.myapplication2;

public class Egg {
    String Name, Level;
    int Photo, Bar, Num;

    public Egg(String name, String level, int photo, int bar, int num){
        Name = name;
        Level = level;
        Photo = photo;
        Bar = bar;
        Num = num;
    }

    public String getName(){
        return Name;
    }

    public String getLevel(){
        return Level;
    }

    public int getPhoto(){
        return Photo;
    }

    public int getBar(){
        return Bar;
    }

    public int getNum(){
        return Num;
    }

    public void setName(String name){
        Name = name;
    }

    public void setLevel(String level){
        Level = level;
    }

    public void setPhoto(int photo){
        Photo = photo;
    }

    public void setBar(int bar){
        Bar = bar;
    }

    public void setNum(int num){
        Num = num;
    }
}
