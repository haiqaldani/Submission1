package com.example.submission1;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submission1.Film;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModelMovie extends ViewModel {
    private static final String API_KEY = "a636b5aee9e03575b71445739bd0c9ad";
    private MutableLiveData<ArrayList<Film>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Film>> listTVShow = new MutableLiveData<>();

    void setMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Film> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Film movieItems = new Film();
                        String poster = "https://image.tmdb.org/t/p/original/";
                        movieItems.setPoster(poster + movie.getString("poster_path"));
                        movieItems.setId(movie.getInt("id"));
                        movieItems.setJudul(movie.getString("title"));
                        movieItems.setTahun(movie.getString("release_date"));
                        movieItems.setGenre(movie.getString("original_title"));
                        movieItems.setDetail(movie.getString("overview"));

                        listItems.add(movieItems);
                    }
                    listMovie.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure",error.getMessage());
            }
        });

    }

    void setTVShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Film> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Film movieItems = new Film();
                        String poster = "https://image.tmdb.org/t/p/original/";
                        movieItems.setPoster(poster + movie.getString("poster_path"));
                        movieItems.setId(movie.getInt("id"));
                        movieItems.setJudul(movie.getString("name"));
                        movieItems.setTahun(movie.getString("first_air_date"));
                        movieItems.setGenre(movie.getString("original_name"));
                        movieItems.setDetail(movie.getString("overview"));

                        listItems.add(movieItems);
                    }
                    listTVShow.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure",error.getMessage());
            }
        });

    }

    LiveData<ArrayList<Film>> getMovie(){
        return listMovie;
    }

    LiveData<ArrayList<Film>> getTVShow(){
        return listTVShow;
    }

}
