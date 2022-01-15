package com.example.movies.Activities;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.Model.Movies;
import com.example.movies.R;
import com.example.movies.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie_Detail extends AppCompatActivity {

    private Movies movies;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieYear;
   // private TextView director;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;

    private RequestQueue queue;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail);

        queue=Volley.newRequestQueue(getApplicationContext());

        movies = (Movies) getIntent().getSerializableExtra("movie");
        movieId = movies.getImdbID();
        Log.d("IMDB------uhd",movieId);
        setUpUI();
        getMoviesDetails(movieId);


    }

    private void getMoviesDetails(String id) {

//        String mainURL=Constants.uRLLLL_LEFT+id+Constants.uRLLLL_RIGHT;
//        Log.d("************",mainURL);
        //id=movieId;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                Constants.uRLLLL_LEFT+id+Constants.uRLLLL_RIGHT,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    if (response.has("Ratings"))
                    {
                        JSONArray raating=response.getJSONArray("Ratings");
                        String source=null;
                        String value=null;
                        if (raating.length()>0)
                        {
                            JSONObject mrating=raating.getJSONObject(raating.length()-1);
                            source=mrating.getString("Source");
                            value=mrating.getString("Value");
                            rating.setText(source+" : "+value);
                        }
                        else{
                            rating.setText("N/A");
                        }
                                movieTitle.setText("Title"+response.getString("Title"));
                                movieYear.setText("Year"+response.getString("Year"));
                                //director.setText("Director"+response.getString("Director"));
                                writers.setText("Writer"+response.getString("Writer"));
                                category.setText("Genre"+response.getString("Genre"));
                                actors.setText("Actors"+response.getString("Actors"));
                                plot.setText("Plot"+response.getString("Plot"));
                                boxOffice.setText("Box Office Collection"+response.getString("BoxOffice"));                                runtime.setText(response.getString("Runtime"));
                                Log.d("move",response.getString("Title"));
                                Picasso.with(getApplicationContext())
                                .load(response.getString("Poster"))
                                .into(movieImage);
                    }


                }catch (JSONException e){
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:",error.getMessage());


            }
        });
        queue.add(jsonObjectRequest);
    }

    private void setUpUI() {

        movieTitle = (TextView) findViewById(R.id.name_of_detail);
        movieImage = (ImageView) findViewById(R.id.image_of_detail);
        movieYear = (TextView) findViewById(R.id.releaase_of_movie_DETAIL);
       // director = (TextView) findViewById(R.id.);
        category = (TextView) findViewById(R.id.category_of_movie_DETAIL);
        rating = (TextView) findViewById(R.id.Rating_of_movie_DETAIL);
        writers = (TextView) findViewById(R.id.writersDet);
        plot = (TextView) findViewById(R.id.plotDet);
        boxOffice = (TextView) findViewById(R.id.boxOfficeDet);
        runtime = (TextView) findViewById(R.id.Runtime_of_movie_DETAIL);
        actors = (TextView) findViewById(R.id.actorsDet);
    }
}
