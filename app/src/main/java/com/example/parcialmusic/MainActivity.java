package com.example.parcialmusic;

import android.content.Intent;
import android.os.Bundle;

import com.example.parcialmusic.adaptadores.TrackAdapter;
import com.example.parcialmusic.api.CancionApp;
import com.example.parcialmusic.entidades.Playlist;
import com.example.parcialmusic.entidades.Track;
import com.example.parcialmusic.entidades.Tracks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static List<Track> listTracks = new ArrayList<>();
    private RecyclerView recyclerTrack;
    private TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  AddActivity.class);
                startActivity(intent);
            }
        });
        traerPlaylist();

    }

    private void traerPlaylist() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CancionApp cancionApp = retrofit.create(CancionApp.class);
        Call<Playlist> call = cancionApp.getCancion();
        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                Playlist playlist = response.body();
                Tracks tracks = playlist.getTracks();
                listTracks = tracks.getTrack();

                recyclerTrack = (RecyclerView) findViewById(R.id.recyclerTrack);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                recyclerTrack.setLayoutManager(llm);


                trackAdapter = new TrackAdapter(listTracks,MainActivity.this);
                recyclerTrack.setAdapter(trackAdapter);


            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
