package com.example.a_terzjan.product_display.product_level.Format.level_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a_terzjan.product_display.Main.JSONParser;
import com.example.a_terzjan.product_display.R;
import com.example.a_terzjan.product_display.Main.RecyclerItemClickListener;
import com.example.a_terzjan.product_display.product_level.Format.level_2.ActivityDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by a_terzjan on 14.02.2018.
 */

public class Activity_Product_Type extends AppCompatActivity{

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    private ArrayList<LevelProduct> productsList;




    // url to get all products list
    private static String url_all_products = "http://s29833l2.beget.tech/json/ph/get_all_products.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "parse";
    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "title";
    private static final String TAG_IMAGE = "image";

    // products JSONArray
    JSONArray products = null;

    private  ListView lv ;
    private TextView tv ;
    private RecyclerView recyclerView;

    private LevelProductAdapter mAdapter;
    //private AsdAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_product_format_type);

        productsList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView123);



        new LoadAllProducts().execute();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(Activity_Product_Type.this, ActivityDetails.class);
                        intent.putExtra("position", String.valueOf(position));
                        startActivity(intent);


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<Integer, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Activity_Product_Type.this);
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

                    // получаем все продукты
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);


                        LevelProduct lp = new LevelProduct(c.getString(TAG_IMAGE), c.getString(TAG_NAME));


                        // adding  to ArrayList
                        productsList.add(lp);
                    }
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
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */


                   // tv.setText(String.valueOf( productsList));

                    mAdapter = new LevelProductAdapter(Activity_Product_Type.this, productsList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_Product_Type.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                   // recyclerView.addItemDecoration(new Activity_Format.GridSpacingItemDecoration(4, dpToPx(10), true));
                   // recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setNestedScrollingEnabled(false);


                }
            });

        }

    }
}













