package com.example.a_terzjan.product_display;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    List<Phone> phones = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new StoreFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new NewsFragment();
                    loadFragment(fragment);
                  //  mTextMessage.setText("Product");
                    return true;
                case R.id.navigation_notifications:

          //          mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        setInitialData();
        // создаем адаптер
//        DataAdapter adapter = new DataAdapter(this, phones);
//        recyclerView.setAdapter(adapter);
        loadFragment(new StoreFragment());

    }

    private void setInitialData(){
        phones.add(new Phone ("Huawei P10", "Huawei", R.mipmap.ic_launcher));
        phones.add(new Phone ("Elite z3", "HP", R.mipmap.ic_launcher));
        phones.add(new Phone ("Galaxy S8", "Samsung", R.mipmap.ic_launcher));
        phones.add(new Phone ("LG G 5", "LG", R.mipmap.ic_launcher));
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
