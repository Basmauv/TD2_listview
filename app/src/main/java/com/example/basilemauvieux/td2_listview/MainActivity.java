package com.example.basilemauvieux.td2_listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity _this = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.giveInfosToView(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void vider(View view) {
        ListView list = findViewById(R.id.list);
        ArrayList<Movie> emptyMovieList = new ArrayList<>();
        MovieArrayAdapter movieAdapter = new MovieArrayAdapter(this, emptyMovieList);

        list.setAdapter(movieAdapter);
    }

    public void defaut(View view) {
        try {
            this.giveInfosToView(this.movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveInfosToView(ArrayList<Movie> movies) throws IOException {
        final ListView list = findViewById(R.id.list);

        movies = this.generateMovies();

        MovieArrayAdapter movieAdapter = new MovieArrayAdapter(this, movies);
        list.setAdapter(movieAdapter);

    }

    public void addMovieToList(View view)
    {
        Movie movie1 = new Movie();
        movie1
                .setAnnee(2020)
                .setNom("Je suis un ajout")
                .setProducteur("Je suis un ajout")
                .setRealisateur("Je suis un ajout")
                .setAffiche("https://picsum.photos/536/354")
                .setCout(202020202);
        ;

        movies.add(movie1);

        final ListView list = findViewById(R.id.list);
        MovieArrayAdapter movieAdapter = new MovieArrayAdapter(this, movies);
        list.setAdapter(movieAdapter);
    }

    public ArrayList<Movie> generateMovies() throws IOException {


        Movie movie1 = new Movie();
        movie1
                .setAnnee(2012)
                .setNom("Les 10 Salopards")
                .setProducteur("Tarantino")
                .setRealisateur("Tarantino")
                .setAffiche("https://picsum.photos/536/354")
                .setCout(120301);
        ;

        Movie movie2 = new Movie();
        movie2
                .setAnnee(1997)
                .setNom("Star wars")
                .setProducteur("Quelqu'un")
                .setRealisateur("Abadon Marus")
                .setAffiche("https://picsum.photos/id/237/536/354")
                .setCout(5441233);
        ;

        Movie movie3 = new Movie();
        movie3
                .setAnnee(1996)
                .setNom("Hérésie d'Horus")
                .setProducteur("Roboute Guli")
                .setRealisateur("Samuel Jackson")
                .setAffiche("https://picsum.photos/seed/picsum/536/354")
                .setCout(1231242131);
        ;

        Movie movie4 = new Movie();
        movie4
                .setAnnee(2002)
                .setNom("Le seigneur des anneaux")
                .setProducteur("Peter Jackson")
                .setRealisateur("Peter Jackson")
                .setAffiche("https://picsum.photos/id/1084/536/354?grayscale")
                .setCout(123042159);
        ;

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        return movies;
    }

    private void toUseLater() {
        //Button defaut
        /*final Button defaut = findViewById(R.id.defaut);
        defaut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ListView list = findViewById(R.id.list);
                list.setEmptyView(v);
            }
        });

        //Button ajouter
        final Button ajout = findViewById(R.id.ajout);
        vide.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                _this.addMovieToList(_this.movies);
            }
        });*/
    }

}
