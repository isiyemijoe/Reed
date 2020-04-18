package com.example.reed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.reed.Book;

import java.util.List;
@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
        void insertBook(Book book);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAllBook(Book...book);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT  * FROM book_table WHERE  title = :title")
     LiveData<Book> getTitleSearch(String title);

    @Query("SELECT  * FROM book_table WHERE  authors = :authors")
    LiveData<Book> getAutSearch(String authors);

    @Query("SELECT  * FROM book_table WHERE  category = :categories")
    LiveData<Book> getCatSearch(String categories);

    @Query("SELECT  * FROM book_table WHERE  publisher = :publishers")
    LiveData<Book> getPubSearch(String publishers);

    @Query("SELECT  * FROM book_table")
    LiveData<Book> getAllBooks();
}
