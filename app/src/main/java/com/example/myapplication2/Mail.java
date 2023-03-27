package com.example.myapplication2;

public class Mail {
    String Name;
    String Id;

    public Mail(String name,String id){
        Name = name;
        Id = id;
    }

    public String getName(){
        return Name;
    }
    public String getId(){
        return Id;
    }
    public void setName(String name){
        Name = name;
    }
    public void setId(String id){
        Id = id;
    }
}
