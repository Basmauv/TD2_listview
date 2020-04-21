package com.example.basilemauvieux.td2_listview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageAsyncTask extends AsyncTask<Integer, Short, Long>
{
    private View view;
    private MovieArrayAdapter movies;

    public ImageAsyncTask(View view, MovieArrayAdapter movies)
    {
        this.view = view;
        this.movies = movies;
    }


    @Override
    protected Long doInBackground(Integer... integers)
    {
        for (int i = 0; i<this.movies.getCount(); i++) {
            Movie movie = this.movies.getItem(i);

            if (movie == null) {
               continue;
            }

            movie.setAffiche(this.getBitmapFromURL(movie.getAfficheUrl()));
        }

        return null;
    }

    protected void onProgressUpdate(Integer... progress)
    {
    }

    protected void onPostExecute(Long result)
    {
        this.movies.notifyDataSetChanged();
    }

    private Bitmap getBitmapFromURL(String src)
    {
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
