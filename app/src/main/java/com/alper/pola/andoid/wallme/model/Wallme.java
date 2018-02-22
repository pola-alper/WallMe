package com.alper.pola.andoid.wallme.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alper.pola.andoid.wallme.util.Constants;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shashank on 02-Aug-17.
 */

public class Wallme {
    private static final String TAG = "Webservice";
    private static RequestQueue requestQueue;
    private static Wallme INSTANCE;


    private Wallme() {
    }

    public static Wallme getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            requestQueue = Volley.newRequestQueue(context);



            INSTANCE = new Wallme();

            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public void getSearchList(Activity activity, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Constants.Editor_choice, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                                Type listType = new TypeToken<List<ImageResponse>>() {
                                }.getType();
                                ArrayList<ImageResponse> imageResponses = new Gson().fromJson(response.getString("hits"), listType);
                                callBack.onSuccess(imageResponses);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }

    public void getSearchLists(Context context, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Constants.Editor_choice, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                            Type listType = new TypeToken<List<ImageResponse>>() {
                            }.getType();
                            ArrayList<ImageResponse> imageResponses = new Gson().fromJson(response.getString("hits"), listType);
                            callBack.onSuccess(imageResponses);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );
        int socketTimeout = 90000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        getRequest.setRetryPolicy(policy);
        queue.add(getRequest);

    }
    public void getSearchLists2(Context context, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Constants.Daily_Wallpaber, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                            Type listType = new TypeToken<List<ImageResponse>>() {
                            }.getType();
                            ArrayList<ImageResponse> imageResponses = new Gson().fromJson(response.getString("hits"), listType);
                            callBack.onSuccess(imageResponses);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );
        int socketTimeout = 90000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        getRequest.setRetryPolicy(policy);
        queue.add(getRequest);

    }


    public void getSearch(Activity activity, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Constants.search, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                            Type listType = new TypeToken<List<ImageResponse>>() {
                            }.getType();
                            ArrayList<ImageResponse> imageResponses = new Gson().fromJson(response.getString("hits"), listType);
                            callBack.onSuccess(imageResponses);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }
    public interface GetSearchListCallBack{
        void onSuccess(ArrayList<ImageResponse> imageResponses);
        void onFail(String message);
    }
}
