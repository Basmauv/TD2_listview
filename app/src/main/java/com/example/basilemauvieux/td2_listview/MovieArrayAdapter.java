package com.example.basilemauvieux.td2_listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.example.basilemauvieux.td2_listview.Entity.Movie;




public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private Context mContext;

    MovieArrayAdapter(@NonNull Context context, ArrayList<Movie> list) {
        super(context, R.layout.movie_layout , list);
        mContext = context;
        ArrayList<Movie> moviesList = list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.movie_layout, parent,false);
        }

        Movie currentMovie = getItem(position);

        if (currentMovie == null) {
            return null;
        }

        ImageView image = (ImageView)listItem.findViewById(R.id.icon);
      // image.setImageBitmap(getBitmapFromURL(currentMovie.getAffiche()));

        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentMovie.getNom());

        TextView annee = (TextView) listItem.findViewById(R.id.annee);
        annee.setText(currentMovie.getAnnee());

        TextView cout = (TextView) listItem.findViewById(R.id.cout);
        cout.setText(currentMovie.getCout());

        TextView producteur = (TextView) listItem.findViewById(R.id.producteur);
        producteur.setText(currentMovie.getRealisateur());

        TextView realisateur = (TextView) listItem.findViewById(R.id.realisateur);
        realisateur.setText(currentMovie.getRealisateur());





        return listItem;
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            return null;
        }
    }
}