package com.example.parcialmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialmusic.api.CancionApp;
import com.example.parcialmusic.entidades.Track;
import com.example.parcialmusic.entidades.other.Buscar;
import com.example.parcialmusic.entidades.other.Results;
import com.example.parcialmusic.entidades.other.TrackString;
import com.example.parcialmusic.entidades.other.Trackmatches;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {
    private EditText nombre,artista,buscar;
    private Button btnBuscar;
    public static Activity myActivity;
    List<TrackString> lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre = (EditText)findViewById(R.id.txtCancion);
        artista = (EditText)findViewById(R.id.txtArtista);
        buscar = (EditText)findViewById(R.id.txtBuscar);

        myActivity=this;

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPost();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu mmenu) {
        getMenuInflater().inflate(R.menu.menu_add, mmenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertCanciones();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CancionApp cancionApp = retrofit.create(CancionApp.class);
        String aux = buscar.getText().toString();
        Call<Buscar> call = cancionApp.getBusqueda("track.search",aux,"b284db959637031077380e7e2c6f2775","json");
        try{
            call.enqueue(new Callback<Buscar>() {
                @Override
                public void onResponse(Call<Buscar> call, Response<Buscar> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Buscar buscar = response.body();
                    Results results = buscar.getResults();
                    Trackmatches trackmatches = results.getTrackmatches();
                    lista = trackmatches.getTrack();
                    nombre.setText(lista.get(0).getName());
                    artista.setText(lista.get(0).getArtist());
                }

                @Override
                public void onFailure(Call<Buscar> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (IllegalStateException | JsonSyntaxException exception){

        }


    }

    private void insertCanciones(){
        Track track= new Track();
        Track.Artist artist = new Track.Artist();
        artist.name = artista.getText().toString();

        track.artist = artist;
        track.name = nombre.getText().toString();
        track.duration = "INDEFINIDO";

        MainActivity.listTracks.add(track);
    }

}
