package com.aryanganotra.parkit.Socket;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.Observer;

import com.aryanganotra.parkit.Singleton.SingletonClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.LogRecord;

public class Client implements Runnable {
    private Socket sock = null;
    private InputStreamReader inr;
    private BufferedReader br;
    private String ip, msg;
    private int port;
    private DataOutputStream out;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {


            while(true) {
                try {
                    sock = new Socket(InetAddress.getByName(ip), port);
                    if (sock.isConnected()) {
                        Log.i("ConnectionSocket", "Connected");
                        break;
                    }
                }
                catch (Exception e){
                    Log.i("ConnectionSocket","Failed");
                }
            }
        try {

            try {
                inr = new InputStreamReader(sock.getInputStream());
                while (true) {
                    br = new BufferedReader(inr);

                    msg = br.readLine();
                    Log.i("Message", msg);
                    if (msg.startsWith("vacant")) {
                        if(!msg.equals(SingletonClient.getInstance().getVacant().getValue()))
                        SingletonClient.getInstance().getVacant().postValue(Integer.valueOf(msg.substring(7)));
                    }
                    if (msg.startsWith("license_status")){

                        if(!msg.equals(SingletonClient.getInstance().getLicense_num_status().getValue()))
                            SingletonClient.getInstance().getLicense_num_status().postValue(msg.substring(15));
                    }
                    if (msg.startsWith("connec")){

                            SingletonClient.getInstance().getConnectionStatus().postValue(true);
                    }


                }
            }
            catch (IOException e) {
                e.printStackTrace();
                try {
                    if(sock!=null && !sock.isClosed())
                        sock.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }


            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    super.run();

                }
            };

            //thread1.start();
            //thread2.start();

            /*while (true) {
                br = new BufferedReader(inr);
                msg = br.readLine();
                Log.i("Message",msg);
                if (msg.startsWith("vacant")){
                    SingletonClient.getInstance().getVacant().postValue(Integer.valueOf(msg.substring(7)));
                }
                if(SingletonClient.getInstance().getLicense_num().getValue().startsWith("license:")){
                    Log.i("Called","yes");
                    out.writeBytes(SingletonClient.getInstance().getLicense_num().getValue());
                }
                if(sock.isClosed() || !sock.isConnected())
                {
                    Log.i("ConnectionSocket","Disconnected");
                    break;
                }
            }
            if (!sock.isClosed())
            sock.close();

             */
        }
        catch (Exception e)
        {
            Log.i("Exception",e.getMessage());
            try {
                if(sock!=null && !sock.isClosed())
                sock.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void write(final String msg) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                if (sock != null) {
                    out = new DataOutputStream(sock.getOutputStream());
                    if (out != null) {

                            out.write(msg.getBytes());

                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
