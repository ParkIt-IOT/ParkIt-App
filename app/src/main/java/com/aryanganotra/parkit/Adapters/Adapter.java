package com.aryanganotra.parkit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.aryanganotra.parkit.Model.Place;
import com.aryanganotra.parkit.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter implements Filterable {
    public Context context;
    public ArrayList<Place> places;
    public ArrayList<Place> orig;

    public Adapter(Context context, ArrayList<Place> places ){
        super();
        this.context = context;
        this.places = places;
    }

    public class AdapterHolder
    {
        TextView place;
        TextView dist;
    }


    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder = new AdapterHolder();
            holder.place = (TextView)convertView.findViewById(R.id.txtName);
            holder.dist = (TextView)convertView.findViewById(R.id.txtAge);
            convertView.setTag(holder);
        }
        else {
            holder = (AdapterHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, context.getClass()));
                Log.i("Selected","yes");
            }
        });

        holder.place.setText(places.get(position).getPlace());
        holder.dist.setText(places.get(position).getDist());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Place> results = new ArrayList<>();
                if(orig == null) orig = places;
                if (constraint!=null || !constraint.toString().isEmpty()){
                    if(orig != null && orig.size() > 0){
                        for(final Place g: orig){
                            if (g.getPlace().toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                else{
                    oReturn.values = orig;
                }

                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                places = (ArrayList<Place>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
