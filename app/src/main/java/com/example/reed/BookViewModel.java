package com.example.reed;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel  extends AndroidViewModel {

    private BookRepositories bookRepositories;
    private LiveData<Book> mAllBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepositories = new BookRepositories(application);
        mAllBooks = bookRepositories.getTotalBooks();
    }

    LiveData<Book> getAllBooks(){
        return mAllBooks;
    }

    public void insert(Book...book){
        bookRepositories.insert();
    }


}
