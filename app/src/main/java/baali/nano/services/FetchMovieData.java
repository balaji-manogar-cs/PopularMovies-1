package baali.nano.services;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import baali.nano.BuildConfig;

/**
 * Created by Balaji on 05/01/16.
 */
public class FetchMovieData extends AsyncTask<Void, Void, String>
{
    private String TAG = FetchMovieData.class.getSimpleName();

    @Override
    protected String doInBackground(Void... params)
    {
        String location = "http://api.themoviedb.org/3/discover/movie?api_key="+ BuildConfig.THE_MOVIE_DB_API_KEY +"&sort_by=popularity.desc";
        HttpURLConnection connection;
        BufferedReader reader;
        try {
            URL url = new URL(location);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream is = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (is == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            String jsonString = buffer.toString();

            Log.d(TAG, "doInBackground: " + jsonString);


        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
