package com.fentury.testapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fentury.testapp.R;
import com.fentury.testapp.adapter.TopStoriesAdapter;
import com.fentury.testapp.api.StoriesApi;
import com.fentury.testapp.model.Model;
import com.fentury.testapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;
    private TopStoriesAdapter topStoriesAdapter;
    private List<Integer> topStories = new ArrayList<>();
    private Retrofit retrofit;
    private StoriesApi request;
    private List<Model> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initRetrofit();
        loadJSON();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(StoriesApi.class);
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topStoriesAdapter = new TopStoriesAdapter(models, this);
        recyclerView.setAdapter(topStoriesAdapter);
    }

    private void loadJSON() {
        Call<List<Integer>> call = request.getTopStories();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                topStories = response.body();
                for (Integer id : topStories) {
                    request.getTopStore(id).enqueue(new Callback<Model>() {
                        @Override
                        public void onResponse(Call<Model> call, Response<Model> response) {
                            models.add(response.body());
                            topStoriesAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Model> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
