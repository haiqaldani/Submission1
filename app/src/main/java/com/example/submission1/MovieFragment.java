package com.example.submission1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    public ArrayList<Film> listFilm = new ArrayList<>();
    public ProgressBar progressBar;
    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private MainViewModelMovie mainViewModel;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelMovie.class);
        mainViewModel.setMovie();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progressBarmovies);
        rvMovie.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(listFilm);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvMovie.setAdapter(listMovieAdapter);
    }

    private void showDetail(Film film) {
        Intent i = new Intent(MovieFragment.this.getActivity(), DetailFilm.class);

        Film film1 = new Film();
        film1.setJudul(film.getJudul());
        film1.setPoster(film.getPoster());
        film1.setTahun(film.getTahun());
        film1.setGenre(film.getGenre());
        film1.setDetail(film.getDetail());
        i.putExtra(DetailFilm.EXTRA_FILM, film1);

        startActivity(i);
    }
}