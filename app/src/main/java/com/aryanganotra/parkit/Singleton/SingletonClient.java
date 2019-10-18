package com.aryanganotra.parkit.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aryanganotra.parkit.Socket.Client;

public class SingletonClient {

    private static SingletonClient instance = null;
    private MutableLiveData<Integer> vacant = new MutableLiveData<>();
    private MutableLiveData<String> license_num = new MutableLiveData<>();
    private MutableLiveData<String> license_num_status = new MutableLiveData<>();
    private MutableLiveData<Boolean> connection_status = new MutableLiveData<>();

    public MutableLiveData<Boolean> getConnectionStatus () {
        return connection_status;
    }


    public MutableLiveData<Integer> getVacant () {
        return vacant;
    }

    public MutableLiveData<String> getLicense_num() {
        return license_num;
    }

    public MutableLiveData<String> getLicense_num_status() {
        return license_num_status;
    }

    public static SingletonClient getInstance(){
        if (instance == null){
            instance = new SingletonClient();
        }
        return instance;
    }

    private Client client = new Client("192.168.31.13",10000);

    public  Client getClient(){
        return client;
    }

    private SingletonClient(){

        Thread th = new Thread(client);
        th.start();

    }

}
