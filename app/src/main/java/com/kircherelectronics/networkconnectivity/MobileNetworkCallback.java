package com.kircherelectronics.networkconnectivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by KircherEngineerH on 6/10/2016.
 */
public class MobileNetworkCallback extends ConnectivityManager.NetworkCallback
{
    private final static String tag = MobileNetworkCallback.class.getSimpleName();

    public void onAvailable(Network network)
    {
        Log.d(tag, "Network ID: " + network.toString());

        try
        {
            URL url = new URL("http://www.google.com/");

            URLConnection connection = network.openConnection(url);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                Log.d(tag, inputLine);
            in.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
