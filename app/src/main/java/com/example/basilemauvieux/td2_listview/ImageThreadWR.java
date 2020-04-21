package com.example.basilemauvieux.td2_listview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.basilemauvieux.td2_listview.Entity.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageThreadWR implements Runnable
{
    private WeakReference<MovieArrayAdapter> weakReference;
    private Movie movie;
    private Handler handler;

    public ImageThreadWR(Movie item, MovieArrayAdapter arrayAdapter, Handler handler) {
        this.weakReference = new WeakReference<>(arrayAdapter);
        this.movie = item;
        this.handler = handler;
    }

    @Override
    public void run() {
        movie.setAffiche(this.getBitmapFromURL(movie.getAfficheUrl()));
        if (weakReference.get() != null) {
            MovieArrayAdapter adapter = weakReference.get();
            //Event bus qui tourne sur le thread UI

            Message message = new Message();
            message.what = ImageThread.MESSAGE_UPDATE_MOVIE;
            handler.sendMessage(message);
        }
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
