package demo.com.br.plot_filmguide.MDBapi;

import java.util.List;

import demo.com.br.plot_filmguide.models.Movie;
import demo.com.br.plot_filmguide.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MDBapiService {

    @GET("movie/popular")
    Call<Movie> getMovieData(@Query("api_key") String API_KEY);

    @GET("movie/top_rated")
    Call<Movie> getTopRated(@Query("api_key") String API_KEY);

    @GET("movie/{movie_id}/similar")
    Call<Movie> getSimilars(
            @Path("movie_id") String movie_id,
            @Query("api_key") String API_KEY);
}

