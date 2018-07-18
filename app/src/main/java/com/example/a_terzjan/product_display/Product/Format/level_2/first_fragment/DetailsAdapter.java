package com.example.a_terzjan.product_display.Product.Format.level_2;

/**
 * Created by a_terzjan on 21.02.2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a_terzjan.product_display.R;

import java.util.ArrayList;
import java.util.List;


class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    private Context context;



    private List<Details> productsList;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView name;
        public ImageView image;

     //   public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);

            image = (ImageView) view.findViewById(R.id.image_detail);

            //   thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }

    }



    public DetailsAdapter(Context context, ArrayList<Details> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @Override
    public DetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_details, parent, false);

        return new DetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailsAdapter.MyViewHolder holder, final int position) {
        final Details movie = productsList.get(position);
        ///  holder.image.setText( movie.getImage());
        holder.name.setText( movie.getDetails());


        Glide.with(context)
                .load(movie.getImage())
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


}


















