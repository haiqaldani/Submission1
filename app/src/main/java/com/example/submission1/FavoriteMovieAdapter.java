package com.example.submission1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder> {

    private ArrayList<Film> film;
    private Context context;

    public ArrayList<Film> getFilm() {
        return film;
    }

    public void setFilm(ArrayList<Film> film) {
        this.film = film;
    }

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Film> items) {
        film.clear();
        film.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_film, viewGroup, false);
        return new FavoriteMovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.tvJudul.setText(film.get(i).getJudul());
        movieViewHolder.tvTahun.setText(film.get(i).getTahun());
        movieViewHolder.tvGenre.setText(film.get(i).getGenre());
        Glide.with(context).load(film.get(i).getPoster())
                .into(movieViewHolder.imgPhoto);

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailFilm.class);
                intent.putExtra(DetailFilm.EXTRA_FILM, getFilm().get(i));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return film.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvJudul;
        TextView tvTahun;
        TextView tvGenre;
        ImageView imgPhoto;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.judul_film);
            tvTahun = itemView.findViewById(R.id.tahun_film);
            tvGenre = itemView.findViewById(R.id.genre_film);
            imgPhoto = itemView.findViewById(R.id.gbr_film);
        }
    }
}
