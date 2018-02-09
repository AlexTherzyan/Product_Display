package com.example.a_terzjan.product_display;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {



     List<Phone> phones = new ArrayList<>();



    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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

        setInitialData();
        DataAdapter adapter = new DataAdapter(getActivity(), phones);

        recyclerView.setAdapter(adapter);


        return view;
    }



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

    private void setInitialData(){
        phones.add(new Phone ("Huawei P10", "Huawei", R.mipmap.ic_launcher));
        phones.add(new Phone ("Elite z3", "HP", R.mipmap.ic_launcher));
        phones.add(new Phone ("Galaxy S8", "Samsung", R.mipmap.ic_launcher));
        phones.add(new Phone ("LG G 5", "LG", R.mipmap.ic_launcher));
    }
}
