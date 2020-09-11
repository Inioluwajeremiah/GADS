package com.example.gads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class IQrecAdapter extends RecyclerView.Adapter<IQrecAdapter.ViewHolder> {

    private Context context;
    private List<IQdata> miq_data;

    public IQrecAdapter (Context context, List<IQdata> miq_data) {
        this.context = context;
        this.miq_data = miq_data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.iq_textscore.setText(miq_data.get(position).getScore());
        viewHolder.iq_textname.setText(miq_data.get(position).getName());
        viewHolder.iq_textcountry.setText(miq_data.get(position).getCountry());
        Glide.with(context).load(miq_data.get(position).getImage_link()).into(viewHolder.iq_imageView);
    }

    @Override
    public int getItemCount() {
        return miq_data.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{
        public ImageView iq_imageView;
        public TextView iq_textname, iq_textcountry, iq_textscore , iq_text_from;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iq_imageView = itemView.findViewById(R.id.iq_img);
            iq_textname = itemView.findViewById(R.id.iq_name);
            iq_textscore = itemView.findViewById(R.id.iq_score);
            iq_textcountry = itemView.findViewById(R.id.iq_country);
            iq_text_from = itemView.findViewById(R.id.iq_from);
        }
    }
}
