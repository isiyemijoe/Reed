package com.example.reed;

import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setEnterTransition(fade);

        Book book = getIntent().getParcelableExtra("Book");
        setTitle(book.title);
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
