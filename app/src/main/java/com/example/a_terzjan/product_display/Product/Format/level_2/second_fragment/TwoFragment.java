package com.example.a_terzjan.product_display.Product.Format.level_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a_terzjan.product_display.Main.JSONParser;
import com.example.a_terzjan.product_display.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by a_terzjan on 01.03.2018.
 */

public class TwoFragment extends android.support.v4.app.Fragment {

    //--------------------------------------------------------------------



    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    private ArrayList<Details> productsList;


    private int clickPosition;

    // url to get all products list
    private static String url_all_products = "http://s29833l2.beget.tech/json/ph/get_all_products.php";

    // JSON Node names
    private static final String TAG_SUCCESS     = "success";
    private static final String TAG_PRODUCTS    = "parse";
    private static final String TAG_DETAILS     = "details";
    private static final String TAG_IMAGE       = "image";

    // products JSONArray
    JSONArray products = null;


    private RecyclerView recyclerView;
    private DetailsAdapter mAdapter;
    //---------------------------------------------------------------------

    public TwoFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.specifications_fragment, container, false);
        // Inflate the layout for this fragment


        //получаем данные о нажатой позиции в предыдущем активити
        Intent intent = getActivity().getIntent();
        clickPosition = Integer.parseInt(intent.getStringExtra("position"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDetails);


        // task start
       // new LoadDetails().execute();



        //_________________________________________________________________________________________

        return view;
    }



    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadDetails extends AsyncTask<Integer, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        /**
         * getting All products from url
         * */
        protected Void doInBackground(Integer... intergers) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());



            try {
                // Checking for SUCCESS TAG

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // получаем массив из таблицы продуктов
                    products = json.getJSONArray(TAG_PRODUCTS);

                    JSONObject c = products.getJSONObject(clickPosition);

                    Details lp = new Details(c.getString(TAG_DETAILS),c.getString(TAG_IMAGE));

                    // adding  to ArrayList
                    productsList.add(lp);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(Void avoid) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */

                    mAdapter = new DetailsAdapter( getActivity(), productsList);
                    recyclerView.setLayoutManager(new LinearLayoutManager( getActivity()));
                    // recyclerView.addItemDecoration(new Activity_Format.GridSpacingItemDecoration(4, dpToPx(10), true));
                    // recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setNestedScrollingEnabled(false);

                }
            });

        }

    }



}



























