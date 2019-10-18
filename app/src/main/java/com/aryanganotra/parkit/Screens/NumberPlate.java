package com.aryanganotra.parkit.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanganotra.parkit.R;
import com.aryanganotra.parkit.Singleton.SingletonClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NumberPlate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_plate);
       // getSupportActionBar().setTitle("Book");

        Button submt = findViewById(R.id.submt_btn);
        final EditText licence_num = findViewById(R.id.license_num);
        final TextView vacant_tv = findViewById(R.id.slot_vacant);
        final ProgressBar pb = findViewById(R.id.progress_bar);
        final Button scan_btn = findViewById(R.id.qr_btn);
        submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(licence_num.getText()!=null && !licence_num.getText().toString().isEmpty()){
                    if(SingletonClient.getInstance().getVacant().getValue()!=null && SingletonClient.getInstance().getVacant().getValue()>0) {
                        pb.setVisibility(View.VISIBLE);
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

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new IntentIntegrator(NumberPlate.this).initiateScan();

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

        SingletonClient.getInstance().getLicense_num_status().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                pb.setVisibility(View.GONE);
                scan_btn.setVisibility(View.VISIBLE);
                Toast.makeText(NumberPlate.this,s, Toast.LENGTH_LONG).show();
                Log.i("ConnectionSocket",s);
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
