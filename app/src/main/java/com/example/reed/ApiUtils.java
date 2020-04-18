package com.example.reed;

import android.net.Uri;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ApiUtils {
    private ApiUtils() {
    }

    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";
    public static final String QUERY_PARAMETER_KEY = "q";
    public static final String KEY = "Key";
    public static final String API_KEY = "AIzaSyAe7_rrlR5h_F6HBcJsgwPeTGDSNMiN_VE";


    public static URL buildUrl(String Title) {
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY, Title)
                .appendQueryParameter(KEY, API_KEY)
                .build();
        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

   /* public static URL  buildURL(String parameter, String sortType){
        URL  url = null;
        StringBuilder ab  = new StringBuilder();
        if(!parameter.isEmpty()) {
            switch (parameter) {
                case "author":
                        ab.append(AUTHOR+parameter);
                        break;
                case "publisher":
                    ab.append(PUBLISHER+parameter);
                    break;
                case "title":
                    ab.append(TITLE+ parameter);
                    break;
                case "isbn":
                    ab.append(ISBN +parameter);
                    break;
                case "category":
                    ab.append(CATEGORY+ parameter);
                    break;
            }
           String Query =  ab.toString();
            Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAMETER_KEY, Query)
                    .appendQueryParameter(KEY, API_KEY)
                    .build();
            try{
                url = new URL(url.toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return  url;
        }
        else{
            return  null;
        }
    }
*/
    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            MainActivity.badNetwork();

        } finally {
            connection.disconnect();
            Log.d("Error", "This list finish Here");
        }
        Log.d("Error", "We got Here too");
        return null;
    }

    public static ArrayList<Book> getBooksFromJson(String Json) {
        final String ID = "id";
        final String IMAGELINKS ="imageLinks";
        final String TITLE = "title";
        final String SUBTITLE = "subtitle";
        final String AUTHORS = "authors";
        final String PUBLISHERS = "publisher";
        final String PUBLISHEDDATE = "publishedDate";
        final String CATEGORY = "categories";
        final String VOLUMEINFO = "volumeInfo";
        final String ITEMS = "items";
        final String THUMBNAIL = "thumbnail";
        final String DESCRIPTION = "description";
        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject jsonBooks = new JSONObject(Json);
            JSONArray arrayBooks = jsonBooks.getJSONArray(ITEMS);
            int numberOfBooks = arrayBooks.length();
            for (int i = 0; i < numberOfBooks; i++) {
                JSONObject bookJson = arrayBooks.getJSONObject(i);
                JSONObject volumeInfoJson =
                        bookJson.getJSONObject(VOLUMEINFO);

                int authorNum = volumeInfoJson.isNull(AUTHORS)? 0 : volumeInfoJson.getJSONArray(AUTHORS).length();
                String[] authors = new String[authorNum];
                for(int j = 0; j < authorNum; j++ ){
                    authors[j] = volumeInfoJson.getJSONArray(AUTHORS).get(j).toString();
                }
                int categoriesNum = volumeInfoJson.isNull(CATEGORY)? 0 : volumeInfoJson.getJSONArray(CATEGORY).length();
                String[] categories = new String[categoriesNum];
                for(int k = 0; k < categoriesNum; k++){
                    categories[k] = volumeInfoJson.getJSONArray(CATEGORY).get(k).toString();
                }
                JSONObject imageJson = volumeInfoJson.getJSONObject(IMAGELINKS);


                Book book = new Book(
                        bookJson.getString(ID),
                        volumeInfoJson.getString(TITLE),
                        (volumeInfoJson.isNull(SUBTITLE)?" ": volumeInfoJson.getString(SUBTITLE)),
                        volumeInfoJson.isNull(PUBLISHERS)?"": volumeInfoJson.getString(PUBLISHERS),
                         authors,
                         volumeInfoJson.isNull(PUBLISHEDDATE)? "":volumeInfoJson.getString(PUBLISHEDDATE),
                        categories, volumeInfoJson.isNull(DESCRIPTION)? "":volumeInfoJson.getString(DESCRIPTION),
                        imageJson.getString(THUMBNAIL)
                );
                books.add(book);
            }


        } catch (Exception e) {
            Log.d("Network", "Error getting Connection");
        }
        return books;
    }

}
