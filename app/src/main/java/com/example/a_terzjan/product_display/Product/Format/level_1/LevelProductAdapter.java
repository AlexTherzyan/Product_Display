package com.example.a_terzjan.product_display.product_level.Format.level_1;

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


class LevelProductAdapter extends RecyclerView.Adapter<LevelProductAdapter.MyViewHolder> {

    private Context context;



    private  List<LevelProduct> productsList;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView name;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.image);

         //   thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }

    }


    public LevelProductAdapter(Context context, ArrayList<LevelProduct> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @Override
    public LevelProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_format_type, parent, false);

        return new LevelProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LevelProductAdapter.MyViewHolder holder, final int position) {
        final LevelProduct movie = productsList.get(position);
      ///  holder.image.setText( movie.getImage());
        holder.name.setText( movie.getTitle());


        Glide.with(context)
                .load(movie.getImage())
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


}















