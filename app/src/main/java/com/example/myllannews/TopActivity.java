package com.example.myllannews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myllannews.adapter.ArticleAdapter;
import com.example.myllannews.model.Article;
import com.example.myllannews.model.ResponseModel;
import com.example.myllannews.service.TopApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopActivity extends AppCompatActivity {
    private static final String TAG = TopActivity.class.getSimpleName();
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    private final static String API_KEY = "f736b599b57f46f0a6668a1347a9d111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectAndGetApiData();
    }
    public void connectAndGetApiData(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        TopApiService topApiService = retrofit.create(TopApiService.class);
        Call<ResponseModel> call = topApiService.getTopHeadlines("us",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                List<Article> articles = response.body().getArticles();
                recyclerView.setAdapter(new ArticleAdapter(articles,R.layout.top_list_item, getApplicationContext()));
                Log.d(TAG, "Number of articles:" + articles.size());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TopActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
