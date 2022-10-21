package com.example.mobilevarificationotp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.Name1.setText(user.getName());
        holder.tt.setText(user.getTT());
        holder.amt.setText(user.getAmount());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name1, tt, amt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name1 = itemView.findViewById(R.id.Name);
            tt = itemView.findViewById(R.id.tt);
            amt = itemView.findViewById(R.id.amt);

        }
    }

}
