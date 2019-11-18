package demo.com.br.plot_filmguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import demo.com.br.plot_filmguide.MDBapi.ApiKey;
import demo.com.br.plot_filmguide.MDBapi.MDBapiService;
import demo.com.br.plot_filmguide.adapters.MovieAdapter;
import demo.com.br.plot_filmguide.helpers.MovieDeserializer;
import demo.com.br.plot_filmguide.models.Movie;
import demo.com.br.plot_filmguide.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class popularFilms extends AppCompatActivity {

    private static final String TAG = "MOVIE";
    private Retrofit retrofit;

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter adapter;
    private ArrayList<Result> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_films);

        init();

        Gson g = new GsonBuilder().registerTypeAdapter (Movie.class, new MovieDeserializer()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiKey.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        getData();

        adapter.setOnItemClickListener(new MovieAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Intent intentItemClick = new Intent(getApplicationContext(), Activity_movie_detail.class);
                Bundle dadosItemClick = new Bundle();
                Result movie = movies.get(position);
                //toast(movie.getOverview());
                intentItemClick.putExtra("Title", movie.getTitle());
                intentItemClick.putExtra("Poster", movie.getURLPoster());
                intentItemClick.putExtra("Description", movie.getOverview());
                String voteAverage = Double.toString(movie.getVoteAverage());
                String idMovie = Integer.toString(movie.getId());
                String popularity = Double.toString(movie.getPopularity());
                intentItemClick.putExtra("Rating", voteAverage);
                intentItemClick.putExtra("Popularity", popularity);
                intentItemClick.putExtra("ID", idMovie);
                //intentItemClick.putExtras(dadosItemClick);

                startActivity(intentItemClick);

            }

            @Override
            public void onItemLongClick(int position, View v) {
                toast("clicou e segurou!");
            }
        });

    }

    public void init() {
        recyclerViewMovies = findViewById(R.id.recycler_view_movies);
        progressBar = findViewById(R.id.activity_popular_films_progress);

        progressBar.setVisibility(View.INVISIBLE);

        movies = new ArrayList<>();
        adapter = new MovieAdapter(popularFilms.this, movies);
        recyclerViewMovies.setAdapter(adapter);

        recyclerViewMovies.setHasFixedSize(true);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getData() {

        progressBar.setVisibility(View.VISIBLE);
        MDBapiService MDBapiService = retrofit.create(MDBapiService.class);
        Call<Movie> movieCall = MDBapiService.getMovieData(ApiKey.API_KEY);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()) {
                    List<Result> moviesAuxiliar = response.body().getResults();

//                    Log.d("ma teste", "ma teste:"+response);
//                    Log.d("ma teste", "ma teste:"+response.body());


                    for (int i = 0; i < moviesAuxiliar.size(); i++) {
                        movies.add(moviesAuxiliar.get(i));
                    }

//                    Log.d("ma teste", "ma teste:"+movies.toString());

                    adapter.setMovies(movies);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }


}
