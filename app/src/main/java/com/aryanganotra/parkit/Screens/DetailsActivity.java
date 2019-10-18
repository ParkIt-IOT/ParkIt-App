package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanganotra.parkit.Model.Details;
import com.aryanganotra.parkit.R;
import com.aryanganotra.parkit.Singleton.SingletonClient;
import com.google.gson.Gson;

import org.json.JSONObject;

import cn.iwgang.countdownview.CountdownView;

public class DetailsActivity extends AppCompatActivity {

    TextView place, code, license;
    //CountdownView mCvCountdownView;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        place = findViewById(R.id.place);
        code = findViewById(R.id.slot_code);
        license = findViewById(R.id.license_num);
       // mCvCountdownView = (CountdownView)findViewById(R.id.cd);
        chronometer = findViewById(R.id.chronometer);

        




        String content = getIntent().getStringExtra("content");
        String license_num = getIntent().getStringExtra("license_num");
        try {
            Gson g = new Gson();
            Details details = g.fromJson(content, Details.class);
            SingletonClient.getInstance().setDetails(details);

           /* JSONObject json = new JSONObject(content);
            String place_str = json.getString("place");
            String slot_code = json.getString("slot_code");
            String time = json.getString("time");
            String id = json.getString("id");

            */

            place.setText("Place-Name: "+details.getPlace());
            code.setText("Slot-Code: "+details.getSlot_code());
            String[] strings = (details.getTime().split("/"))[1].split(":");
            //double time = Float.valueOf(strings[0])*3600 + Float.valueOf(strings[1])*60 ;
            //mCvCountdownView.start((long) time);
            license.setText(license_num);
            chronometer.start();


        }catch (Exception e)
        {
            Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("Error-JSON",e.getMessage());
        }



    }
}