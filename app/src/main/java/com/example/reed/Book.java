package com.example.reed;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
@Entity(tableName = "book_table")
public class Book implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "subTitle")
    public String subTitle;

    @ColumnInfo(name = "publisher")
    public String publisher;

    @ColumnInfo(name = "authors")
    public String authors;

    @ColumnInfo(name = "publishedDate")
    public String publishedDate;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;

    @ColumnInfo(name = "description")
    public String description;


    public Book(String id, String title, String subTitle, String publisher, String[] authors, String publishedDate, String category[],String description, String thumbnail) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.publisher = publisher;
        this.authors = TextUtils.join(", \n  ", authors);
        this.publishedDate = publishedDate;
        this.category = TextUtils.join(", ",category );
        this.description = description;
        this.thumbnail = thumbnail;
    }
    public Book(){}


    protected Book(Parcel in) {
        id = in.readString();
        title = in.readString();
        subTitle = in.readString();
        publisher = in.readString();
        authors = in.readString();
        publishedDate = in.readString();
        category = in.readString();
        description = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(publisher);
        dest.writeString(authors);
        dest.writeString(publishedDate);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(thumbnail);
    }
}
