package com.example.reed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class BookDetails extends AppCompatActivity {
ImageView view;
TextView title;
TextView subTitle;
TextView category;
TextView price;
TextView publishedDate;
TextView details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Book book = getIntent().getParcelableExtra("Book");
        initialize();
        populateViews(book);
    }

    public void initialize(){
        view = findViewById(R.id.imageView);
        title = findViewById(R.id.book_tv_title);
        subTitle = findViewById(R.id.book_tv_subtitle);
        category = findViewById(R.id.book_tv_Category);
        publishedDate = findViewById(R.id.publishedDate);
        details = findViewById(R.id.details_dv);
    }
    public void populateViews(Book book){
        Glide.with(this).load(book.thumbnail).centerCrop().into(view);
        title.setText(book.title);
        subTitle.setText(book.subTitle);
        category.setText(book.category);
        publishedDate.setText(book.publishedDate);
        details.setText(book.description);

    }


}
