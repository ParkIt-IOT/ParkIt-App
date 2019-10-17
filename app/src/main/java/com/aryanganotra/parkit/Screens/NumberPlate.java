package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(licence_num.getText()!=null && !licence_num.getText().toString().isEmpty()){

                }
                else {
                    licence_num.setError("License plate number can't be empty");
                }
            }
        });

        SingletonClient.getInstance().getVacant().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i("ConnectionSocket-Vacant",String.valueOf(integer));
            }
        });
    }
}
