package com.example.myapplication2;

import android.util.Log;

public class Data {
    private static String id;
    private static String server_check_challenge_start;
    private static String money;
    private static String eggnum;
    private static String upeggnum;
    private static String eggplus;
    private static String allegg;
    private static String totaleggnum;
    private static String[] eacheggnum = {} ;
    private static String[] eachegglevel = {};
    private static String checkfriendid;
    private static String eggflag;
    private static String challengeflag;
    private static String friend;
    private static String totalfriend;
    private static String[] eachfriendid;
    private static String[] eachfriendname;
    private static String[] eachfriendmarkegg;
    private static String[] eachfriendeggnum;
    private static String allmailnum;
    private static String[] eachmailname;
    private static String[] eachmailid;
    private static String nowchallenge;
    private static String allfinishflag;
    private static String posttotalnum;
    private static String postname;
    private static String postnum;
    //private static String imgstring;
   // private static boolean imgflag = false;



    public static String get_id() {
        return id;
    }
    public static String get_server_check_challenge_start() {
        return server_check_challenge_start;
    }
    public static String get_money() {
        return money;
    }
    public static String get_eggnum() {
        return eggnum;
    }
    public static String get_upeggnum() {
        return upeggnum;
    }
    public static String get_eggflag() {
        return eggflag;
    }
    public static String get_eggplus() {
        return eggplus;
    }
    public static String get_totaleggnum() {
        return totaleggnum;
    }
    public static String[] get_eacheggnum() {
        return eacheggnum;
    }
    public static String[] get_eachegglevel() {
        return eachegglevel;
    }
    public static String get_chechfriendid() {
        return checkfriendid;
    }
    public static String get_challengeflag() {
        return challengeflag;
    }
    public static String get_totalfriend() {
        return totalfriend;
    }
    public static String[] get_eachfriendid(){return eachfriendid;}
    public static String[] get_eachfriendname(){return eachfriendname;}
    public static String[] get_eachfriendmarkegg(){return eachfriendmarkegg;}
    public static String[] get_eachfriendeggnum(){return eachfriendeggnum;}
    public static String get_allmailnum(){return allmailnum;}
    public static String[] get_eachmailname(){return eachmailname;}
    public static String[] get_eachmailid(){return eachmailid;}
    public static String get_nowchallenge(){return nowchallenge;}
    public static String get_allfinishflag(){return allfinishflag;}
    public static String get_posttotalnum(){return posttotalnum;}
    public static String get_postname(){return postname;}
    public static String get_postnum(){return postnum;}
    //public static String get_imgstring(){return imgstring;}
    //public static boolean get_imgflag(){return imgflag;}


    public static void setA(String a) {
        String cmd = a.substring(0,1);
        String str = a.substring(1);
        switch (cmd){
            case "0":
                Data.id = str;
                break;
            case "p":
                Data.server_check_challenge_start = str;
                //Log.d("s",str);
                break;
            case "m":
                Data.money = str;
                //Log.d("money", money);
                break;
            case "g":
                Data.eggnum = str;
                String[] getegg = eggnum.split("\\.");
                Data.eggnum = getegg[0];
                Data.eggflag = getegg[1];
                Data.money = getegg[2];

                break;
            case "u":
                Data.upeggnum = str;
                break;
            case "e":
                Data.eggplus = str;
                String[] neweggplus = eggplus.split("\\.");
                Data.server_check_challenge_start = neweggplus[0];
                Data.eggplus = neweggplus[1];
                Data.money = neweggplus[2];
                break;
            case "t":
                Data.friend = str;
                Log.d("friend", str);
                String[] total_f = friend.split("\\+");
                Data.totalfriend = total_f[0];
                if(!totalfriend.equals("0")){
                    String[] eachfriend = new String[Integer.parseInt(totalfriend)];
                    if(!totalfriend.equals("1")){
                        eachfriend = total_f[1].split("\\-");
                    }
                    else {
                        eachfriend[0] = total_f[1];
                    }
                    eachfriendid = new String[Integer.parseInt(totalfriend)];
                    eachfriendname = new String[Integer.parseInt(totalfriend)];
                    eachfriendmarkegg = new String[Integer.parseInt(totalfriend)];
                    eachfriendeggnum = new String[Integer.parseInt(totalfriend)];
                    for(int i =0;i<Integer.parseInt(totalfriend);i++){
                        String[] id_name_egg = eachfriend[i].split("\\.");
                        eachfriendid[i] = id_name_egg[0];
                        eachfriendname[i] = id_name_egg[1];
                        eachfriendmarkegg[i] = id_name_egg[2];
                        eachfriendeggnum[i] = id_name_egg[3];
                    }
                }
                break;


            case "a":
                Data.allegg = str;
                Log.d("allegg", allegg);
                String[] total = allegg.split("\\+");
                Data.totaleggnum = total[0];

                String[] eachegg = new String[Integer.parseInt(totaleggnum)];

                if(!totaleggnum.equals("1")){
                    eachegg = total[1].split("\\-");
                }else{
                    eachegg[0] = total[1];
                }

                int total_int = Integer.parseInt(totaleggnum);
                eacheggnum = new String[15];
                eachegglevel = new String[15];
                for(int i=0;i<total_int;i++){
                    String[] num_level = eachegg[i].split("\\.");
                    //Log.d("num+level", num_level[0]);
                    Data.eacheggnum[i] = num_level[0];
                    //Log.d("num", eacheggnum[i]);
                    Data.eachegglevel[i] = num_level[1];
                    //Log.d("level", eachegglevel[i]);
                }
                break;

            case "w":
                String allmail = str;
                String[] mail = allmail.split("\\+");
                Data.allmailnum = mail[0];
                if(!allmailnum.equals("0")){
                    String[] eachmail = new String[Integer.parseInt(allmailnum)];
                    if(!allmailnum.equals("1")){
                        eachmail = mail[1].split("\\-");
                    }
                    else{
                        eachmail[0] = mail[1];
                    }
                    eachmailname = new String[Integer.parseInt(allmailnum)];
                    eachmailid = new String[Integer.parseInt(allmailnum)];
                    for(int i =0;i<Integer.parseInt(allmailnum);i++){
                        String[] mail_id_name = eachmail[i].split("\\.");
                        eachmailid[i] = mail_id_name[0];
                        eachmailname[i] = mail_id_name[1];
                    }
                }
                break;

            case "r":
                Data.checkfriendid = str;
                break;
            case "f":
                String[] allfinish = str.split("\\.");
                Data.allfinishflag = allfinish[1];
                Data.money = allfinish[0];
                break;
            case "c":
                Data.challengeflag = str;
                String[] flags = challengeflag.split("\\.");
                Data.challengeflag = flags[0];
                Data.nowchallenge = flags[1];
                break;
            case "h":
                String[] share = str.split("\\.");
                Data.postname = share[0];
                Data.postnum = share[1];
                //Data.imgflag = true;
                break;

            case "x":
                Data.posttotalnum = str;
                break;
        }
    }
    /*public static void setImage(String a){
        Data.imgstring = a;
        Log.d("img", a);
    }
    public static void setImageflag(boolean b){
        Data.imgflag = b;
    }*/

}
