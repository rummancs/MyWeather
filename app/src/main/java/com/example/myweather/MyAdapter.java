package com.example.myweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;

    List<Weather> weathers;

    public MyAdapter(Context c, List<Weather> weathers) {
        this.c = c;
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c)
                .inflate(R.layout.list_item, viewGroup ,false);
        return new MyHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.nameView.setText(contacts.get(i).getName());
        myHolder.descView.setText(contacts.get(i).getAddress());
        //myHolder.myImage.setImageResource(imageData[i]);

    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView descView;
        ImageView myImage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.textView);
            descView = itemView.findViewById(R.id.textView2);
            myImage = itemView.findViewById(R.id.image);
        }
    }
}
