package com.example.reed;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static androidx.room.RoomDatabase.*;

@Database(entities = {Book.class}, version = 1,exportSchema = false)
public abstract class BookRoomDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    private static volatile BookRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD  = 4;
     static final ExecutorService databaseWriter =
             Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class,"book_database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallBack = new Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            LiveData<Book> cookingBooks;
            databaseWriter.execute(() ->{

            });

        }
        };
    };
