package com.example.reed;

import android.content.Context;

import androidx.lifecycle.LiveData;

public class BookRepositories {
    private BookDao mBookDao;
    private LiveData<Book> mAllBooks;

    BookRepositories(Context application){
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        mBookDao = db.bookDao();
        mAllBooks =  mBookDao.getAllBooks();
    }

    LiveData<Book> getTotalBooks(){
        return mAllBooks;
    }

    void insert(Book... book){
        mBookDao.insertAllBook(book);
    }
}
