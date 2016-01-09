package baali.nano;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import baali.nano.adapter.MoviePosterAdapter;
import baali.nano.config.MovieFetchOptions;
import baali.nano.model.Movie;
import baali.nano.services.FetchMovieData;
import baali.nano.utils.TheMovieDBUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  implements MainActivity.DelegateMovieAdapterProcess<Movie>
{
    private String TAG = MainActivityFragment.class.getSimpleName();
    private ArrayAdapter movieAdapter;
    private List<Movie> moviesList;
    private GridView gridMoviePoster;

    public MainActivityFragment()
    {
        moviesList = new ArrayList<>();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //FetchMovieData movieData = new FetchMovieData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        gridMoviePoster = (GridView) rootView.findViewById(R.id.grid_poster);
        load();
        return rootView;
    }

    private void load()
    {
        movieAdapter = getPosterAdapter();
        gridMoviePoster.setAdapter(movieAdapter);


        TheMovieDBUtils movieUtil = new TheMovieDBUtils(getContext());
        String requestUrl = movieUtil.buildURL(MovieFetchOptions.Popular);

        String posterBasePath = movieUtil.getStringResource(R.string.img_poster_url);
        String backdropBasePath = movieUtil.getStringResource(R.string.img_backdrop_url);


        FetchMovieData movieData = new FetchMovieData();
        movieData.setMovieDelegate(this);
        movieData.execute(requestUrl, posterBasePath, backdropBasePath);



        //Log.d(TAG, "Movie: " + moviesList.get(0));

        Log.d(TAG, "init: " + movieUtil.buildURL(MovieFetchOptions.Popular));
        Toast.makeText(getActivity(), movieUtil.buildURL(MovieFetchOptions.Popular), Toast.LENGTH_LONG).show();

    }

    private ArrayAdapter getPosterAdapter()
    {
        return new MoviePosterAdapter(getContext(), R.layout.grid_main_poster, moviesList);
    }


    @Override
    public void process(List<? extends Movie> movieList)
    {
        if(movieList.size() > 0)
        {
            movieAdapter.clear();
            movieAdapter.addAll(movieList);
        }
        else
        {
//            handle here for no movies
        }
    }


}
