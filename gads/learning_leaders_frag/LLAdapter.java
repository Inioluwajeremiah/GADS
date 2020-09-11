package com.example.gads.learning_leaders_frag;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gads.R;

import java.util.Collections;
import java.util.List;

public class LLAdapter extends RecyclerView.Adapter<LLAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<LLData> llDataList;

    // create constructor to innitilize context and data sent from MainActivity
    public LLAdapter(Context context, List<LLData> data){
        this.context=context;
        this.llDataList = data;

    }

    // Inflate the layout when viewholder created
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.from(parent.getContext()).inflate(R.layout.learners_cardview, parent, false);
        return new ViewHolder(view);
    }

    // Bind data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list

        LLData current=llDataList.get(position);
        holder.textLLname.setText(current.getName());
        holder.textLLcountry.setText(current.getCountry());
        holder.textLLhour.setText(current.getHour());

        // load image into imageview using glide
        Glide.with(context).load(current.getImg_url()).into(holder.ll_image);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return llDataList.size();
    }


   public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textLLname, textLLcountry, textLLhour, text_ll_from;
        ImageView ll_image;
        // create constructor to get widget reference
        public ViewHolder(View itemView) {
            super(itemView);
            textLLname= (TextView) itemView.findViewById(R.id.ll_name);
            ll_image= (ImageView) itemView.findViewById(R.id.ll_img);
            textLLcountry = (TextView) itemView.findViewById(R.id.ll_country);
            textLLhour = (TextView) itemView.findViewById(R.id.ll_hrs);
            text_ll_from = itemView.findViewById(R.id.ll_from);
        }

    }

}
