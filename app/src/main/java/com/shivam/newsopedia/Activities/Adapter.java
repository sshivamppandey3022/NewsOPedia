package com.shivam.newsopedia.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shivam.newsopedia.Models.Articles;
import com.shivam.newsopedia.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context ctx;
    List<Articles> articles;

    public Adapter(Context ctx, List<Articles> articles) {
        this.ctx = ctx;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.items,parent,false);
        return new Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Articles art = articles.get(position);
        holder.tvTitle.setText(art.getTitle());
        holder.tvSource.setText(art.getSource().getName());
        holder.tvDate.setText(dateTime(art.getPublishedAt()));

        String imageUrl = art.getUrlToImage();
        Picasso.with(ctx).load(imageUrl).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,DetailedNews.class);
                intent.putExtra("title",art.getTitle());
                intent.putExtra("source",art.getSource().getName());
                intent.putExtra("time",dateTime(art.getPublishedAt()));
                intent.putExtra("desc",art.getDescription());
                intent.putExtra("imageUrl",art.getUrlToImage());
                intent.putExtra("url",art.getUrl());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvDate;
        ImageView image;
        CardView cardView;
        ProgressBar loader;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            cardView = itemView.findViewById(R.id.cardView);
            image = itemView.findViewById(R.id.image);



        }
    }
    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        return time;

    }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }
}
