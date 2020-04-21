package com.example.basilemauvieux.td2_listview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    //Question 2.3
    public void thread(View view) {
        //thread de l'ui
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == ImageThread.MESSAGE_UPDATE_MOVIE) {
                    movieAdapter.notifyDataSetChanged();
                }
            }
        };

        for (int i = 0; i < this.movieAdapter.getCount(); i++) {
            Thread thread = new Thread(new ImageThread(this.movieAdapter.getItem(i), handler));

            thread.start();
        }

    }

    //Question 2.4
    public void executor(View view)
    {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == ImageThread.MESSAGE_UPDATE_MOVIE) {
                    movieAdapter.notifyDataSetChanged();
                }
            }
        };

        //Dire le nombre de coeurs sur lesquels mapper les thread
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < this.movieAdapter.getCount(); i++) {
            executorService.execute(new ImageThread(this.movieAdapter.getItem(i), handler));
        }
    }

    //Questio2.5
    public void weakReferencies(View view) {
        
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
                .setAfficheUrl("https://picsum.photos/300/300")
                .setCout(120301);
        ;
        movie1.setAffiche(this.getBitmapFromURL(movie1.getAfficheUrl()));


        Movie movie2 = new Movie();
        movie2
                .setAnnee(1997)
                .setNom("Star wars")
                .setProducteur("Quelqu'un")
                .setRealisateur("Abadon Marus")
                .setAfficheUrl("https://picsum.photos/300/300")
                .setCout(5441233);
        ;
        movie2.setAffiche(this.getBitmapFromURL(movie2.getAfficheUrl()));


        Movie movie3 = new Movie();
        movie3
                .setAnnee(1996)
                .setNom("Hérésie d'Horus")
                .setProducteur("Roboute Guli")
                .setRealisateur("Samuel Jackson")
                .setAfficheUrl("https://picsum.photos/300/300")
                .setCout(1231242131);
        ;

        movie3.setAffiche(this.getBitmapFromURL(movie3.getAfficheUrl()));


        Movie movie4 = new Movie();
        movie4
                .setAnnee(2002)
                .setNom("Le seigneur des anneaux")
                .setProducteur("Peter Jackson")
                .setRealisateur("Peter Jackson")
                .setAfficheUrl("https://picsum.photos/300/300")
                .setCout(123042159);
        ;

        movie4.setAffiche(this.getBitmapFromURL(movie4.getAfficheUrl()));

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        return movies;
    }

    private Bitmap getBitmapFromURL(String src) {
        //Pas le moyen le plus opti
        //Mais le seul que j'ai trouvé pour faire fonctionner le tout
        System.setProperty("http.keepAlive", "false");
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream input = connection.getInputStream();

            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

}