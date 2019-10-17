package com.aryanganotra.parkit.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryanganotra.parkit.Model.Park;
import com.aryanganotra.parkit.R;

import java.util.ArrayList;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Park> parks;

    public ParkAdapter(Context context, ArrayList<Park> parks){

        this.context = context;
        this.parks = parks;

    }

    public ArrayList<Park> getParks(){
        return this.parks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.park,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(parks.get(i).getCode());
            if(parks.get(i).isParked()){
                viewHolder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.car));
            }
            else {
                viewHolder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.green));
            }
    }

    @Override
    public int getItemCount() {
        return parks.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
            iv = (ImageView)itemView.findViewById(R.id.iv);

        }
    }
}
