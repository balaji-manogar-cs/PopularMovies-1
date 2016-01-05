package baali.nano;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import baali.nano.config.MovieFetchOptions;
import baali.nano.services.FetchMovieData;
import baali.nano.utils.TheMovieDBUtils;


public class MainActivity extends AppCompatActivity
{

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStart()
    {

        Log.d(TAG, "onStart: " + getApplicationContext().getResources().getString(R.string.q_api_key));
        init();
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.d(TAG, "onCreate: " + BuildConfig.THE_MOVIE_DB_API_KEY);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init()
    {
        TheMovieDBUtils movieUtil = new TheMovieDBUtils(getApplicationContext());
        FetchMovieData movieData = new FetchMovieData();
        movieData.execute();
        Log.d(TAG, "init: " +  movieUtil.getMovieURL(MovieFetchOptions.Popular));
    }
}
