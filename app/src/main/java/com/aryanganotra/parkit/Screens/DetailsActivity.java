package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanganotra.parkit.Model.Details;
import com.aryanganotra.parkit.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    TextView place, code, license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        place = findViewById(R.id.place);
        code = findViewById(R.id.slot_code);
        license = findViewById(R.id.license_num);


        String content = getIntent().getStringExtra("content");
        String license_num = getIntent().getStringExtra("license_num");
        try {
            Gson g = new Gson();
            Details details = g.fromJson(content, Details.class);

           /* JSONObject json = new JSONObject(content);
            String place_str = json.getString("place");
            String slot_code = json.getString("slot_code");
            String time = json.getString("time");
            String id = json.getString("id");

            */
            place.setText("Place-Name: "+details.getPlace());
            code.setText("Slot-Code: "+details.getSlot_code());
            license.setText(license_num);

        }catch (Exception e)
        {
            Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("Error-JSON",e.getMessage());
        }



    }
}
