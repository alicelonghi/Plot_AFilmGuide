package demo.com.br.plot_filmguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import demo.com.br.plot_filmguide.models.Result;

public class Activity_movie_detail extends AppCompatActivity {

    TextView TextView_title;
    TextView textView_description;
    TextView textView_rating;
    TextView textView_popularity;
    Button buttonSimilares;
    ImageView imageView_poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intentMovieDetail = getIntent();
        Bundle dadosItemClick = intentMovieDetail.getExtras();

        buttonSimilares = findViewById(R.id.buttonSimilares);
        imageView_poster = findViewById(R.id.imageView_poster);
        Picasso.get()
                .load(getIntent().getExtras().getString("Poster"))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView_poster);

        if(getIntent()!= null) {
            String title = getIntent().getExtras().getString("Title");
            String description = getIntent().getExtras().getString("Description");
            String rating = getIntent().getExtras().getString("Rating");
            String popularity = getIntent().getExtras().getString("Popularity");

            //Double rating = intentMovieDetail.getDoubleExtra("Rating", 0.00);
            TextView_title = findViewById(R.id.TextView_title);
            textView_description = findViewById(R.id.textView_description);
            textView_popularity = findViewById(R.id.textView_popularity);
            TextView_title.setText(title);
            textView_rating = findViewById(R.id.textView_rating);
            textView_rating.setText(rating);
            // String rating_String = Double.toString(rating);
            //textView_rating.setText(rating_String);
            textView_description.setText(description);
            textView_popularity.setText(popularity);
            //Toast.makeText(Activity_movie_detail.this, "Movie ID: " + movie_id  , Toast.LENGTH_LONG).show();
        }

        buttonSimilares.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String movie_id = getIntent().getExtras().getString("ID");
                Intent activitySimilares = new Intent(getApplicationContext(), Similares.class);
                //Toast.makeText(Activity_movie_detail.this, "Movie ID: " + movie_id, Toast.LENGTH_LONG).show();
                activitySimilares.putExtra("ID", Integer.parseInt(movie_id));
                activitySimilares.putExtra("Title", getIntent().getExtras().getString("Title"));

                startActivity(activitySimilares);

            }
        });

    }
}
