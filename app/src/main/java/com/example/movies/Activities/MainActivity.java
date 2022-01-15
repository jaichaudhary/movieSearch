package com.example.movies.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.Data.Movie_recycler_view_ADAPTER;
import com.example.movies.Model.Movies;
import com.example.movies.R;
import com.example.movies.Util.Constants;
import com.example.movies.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Movie_recycler_view_ADAPTER movie_recycler_view_adapter;
    private List<Movies> moviesList;
    private RequestQueue queue;
    private AlertDialog.Builder dialog_Builder;
    private AlertDialog dialog;
    private EditText editText_search;
    private Button button_for_search;

    //private final String movieURL="http://www.omdbapi.com/?s=Batman&page=tt3896198&apikey=56cc1abd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=getLayoutInflater().inflate(R.layout.search,null);

                editText_search=view1.findViewById(R.id.edit_TEXT_ID);
                button_for_search=view1.findViewById(R.id.Search_button_ID);

                dialog_Builder=new AlertDialog.Builder(MainActivity.this);
                dialog_Builder.setView(view1);
                dialog=dialog_Builder.create();
                dialog.show();


                button_for_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Prefs prefs=new Prefs(MainActivity.this);
                        if (!editText_search.getText().toString().isEmpty())
                        {
                            String searched_ITEM=editText_search.getText().toString();
                            prefs.setSearch(searched_ITEM);
                            moviesList.clear();
                            getMoviesList(searched_ITEM);
                            movie_recycler_view_adapter.notifyDataSetChanged();

                        }
                        dialog.dismiss();
                    }
                });



//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerVIEW_ID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesList=new ArrayList<>();
        Prefs prefs=new Prefs(MainActivity.this);
        String search=prefs.getSearch();
        moviesList=getMoviesList(search);

        movie_recycler_view_adapter = new Movie_recycler_view_ADAPTER(this, moviesList );
        recyclerView.setAdapter(movie_recycler_view_adapter);
        movie_recycler_view_adapter.notifyDataSetChanged();


    }


    public List<Movies> getMoviesList(String searchITEM)
    {
        moviesList.clear();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                Constants.uRL_LEFT + searchITEM + Constants.uRL_RIGHT, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray movie_ARRAY = response.getJSONArray("Search");
                            //JSONArray movie_ARRAY = response.getJSONArray("employees");
                            for (int i=0;i<=movie_ARRAY.length();i++)
                            {
                                JSONObject movieOBJECT=movie_ARRAY.getJSONObject(i);
                                Movies movies=new Movies();
                                movies.setTitle("Name: "+ movieOBJECT.getString("Title"));
                                movies.setYear("year: "+ movieOBJECT.getString("Year"));
                                movies.setMovie_TYPE("Type:" + movieOBJECT.getString("Type"));
                                movies.setPoster(movieOBJECT.getString("Poster"));
                                movies.setImdbID(movieOBJECT.getString("imdbID"));

                                Log.d("MOVIEEE",movies.getTitle().toString()+ "  "+movie_ARRAY.length());
                                //movieRecyclerViewAdapter.notifyDataSetChanged();
                                moviesList.add(movies);
                            }

                        }catch (JSONException e){
                            movie_recycler_view_adapter.notifyDataSetChanged();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MOVIEEE",error.getMessage().toString());
            }
        });
        queue.add(jsonObjectRequest);
        return moviesList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
