package com.example.myweather;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        new myAsyncTask().execute();
    }
    public String fetchDataFromUrl (String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }

    public  class myAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try
            {
                data = fetchDataFromUrl("http://api.openweathermap.org/data/2.5/forecast/daily?APPID=c13159d2d9b7d01343afbc8acde7572b&q=Dhaka&mode=json&units=metric&cnt=16&fbclid=IwAR2DhQpm2tf8WJo_6EvdPq-3HDzoD8EfK_S2ZM7TvmtqTv55vuXw9VAkkbw");

                System.out.println("test"+data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            MyContacts myContacts = new Gson().fromJson(data,MyContacts.class);
            recyclerView.setAdapter(new MyAdapter(MainActivity.this,myContacts.getContacts()));
        }
    }
}
