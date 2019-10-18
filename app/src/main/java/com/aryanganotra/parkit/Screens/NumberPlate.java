package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aryanganotra.parkit.R;
import com.aryanganotra.parkit.Singleton.SingletonClient;

public class NumberPlate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_plate);
       // getSupportActionBar().setTitle("Book");

        Button submt = findViewById(R.id.submt_btn);
        final EditText licence_num = findViewById(R.id.license_num);
        final TextView vacant_tv = findViewById(R.id.slot_vacant);
        submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(licence_num.getText()!=null && !licence_num.getText().toString().isEmpty()){
                    if(SingletonClient.getInstance().getVacant().getValue()>0) {
                        SingletonClient.getInstance().getLicense_num().setValue("license:" + licence_num.getText().toString());
                    }
                    else{
                        licence_num.setError("No vacant spaces. Please try later.");
                    }
                    //Log.i("Called",SingletonClient.getInstance().getLicense_num().getValue());
                }
                else {
                    licence_num.setError("License plate number can't be empty");
                }
            }
        });

        SingletonClient.getInstance().getVacant().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(final Integer integer) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vacant_tv.setText("Slots available: "+String.valueOf(integer));
                    }
                });
            }
        });

        SingletonClient.getInstance().getLicense_num().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                SingletonClient.getInstance().getClient().write(s);
            }
        });




    }
}
