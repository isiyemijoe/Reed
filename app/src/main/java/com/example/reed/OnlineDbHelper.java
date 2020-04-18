package com.example.reed;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

import static com.example.reed.MainActivity.badNetwork;

public class OnlineDbHelper {




    public void getAllBooks(){
        URL cookUrl = ApiUtils.buildUrl("Cooking");
        URL fantasyUrl = ApiUtils.buildUrl("Fantasy");
        URL artUrl = ApiUtils.buildUrl("Art");
        URL motivationUrl = ApiUtils.buildUrl("Motivation");
        URL wealthUrl = ApiUtils.buildUrl("Wealth");
        URL urls[] = {wealthUrl,motivationUrl,artUrl,fantasyUrl,cookUrl};

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

            if (s == null || s.isEmpty()) {
                badNetwork();

            } else {
                ArrayList<Book> books = ApiUtils.getBooksFromJson(s);

              //  BooksAdapter adapter = new BooksAdapter(books, MainActivity.this);
             /*   recyclerView.setAdapter(adapter);
                tv_error.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                error_message.setVisibility((View.GONE));*/
            }
        }

        @Override
        protected void onPreExecute() {
         /*   online();
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);*/
        }
    }

}
