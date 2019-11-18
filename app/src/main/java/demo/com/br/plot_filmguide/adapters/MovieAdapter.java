package demo.com.br.plot_filmguide.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import demo.com.br.plot_filmguide.R;
import demo.com.br.plot_filmguide.models.Result;

public class MovieAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Result> movies;
    private static ClickListener clickListener;


    public MovieAdapter(Context context, ArrayList<Result> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.linha_filme, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder hold = (ViewHolder) holder;
        Result movie = movies.get(position);

        String voteAverage = Double.toString(movie.getVoteAverage());

        hold.textViewOriginalTitle.setText(movie.getOriginalTitle());
        hold.textViewAverage.setText(voteAverage);

        Picasso.get()
                .load(movie.getURLPoster())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(hold.imgView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final ImageView imgView;
        private final TextView textViewOriginalTitle;
        private final TextView textViewAverage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            imgView = itemView.findViewById(R.id.imgView);
            textViewOriginalTitle = itemView.findViewById(R.id.textview_original_title);
            textViewAverage = itemView.findViewById(R.id.TextView_average);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return true;
        }
    }

    public void setMovies(ArrayList<Result> movies) {
        this.movies = movies;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MovieAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}