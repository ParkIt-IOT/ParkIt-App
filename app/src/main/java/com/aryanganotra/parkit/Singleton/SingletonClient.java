package com.aryanganotra.parkit.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aryanganotra.parkit.Socket.Client;

public class SingletonClient {

    private static SingletonClient instance = null;
    private MutableLiveData<Integer> vacant = new MutableLiveData<>();
    private MutableLiveData<String> license_num = new MutableLiveData<>();

    public MutableLiveData<Integer> getVacant () {
        return vacant;
    }

    public MutableLiveData<String> getLicense_num() {
        return license_num;
    }

    public static SingletonClient getInstance(){
        if (instance == null){
            instance = new SingletonClient();
        }
        return instance;
    }

    private SingletonClient(){

        final Client client = new Client("192.168.3.113",10000);
        Thread th = new Thread(client);
        th.start();

        getLicense_num().observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                client.write(s);
            }
        });
    }

}
