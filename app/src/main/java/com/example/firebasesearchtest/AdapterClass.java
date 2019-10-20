package com.example.firebasesearchtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<Users> list;
    Context context;

    public AdapterClass(ArrayList<Users> list){
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView name,status;
        ImageView imageView;
        public MyViewHolder(View itemVew){
            super(itemVew);
            mView = itemVew;
            name = mView.findViewById(R.id.tvName);
            status = mView.findViewById(R.id.tvStatus);
            imageView = mView.findViewById(R.id.ivUsers);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.status.setText(list.get(position).getStatus());
        String url;
        url = list.get(position).getImage();
        Glide.with(holder.imageView).load(url).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"one ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





}
