package com.example.movies.Data;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.Activities.Movie_Detail;
import com.example.movies.Model.Movies;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Movie_recycler_view_ADAPTER extends RecyclerView.Adapter<Movie_recycler_view_ADAPTER.ViewHolder> {

    private Context context;
    private List<Movies> moviesList;
    public Movie_recycler_view_ADAPTER(Context context, List<Movies> movies) {
        this.context=context;
        moviesList=movies;
    }

    @NonNull

    @Override
    public Movie_recycler_view_ADAPTER.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTYPE) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_recycler_view_ADAPTER.ViewHolder viewHolder, int position) {
        Movies movies=moviesList.get(position);
        String poster_LINK=movies.getPoster();
         viewHolder.title.setText(movies.getTitle());
         viewHolder.type.setText(movies.getMovie_TYPE());
         viewHolder.year.setText(movies.getYear());
        Picasso.with(context).
                load(poster_LINK).
                placeholder(R.drawable.ic_launcher_foreground).
                into(viewHolder.poster);


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView poster;
        TextView year;
        TextView type;

        public ViewHolder(@NonNull View itemView,final Context ctx) {
            super(itemView);
            context=ctx;
            title=(TextView)itemView.findViewById(R.id.movie_name_ID);
            poster=(ImageView)itemView.findViewById(R.id.movie_image_ID);
            year=(TextView)itemView.findViewById(R.id.movie_release_ID);
            type=(TextView)itemView.findViewById(R.id.movie_CATEGORY_ID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movies movies=moviesList.get(getAdapterPosition());

                   Intent intent = new Intent(context, Movie_Detail.class);

                   intent.putExtra("movie",movies);
                   ctx.startActivity(intent);
                    Toast.makeText(context,"ROW TAPPED",Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
