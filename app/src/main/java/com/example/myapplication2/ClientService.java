package com.example.myapplication2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientService extends Service {
    //private ClientThread clientThread;
    //private Thread thread;
    public static final int SERVERPORT = 18118;
    public static final String SERVER_IP = "211.21.92.122";
    //public static final int SERVERPORT = 2013;
    //public static final String SERVER_IP = "10.201.26.209";
    public static Socket socket;//=new Socket();
    //public static String message_send = "0" + "-1";

    //void setsendmessage(){
      //  sendMessage(message_send);
   // }

    public ClientService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        startCount();
        //setsendmessage();

        long endTime = System.currentTimeMillis() + 5*1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
        stopSelf();  // 停止Service

        return START_STICKY;
    }

    public void startCount(){
        //clientThread = new ClientThread();
        //thread = new Thread(clientThread);

        new Thread(new Runnable() {
            private BufferedReader input;

            @Override
            public void run() {
                try {
                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVERPORT);
                    while (!Thread.currentThread().isInterrupted()) {

                        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String message = input.readLine();
                        if (null == message || "Disconnect".contentEquals(message)) {
                            //Thread.interrupted();
                            break;
                        }
                        Data.setA(message);
                        /*boolean flag = Data.get_imgflag();

                        if(message.equals("zEOF")){
                            flag = false;
                            Data.setImageflag(false);
                        }

                        Log.d("jj", String.valueOf(flag));

                        if(flag==true){
                            Data.setImage(Data.get_imgstring() + message);
                        }else{
                            Data.setA(message);
                        }*/
                        Log.d("Server", message);
                    }

                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }).start();

    }

    public static void sendMessage(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null != socket) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);
                        out.println(message);
                        Log.d("send", message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void sendByte(final byte[] bytes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null != socket) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);
                        out.println(bytes);
                        //Log.d("send", bytes);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    @Override
    public void onDestroy() {
        //Log.d("HelloService", "onDestroy");
    }
}