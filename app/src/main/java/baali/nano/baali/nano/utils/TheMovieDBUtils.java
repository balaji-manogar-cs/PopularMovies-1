package baali.nano.baali.nano.utils;

import android.content.res.Resources;

/**
 * Created by Balaji on 05/01/16.
 */
public class TheMovieDBUtils
{
    private  String baseURL = "test";
    private Resources resources;

    TheMovieDBUtils(Resources resources)
    {
        this.resources = resources;
    }

    public  String getMovieURL( )
    {

        return baseURL;
    }
}
