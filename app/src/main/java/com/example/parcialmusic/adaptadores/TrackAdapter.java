package com.example.parcialmusic.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialmusic.R;
import com.example.parcialmusic.entidades.Track;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{
    public final List<Track> list;
    private LayoutInflater layoutInflater;


    public TrackAdapter(List<Track> list, Context context) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado,null);
        return new TrackViewHolder(v,this);
    }


    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = list.get(position);
        holder.nombre.setText(track.name);
        holder.duration.setText(track.duration);
        holder.artist.setText(track.artist.name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre;
        private TextView artist;
        private TextView duration;
        private TrackAdapter trackAdapter;
        public TrackViewHolder(@NonNull View itemView, TrackAdapter trackAdapter) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombres);
            artist = itemView.findViewById(R.id.artist);
            duration = itemView.findViewById(R.id.duracion);
            this.trackAdapter = trackAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
