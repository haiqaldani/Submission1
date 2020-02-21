package com.example.submission1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class DetailFilm extends AppCompatActivity {
    public static String EXTRA_FILM = "film";
    
    TextView judul, sinopsis, genre, tahun;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_film);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setId();
    }

    private void setId() {
        judul = findViewById(R.id.judul_detail);
        poster = findViewById(R.id.poster_detail);
        sinopsis = findViewById(R.id.sinopsis_detail);
        genre = findViewById(R.id.genre_detail);
        tahun = findViewById(R.id.tahun_detail);

        Film film = getIntent().getParcelableExtra(EXTRA_FILM);
        assert film != null;
        Glide.with(this).load(film.getPoster())
                .apply(new RequestOptions().centerCrop())
                .into(poster);
        judul.setText(film.getJudul());
        tahun.setText(film.getTahun());
        genre.setText(film.getGenre());
        sinopsis.setText(film.getDetail());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
