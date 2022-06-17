package om.example.homework201811020.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.homework201811020.Entitiy.Result;


public class WeatherResultAdapter extends RecyclerView.Adapter<WeatherResultAdapter.ViewHolder> {
    private java.util.List<Result> MovieResultList;

    public WeatherResultAdapter(java.util.List<Result> weatherResultList) {
        this.MovieResultList = weatherResultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(RecyclerView.ViewHolder.MovieItemList, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = MovieResultList.get(position);

        holder.title_tv.setText(result.getTitle());
        holder.year_tv.setText(result.getYear());
        holder.year_value_tv.setText(result.getYearValue());


        holder.MovieItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return MovieResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final android.R.attr R = ;
        private TextView title_tv;
        private TextView year_tv;
        private TextView year_value_tv;


        public ViewHolder(View v) {
            super(v);

            title_tv = v.findViewById(R.id.title_tv);
            year_tv = v.findViewById(R.id.year_tv);
            year_value_tv = v.findViewById(R.id.year_value_tv);


        }
    }

}