package com.example.a_terzjan.product_display;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactFragment extends Fragment {


    private ExpandableListView elvMain;
    private TextView tv;
    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    Map<String, String> m;

    // названия компаний (групп)
    String[] contactInfo;
    String[] contactName;



    private NewsFragment.OnFragmentInteractionListener mListener;

    public ContactFragment() {
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

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        elvMain = (ExpandableListView) view.findViewById(R.id.elv);

//        countriesList = (ListView) view.findViewById(R.id.lv);
//
//        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
//                android.R.layout.simple_list_item_multiple_choice, countries);
//        // устанавливаем для списка адаптер
//        countriesList.setAdapter(adapter);


        contactName = getResources().getStringArray(R.array.contactName);
        contactInfo = getResources().getStringArray(R.array.contactInfo);

        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();


        for (String group : contactName) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);
        }

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{"phoneName"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[]{android.R.id.text1};

        for (String phone : contactInfo) {
            //для добавление в каждую группу по 1 элементы массива
            childDataItem = new ArrayList<Map<String, String>>();
            //-----------------------------------------------------
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);

            childData.add(childDataItem);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapterELV = new SimpleExpandableListAdapter(
                getActivity(),
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain.setAdapter(adapterELV);



        return view;
}


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        contactName = getResources().getStringArray(R.array.contactName);
//        contactInfo = getResources().getStringArray(R.array.contactInfo);
//
//        // создаем коллекцию для коллекций элементов
//        childData = new ArrayList<ArrayList<Map<String, String>>>();
//
//        // создаем коллекцию элементов для первой группы
//        childDataItem = new ArrayList<Map<String, String>>();
//        // заполняем коллекцию групп из массива с названиями групп
//        groupData = new ArrayList<Map<String, String>>();
//
//
//        for (String group : contactName) {
//            // заполняем список атрибутов для каждой группы
//            m = new HashMap<String, String>();
//            m.put("groupName", group); // имя компании
//            groupData.add(m);
//        }
//
//        // список атрибутов элементов для чтения
//        String childFrom[] = new String[]{"phoneName"};
//        // список ID view-элементов, в которые будет помещены атрибуты элементов
//        int childTo[] = new int[]{android.R.id.text1};
//
//        for (String phone : contactInfo) {
//            //для добавление в каждую группу по 1 элементы массива
//            childDataItem = new ArrayList<Map<String, String>>();
//            //-----------------------------------------------------
//            m = new HashMap<String, String>();
//            m.put("phoneName", phone);
//            childDataItem.add(m);
//
//            childData.add(childDataItem);
//        }
//
//        // список атрибутов групп для чтения
//        String groupFrom[] = new String[] {"groupName"};
//        // список ID view-элементов, в которые будет помещены атрибуты групп
//        int groupTo[] = new int[] {android.R.id.text1};
//
//        SimpleExpandableListAdapter adapterELV = new SimpleExpandableListAdapter(
//                getContext(),
//                groupData,
//                android.R.layout.simple_expandable_list_item_1,
//                groupFrom,
//                groupTo,
//                childData,
//                android.R.layout.simple_list_item_1,
//                childFrom,
//                childTo);
//
//        elvMain.setAdapter(adapterELV);
//
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


}




