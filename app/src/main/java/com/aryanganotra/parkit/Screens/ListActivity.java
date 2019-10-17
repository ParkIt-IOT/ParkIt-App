package com.aryanganotra.parkit.Screens;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.aryanganotra.parkit.Adapters.Adapter;
import com.aryanganotra.parkit.Model.Place;
import com.aryanganotra.parkit.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {

    private SearchView sw;
    private ListView lw;
    private ArrayList<Place> places;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

       // getSupportActionBar().setTitle("Search");

        sw = (SearchView) findViewById(R.id.sw);
        lw = (ListView) findViewById(R.id.listView1);

        places = new ArrayList<>();
        places.add(new Place("Kashmere Gate","0.5 km away"));
        places.add(new Place("ISBT","0.6 km away"));
        places.add(new Place("Lal Quila","2 km away"));
        places.add(new Place("Chandni Chowk","3 km away"));
        places.add(new Place("Jama Masjid","5 km away"));
        places.add(new Place("Daryaganj","6 km away"));
        places.add(new Place("ITO","6 km away"));
        places.add(new Place("Pragati Maidan","6.2 km away"));
        places.add(new Place("India Gate","7 km away"));
        places.add(new Place("Cannaught Place","8 km away"));
        places.add(new Place("Lodhi Garden","8 km away"));
        places.add(new Place("Nizamuddin","8.1 km away"));
        places.add(new Place("Jangpura","8.5 km away"));
        places.add(new Place("Ashram","9 km away"));
        places.add(new Place("Lajpat Nagar","11 km away"));
        places.add(new Place("South Ex","14 km away"));
        places.add(new Place("INA","14 km away"));
        places.add(new Place("Sarojini Nagar","14 km away"));
        places.add(new Place("Moti Bagh","15 km away"));

        adapter = new Adapter(ListActivity.this, places);
        lw.setAdapter(adapter);

        lw.setTextFilterEnabled(true);
        lw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ListActivity.this, ListActivity.class));
                Log.i("Selected","yes");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setupSearchView();

    }

    private void setupSearchView(){
        sw.setIconifiedByDefault(false);
        sw.setOnQueryTextListener(this);
        sw.setSubmitButtonEnabled(true);
        sw.setQueryHint("Search Place");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(TextUtils.isEmpty(query)){
            lw.clearTextFilter();
        }
        else {
            lw.setFilterText(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
