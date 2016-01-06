package baali.nano.services;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Balaji on 05/01/16.
 */
public class FetchMovieData extends AsyncTask<String, Void, String>
{
    private String TAG = FetchMovieData.class.getSimpleName();

    @Override
    protected String doInBackground(String... params)
    {
        String location = params[0];
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String data = null;

        data = getData(location, reader);


        return data;
    }

    private String getData(String location, BufferedReader reader)
    {
        String jsonString = null;
        HttpURLConnection connection;
        connection = getConnection(location);

        InputStream is = getInputStream(connection);

        if (is != null) {
            reader = getReader(is);
            String line;
            jsonString = getResponseData(reader);

        }

        closeConnection(connection);
        closeReader(reader);
        return jsonString;
    }


    @NonNull
    private HttpURLConnection getConnection(String location)
    {
        HttpURLConnection connection = null;
        URL url = null;
        try {
            url = new URL(location);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection(HttpURLConnection connection)
    {
        if (connection != null) {

            connection.disconnect();
        }
    }

    public InputStream getInputStream(HttpURLConnection connection)
    {
        InputStream is = null;
        try {
            is =  connection.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return is;
    }


    @Nullable
    private String getResponseData(BufferedReader reader)
    {
        String line;
        StringBuffer buffer = new StringBuffer();
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (buffer.length() == 0) {
            return null;
        }

        String jsonString = buffer.toString();
        return jsonString;
    }

    @NonNull
    private BufferedReader getReader(InputStream is)
    {
        return new BufferedReader(new InputStreamReader(is));
    }

    private void closeReader(BufferedReader reader)
    {
        try {
            if (reader != null) {
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
