package com.example.reed;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    static LottieAnimationView  progressBar;
    static LottieAnimationView tv_error;
    static TextView error_message;
    String searchText = "intitle";
   static boolean isOnline;


    public static void badNetwork() {
        progressBar.setVisibility(View.INVISIBLE);
        error_message.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.VISIBLE);
        Log.d("Error", "Error Occoured Here");

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.search_bar);

        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Name, Title, Author ");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tv_error = findViewById(R.id.tv_error);
        error_message = findViewById(R.id.error_messaage);
        final int columns = getResources().getInteger(R.integer.column_number);
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        try {
            URL bookUrl = ApiUtils.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.sort:
                break;
            case R.id.author:
                searchText = "inauthor:";
                        break;
            case R.id.category:
                searchText = "subject:";
                        break;
            case R.id.name:
                searchText = "intitle:";
                break;
            case R.id.publisher:
                searchText = "inpublisher:";
                break;
            case R.id.all:
                searchText = "";
                break;
        }
        return false;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            StringBuilder bld = new StringBuilder();
            if(searchText.isEmpty()){bld.append(query);}
            else {bld.append(searchText+query);}

            URL bookUrl = ApiUtils.buildUrl(bld.toString());

            new BooksQueryTask().execute(bookUrl);
        }
        catch (Exception e){
            Log.d("MainActivity","Error building search url");
        }
return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public class BooksQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try {
                result = ApiUtils.getJson(searchUrl);
            } catch (Exception e) {
                Log.d("Error getting Json", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            progressBar.setVisibility(View.GONE);
            if (s == null || s.isEmpty()) {
                badNetwork();

            } else {
                ArrayList<Book> books = ApiUtils.getBooksFromJson(s);

                BooksAdapter adapter = new BooksAdapter(books, MainActivity.this);
                recyclerView.setAdapter(adapter);
                tv_error.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                error_message.setVisibility((View.GONE));
            }
        }

        @Override
        protected void onPreExecute() {
            online();
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    public void online(){
        tv_error.setVisibility(View.GONE);
        error_message.setVisibility(View.GONE);
    }
}