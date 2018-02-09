package com.example.a_terzjan.product_display;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by a_terzjan on 09.02.2018.
 */

//public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
//
//    private LayoutInflater inflater;
//    private List<News> news;
//
//    NewsAdapter(Context context, List<News> news) {
//        this.news = news;
//        this.inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = inflater.inflate(R.layout.list_item_news, parent, false);
//        return new NewsAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
//        News news = news.get(position);
//        holder.imageView.setImageResource(news.getImage());
//        holder.infoView.setText(news.getInfo());
//        holder.moreView.setText(news.getMore());
//    }
//
//    @Override
//    public int getItemCount() {
//        return news.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        final ImageView imageView;
//        final TextView infoView, moreView;
//        ViewHolder(View view){
//            super(view);
//            imageView = (ImageView)view.findViewById(R.id.image);
//            infoView = (TextView) view.findViewById(R.id.info);
//            moreView = (TextView) view.findViewById(R.id.more);
//        }
//    }
//}


class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private Context context;
    private List<News> newsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView info, more;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            info = (TextView) view.findViewById(R.id.info);
            more = (TextView) view.findViewById(R.id.more);
            thumbnail = (ImageView)view.findViewById(R.id.image);

        }
    }


    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final News movie = newsList.get(position);


            holder.info.setText(movie.getInfo());
            holder.more.setText(movie.getMore());

            Glide.with(context)
                    .load(movie.getImage())
                    .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
