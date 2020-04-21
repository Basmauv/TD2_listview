package com.example.basilemauvieux.td2_listview;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    public MovieArrayAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT <= 8) {
           return;
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        try {
            this.giveInfosToView(this.generateMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Question 2.1
    public void atsk(View view)
    {
        ImageAsyncTask asyncTask = new ImageAsyncTask(view, this.movieAdapter);
        asyncTask.execute();

    }

    //Question 2.2
    public void ATskExec(View view)
    {
        ImageAsyncTask asyncTask = new ImageAsyncTask(view, this.movieAdapter);
        asyncTask.executeOnExecutor(ImageAsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void vider(View view) {
        this.movieAdapter.clear();
    }

    public void defaut(View view) throws IOException
    {
        this.movieAdapter.clear();
        ArrayList<Movie> defaultMovies = this.generateMovies();
        for (int i = 0; i<this.movieAdapter.getCount(); i++) {
            System.out.println(this.movieAdapter.getItem(i));
        }

        this.movieAdapter.addAll(defaultMovies);
    }


    public void addMovieToList(View view)
    {
        Movie movie1 = new Movie();
        movie1
                .setAnnee(2020)
                .setNom("Je suis un ajout")
                .setProducteur("Je suis un ajout")
                .setRealisateur("Je suis un ajout")
                .setAfficheUrl("https://picsum.photos/seed/picsum/536/354")
                .setCout(202020202);
        ;

        this.movieAdapter.add(movie1);
    }

    public void giveInfosToView(ArrayList<Movie> movies)
    {
        final ListView list = findViewById(R.id.list);
        this.movieAdapter = new MovieArrayAdapter(this, movies);
        list.setAdapter(movieAdapter);
    }

    public ArrayList<Movie> generateMovies() throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1
                .setAnnee(2012)
                .setNom("Les 10 Salopards")
                .setProducteur("Tarantino")
                .setRealisateur("Tarantino")
                .setAfficheUrl("https://picsum.photos/id/237/536/354")
                .setCout(120301);
        ;

        Movie movie2 = new Movie();
        movie2
                .setAnnee(1997)
                .setNom("Star wars")
                .setProducteur("Quelqu'un")
                .setRealisateur("Abadon Marus")
                .setAfficheUrl("https://picsum.photos/id/237/536/354")
                .setCout(5441233);
        ;

        Movie movie3 = new Movie();
        movie3
                .setAnnee(1996)
                .setNom("Hérésie d'Horus")
                .setProducteur("Roboute Guli")
                .setRealisateur("Samuel Jackson")
                .setAfficheUrl("https://picsum.photos/id/237/536/354")
                .setCout(1231242131);
        ;

        Movie movie4 = new Movie();
        movie4
                .setAnnee(2002)
                .setNom("Le seigneur des anneaux")
                .setProducteur("Peter Jackson")
                .setRealisateur("Peter Jackson")
                .setAfficheUrl("https://picsum.photos/id/237/536/354")
                .setCout(123042159);
        ;

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        return movies;
    }
}