package demo.com.br.plot_filmguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import demo.com.br.plot_filmguide.MDBapi.ApiKey;
import demo.com.br.plot_filmguide.MDBapi.MDBapiService;
import demo.com.br.plot_filmguide.helpers.MovieDeserializer;
import demo.com.br.plot_filmguide.models.Movie;
import demo.com.br.plot_filmguide.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnPopularFilms;
    Button btnTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopularFilms = findViewById(R.id.btnPopularFilms);
        btnTopRated = findViewById(R.id.btnTopRated);

        btnPopularFilms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNewActivity(popularFilms.class);
            }
        });

        btnTopRated.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openNewActivity(activity_top_rated.class);
            }
        });
    }

    /**
     * Open an activity based on class
     * @param c Class
     */
    public void openNewActivity(Class c) {
        Intent intentNewActivity = new Intent(this, c);
        startActivity(intentNewActivity);
    }

}
