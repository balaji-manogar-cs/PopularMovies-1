package baali.nano.services;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import baali.nano.utils.HttpUtils;

/**
 * Created by Balaji on 05/01/16.
 */
public class FetchMovieData extends AsyncTask<String, Void, String>
{
    private final HttpUtils httpUtils = new HttpUtils();
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
        connection = httpUtils.getConnection(location);

        InputStream is = httpUtils.getInputStream(connection);

        if (is != null) {
            reader = httpUtils.getReader(is);
            String line;
            jsonString = readData(reader);

        }

        httpUtils.closeConnection(connection);
        httpUtils.closeReader(reader);
        return jsonString;
    }


    @Nullable
    private String readData(BufferedReader reader)
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

}
