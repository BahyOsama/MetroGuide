package com.example.metroguide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MetroRouteAdapter extends RecyclerView.Adapter<MetroRouteAdapter.ViewHolder> {

    private final List<String> metroStations;

    public MetroRouteAdapter(List<String> metroStations) {
        this.metroStations = metroStations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_metro_station, parent, false);
        return new ViewHolder(view);
        
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String station = metroStations.get(position);
        holder.stationNameTextView.setText(station);
    }

    @Override
    public int getItemCount() {
        return metroStations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stationNameTextView;

        public ViewHolder(View view) {
            super(view);
            stationNameTextView = view.findViewById(R.id.stationNameTextView);
            
        }
    }
}