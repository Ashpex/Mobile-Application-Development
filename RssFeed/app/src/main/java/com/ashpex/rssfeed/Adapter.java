package com.ashpex.rssfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Newspaper> list;
    private Context context;


    public Adapter(ArrayList<Newspaper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(ArrayList<Newspaper> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Newspaper news = list.get(position);
        if(news == null){
            return;
        }
        holder.imgNews.setImageResource(news.getResourceImage());
        holder.txtName.setText(news.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", news.getName());
                context.startActivity(intent);
            }
        });
        holder.imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", news.getName());
                context.startActivity(intent);
            }
        });
        //holder.button.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Button button;
        public ImageView imgNews;
        public TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.image);
            txtName = itemView.findViewById(R.id.name);
        }
    }
}
