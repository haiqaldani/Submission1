package com.example.submission1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Film> list_tvshow = new ArrayList<>();
    private onItemKlik onItemKlik;

    public void setData(ArrayList<Film> items) {
        list_tvshow.clear();
        list_tvshow.addAll(items);
        notifyDataSetChanged();
    }

    void setOnItemKlik(onItemKlik onItemKlik) {
        this.onItemKlik = onItemKlik;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_film, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(list_tvshow.get(position));
    }

    @Override
    public int getItemCount() {
        return list_tvshow.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView JudulFilm;
        TextView TahunFilm;
        TextView GenreFilm;
        ImageView GambarFilm;

        public MovieViewHolder(View view) {
            super(view);
            JudulFilm = itemView.findViewById(R.id.judul_film);
            TahunFilm = itemView.findViewById(R.id.tahun_film);
            GenreFilm = itemView.findViewById(R.id.genre_film);
            GambarFilm = itemView.findViewById(R.id.gbr_film);

        }

        public void bind(Film film) {
            Glide.with(itemView.getContext())
                    .load(film.getPoster())
                    .into(GambarFilm);
            JudulFilm.setText(film.getJudul());
            GenreFilm.setText(film.getGenre());
            TahunFilm.setText(film.getTahun());
        }
    }

    public interface onItemKlik{
        void onItemClicked(Film film);
    }
}
