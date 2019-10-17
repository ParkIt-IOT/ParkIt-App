package com.aryanganotra.parkit.Singleton;

import com.aryanganotra.parkit.Socket.Client;

public class SingletonClient {

    private static SingletonClient instance = null;

    public static SingletonClient getInstance(){
        if (instance == null){
            instance = new SingletonClient();
        }
        return instance;
    }

    private SingletonClient(){
        Client client = new Client("192.168.3.113",10000);
        Thread th = new Thread(client);
        th.start();
    }
}
