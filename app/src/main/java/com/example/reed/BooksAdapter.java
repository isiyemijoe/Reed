package com.example.reed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.myViewHolder>{

    ArrayList<Book> mData = new ArrayList<>();
    Context mContext;


    public BooksAdapter(ArrayList<Book> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BooksAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.myViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mData.get(position).thumbnail)
                .centerCrop()
                .placeholder(R.drawable.coming)
                .into(holder.thumbnail);
        holder.title_view.setText(mData.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         ImageView thumbnail;
         TextView title_view;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title_view = itemView.findViewById(R.id.title_tv);
            itemView . setOnClickListener(this) ;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book selectedBook = mData.get(position);
            Intent intent = new Intent(itemView.getContext() , BookDetails.class);
        intent.putExtra("Book", selectedBook);
        intent.putExtra("imageUrl", selectedBook.thumbnail);
            itemView.getContext().startActivity(intent);
        }
    }

}
