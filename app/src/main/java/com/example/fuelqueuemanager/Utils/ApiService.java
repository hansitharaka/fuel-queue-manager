package com.example.fuelqueuemanager.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiService {
    private static ApiService mService;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private ApiService(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ApiService getInstance(Context context) {

        // If instance is null, initialize new instance
        if (mService == null) {
            mService = new ApiService(context);
        }

        // Return MySingleton new Instance
        return mService;
    }

    public RequestQueue getRequestQueue() {

        // If RequestQueue is null, initialize new RequestQueue
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        // Return RequestQueue
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        // Add the specified request to the request queue
        getRequestQueue().add(req);
    }


}
