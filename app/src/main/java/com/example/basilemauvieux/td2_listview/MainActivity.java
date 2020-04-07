package com.example.basilemauvieux.td2_listview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.giveInfosToView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveInfosToView() throws IOException {
        final ListView list = findViewById(R.id.list);

        ArrayList<Movie> movies = this.generateMovies();

        MovieArrayAdapter movieAdapter = new MovieArrayAdapter(this, movies);
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
}
