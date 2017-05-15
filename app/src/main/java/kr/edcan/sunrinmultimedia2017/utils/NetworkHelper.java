package kr.edcan.sunrinmultimedia2017.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.NetworkInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public class NetworkHelper {
    private final static String url = "http://13.124.125.184/";
    private final static int port = 3000;

    private static Retrofit retrofit;

    public static APIRequest getNetworkInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url + ":" + port)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIRequest.class);
    }

    public static boolean returnNetworkState(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
