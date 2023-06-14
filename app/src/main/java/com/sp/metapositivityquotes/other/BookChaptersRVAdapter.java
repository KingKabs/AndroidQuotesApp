package com.sp.metapositivityquotes.other;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.sp.metapositivityquotes.R;

import java.util.ArrayList;
import java.util.Random;

public class BookChaptersRVAdapter extends RecyclerView.Adapter<BookChaptersRVAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BookChaptersDataModel> dataModelArrayList;

    public BookChaptersRVAdapter(Context ctx, ArrayList<BookChaptersDataModel> dataModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_bookchapter_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.chapterID.setText("QUOTE "+dataModelArrayList.get(position).getChapterID());
        holder.chapterTitle.setText(dataModelArrayList.get(position).getChapterTitle());
        holder.chapterDescription.setText(dataModelArrayList.get(position).getChapterDescription());
        //holder.cardView.setBackgroundColor(getRandomColor());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView chapterID, chapterTitle, chapterDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            //chapterID = (TextView) itemView.findViewById(R.id.tvChapterID);
            chapterTitle = (TextView) itemView.findViewById(R.id.tvChapterTitle);
            chapterDescription = (TextView) itemView.findViewById(R.id.tvChapterDescription);
        }

    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
