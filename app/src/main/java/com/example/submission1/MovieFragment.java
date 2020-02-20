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

    private MovieAdapter movieAdapter;
    private RecyclerView RVMovie;
    private ProgressBar progressBar;
    private MainViewModelMovie mainViewModel;
    private ArrayList<Film> l = new ArrayList<>();//membuat arraybaru


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelMovie.class);
        mainViewModel.setMovie();
        mainViewModel.getMovie().observe(this, new Observer<ArrayList<Film>>() {
            @Override
            public void onChanged(final ArrayList<Film> films) {
                if (films != null){
                    movieAdapter.setData(films);
                    showLoading(false);
        OnItemClickSupport.addTo(RVMovie).setOnItemClickListener((recyclerView, position, v) ->
                showDetail(films.get(position)));
                }
            }
        });
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ip = RVMovie.getChildLayoutPosition(view);
                Film item = l.get(ip);
                Toast.makeText(getContext(), item.getJudul(), Toast.LENGTH_SHORT).show();
            }
        });

        RVMovie = rootView.findViewById(R.id.rv_movie);
        progressBar = rootView.findViewById(R.id.progressBarmovies);
        RVMovie.setHasFixedSize(true);

        RVMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, rootView.isInLayout()));
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        RVMovie.setAdapter(movieAdapter);


        return rootView;

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