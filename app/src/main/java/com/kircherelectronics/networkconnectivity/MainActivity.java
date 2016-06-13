package com.kircherelectronics.networkconnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    private final static String tag = MainActivity.class.getSimpleName();

    private final static int PERMISSION_WRITE_SETTINGS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMobileNetworkConnection();
        initWifiNetworkConnection();
    }

    private void initMobileNetworkConnection()
    {
        Log.d(tag, "Init Mobile Network Connection");

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);

        MobileNetworkCallback networkCallback = new MobileNetworkCallback();

        NetworkRequest networkRequest = builder.build();
        connectivityManager.requestNetwork(networkRequest, networkCallback);
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    private void initWifiNetworkConnection()
    {
        Log.d(tag, "Init Wifi Network Connection");

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);

        WifiNetworkCallback networkCallback = new WifiNetworkCallback();

        NetworkRequest networkRequest = builder.build();
        connectivityManager.requestNetwork(networkRequest, networkCallback);
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }
}
