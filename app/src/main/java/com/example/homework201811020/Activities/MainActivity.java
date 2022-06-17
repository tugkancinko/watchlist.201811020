package com.example.homework201811020.Activities;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.homework201811020.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {

    private EditText movie_input_edit_text;
    private Button show_saved_data_button;
    private Button show_data_button;

private String movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        movieName = " ";
        if (bundle != null)
        {
            movieName = bundle.getString("movie_name");
        }

    getMovieListFromNetwork();





        movie_input_edit_text = findViewById(R.id.movie_input_edit_text);
        show_saved_data_button = findViewById(R.id.show_saved_data_button);
        show_data_button = findViewById(R.id.show_data_button);

        show_saved_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateDownloadedMovieActivity();
            }
        });




        show_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMovieNameToLocalDataSource(movie_input_edit_text.getText().toString());
                navigateMovieDetailsActivity();
            }
        });
         movieName = getMovieNameFromLocalDataSource();

        if(movieName.length()>0)
        {
            navigateMovieDetailsActivity();
        }


    }

    private void getMovieListFromNetwork()
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/imdb/imdbSearchByName?query=inception")
                .method("GET", body)
                .addHeader("authorization", "apikey 6pnwckd7mypLSmmDRuAw0V:3CrWpQ0Zwui5ZQlSCXDSYy")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG,"onFailure :");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful())
                {
                    final String responseBody = response.body().string();
                }
            }
        });
    }


    private void navigateDownloadedMovieActivity()
{
    Intent DownloadedMovieIntent = new Intent(MainActivity.this,DownloadedMoviesActivity.class);
    startActivity(DownloadedMovieIntent);
}

private void saveMovieNameToLocalDataSource(String movieName)
{


    this.movieName=movieName;

    String CONST_DATA = "MOVIE_NAME";
    SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(CONST_DATA,String.valueOf(movieName));
    editor.apply();

}

private String getMovieNameFromLocalDataSource()
{
    String result;
    String CONST_DATA = "MOVIE_NAME";
    SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
    result = preferences.getString(CONST_DATA, "");


    return result;
}


private void navigateMovieDetailsActivity()
{
    Intent WatchListIntent = new Intent(MainActivity.this,WatchListActivity.class);
    WatchListIntent.putExtra("movie_name",movieName);
    startActivity(WatchListIntent);
}



}