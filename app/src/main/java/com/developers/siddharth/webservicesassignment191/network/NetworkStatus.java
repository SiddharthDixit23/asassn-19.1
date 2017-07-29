package com.developers.siddharth.webservicesassignment191.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by siddharth on 7/19/2017.
 */

public class NetworkStatus {
    private static NetworkStatus instance = new NetworkStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public static NetworkStatus getInstance(Context ctx)
    {
        context = ctx;
        return instance;
    }

    public boolean isOnline(Context con)
    {
        try
        {
            connectivityManager = (ConnectivityManager)con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo!=null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(con,"Check network connection it's unavailable",Toast.LENGTH_SHORT).show();
        }
        return connected;
    }

    public boolean isConnectedToInternet()
    {
        connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
        {
            return true;
        }
        else
        {return false;}
    }
}
