package com.example.a_terzjan.product_display;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


public class StoreFragment extends Fragment {

    private static final String URL = "https://api.androidhive.info/json/movies_2017.json";

    List<News> newsList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private NewsAdapter adapter;

    public StoreFragment() {
        // Required empty public constructor
    }


    public static StoreFragment newInstance() {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        //--- для отображения recycler во фрагменте добавляется эта строка
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //-----------------------------------------------------------------

        //setInitialNews();
        adapter = new NewsAdapter(getActivity(), newsList);

        recyclerView.setAdapter(adapter);

        fetchStoreItems();

        return view;
    }


//    private void setInitialNews(){
//        news.add(new Phone ("Iphone", "Huawei", R.mipmap.ic_launcher));
//        news.add(new Phone ("Iphone", "HP", R.mipmap.ic_launcher));
//        news.add(new Phone ("Iphone", "Samsung", R.mipmap.ic_launcher));
//        news.add(new Phone ("Iphone", "LG", R.mipmap.ic_launcher));
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void fetchStoreItems() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<News> items = new Gson().fromJson(response.toString(), new TypeToken<List<News>>() {
                        }.getType());

                        newsList.clear();
                        newsList.addAll(items);

                        // refreshing recycler view
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleyHardware.getInstance().addToRequestQueue(request);
    }


}




