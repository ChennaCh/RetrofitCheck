package retrofit.chenna.com.retrofitcheck.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.chenna.com.retrofitcheck.Adapter.ActorsAdapter;
import retrofit.chenna.com.retrofitcheck.R;
import retrofit.chenna.com.retrofitcheck.model.ActorList;
import retrofit.chenna.com.retrofitcheck.model.Actors;
import retrofit.chenna.com.retrofitcheck.newtork.RetrofitClient;
import retrofit.chenna.com.retrofitcheck.newtork.APIServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private ActorsAdapter adapter;
    private RecyclerView recyclerView;
    APIServices service;
    Toolbar post_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post_toolbar = (Toolbar)findViewById(R.id.post_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);

        post_toolbar = (Toolbar) findViewById(R.id.post_toolbar);
        setSupportActionBar(post_toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*Create handle for the RetrofitInstance interface*/
        service = RetrofitClient.getClient().create(APIServices.class);

        Call<ActorList> call = service.getActorData(150);
        Log.wtf(TAG, call.request() + "");
        call.enqueue(new Callback<ActorList>() {
            @Override
            public void onResponse(Call<ActorList> call, Response<ActorList> response) {

                generateActorData(response.body().getActors());
            }

            @Override
            public void onFailure(Call<ActorList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void generateActorData(ArrayList<Actors> actors) {

        adapter = new ActorsAdapter(MainActivity.this,actors);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
//        adapter.notifyDataStateChanged();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    // Recycle view scrolling up...
                    Toast.makeText(getApplicationContext(), "Reached the top the view", Toast.LENGTH_LONG).show();


                } else if (dy > 0) {
                    // Recycle view scrolling down...
                    if(recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) == false){
                        Toast.makeText(getApplicationContext(), "Reached the end of recycler view", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
