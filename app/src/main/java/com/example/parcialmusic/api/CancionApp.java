package com.example.parcialmusic.api;

import com.example.parcialmusic.entidades.Playlist;
import com.example.parcialmusic.entidades.other.Buscar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CancionApp {

    @GET("2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<Playlist> getCancion();

    @GET("2.0")
    Call<Buscar> getBusqueda(@Query("method") String method, @Query("track") String track, @Query("api_key") String api_key,
                             @Query("format") String format);

}
