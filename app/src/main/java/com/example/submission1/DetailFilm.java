package com.example.submission1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class DetailFilm extends AppCompatActivity {
    public static String EXTRA_FILM = "film";
    private Film film;
    private FavoriteHelper favoriteHelper;
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
        if (item.getItemId() == R.id.action_add_favorite) {
            if (!favoriteHelper.isExist(movie)) {
                long result = favoriteHelper.insertFavorite(film);
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star_clicked);
                    Toast.makeText(DetailFilm.this, getResources().getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailFilm.this, getResources().getString(R.string.failed_add_favorite), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DetailFilm.this, getResources().getString(R.string.favorite_is_exist), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (item.getItemId() == R.id.action_delete_favorite) {
                int result = favoriteHelper.deleteFavorite(movie.getId());
                if (result > 0) {
                    item.setIcon(R.drawable.ic_star);
                    Toast.makeText(DetailFilm.this, getResources().getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailFilm.this, getResources().getString(R.string.failed__delete_favorite), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
