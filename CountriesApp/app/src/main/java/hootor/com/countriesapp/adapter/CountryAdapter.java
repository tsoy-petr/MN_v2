package hootor.com.countriesapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hootor.com.countriesapp.R;
import hootor.com.countriesapp.model.Result;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CounrtyViewHolder> {

    private ArrayList<Result> countryList;

    public CountryAdapter(ArrayList<Result> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CounrtyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.row_country,viewGroup, false);

        return new CounrtyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounrtyViewHolder counrtyViewHolder, int i) {

        Result result = countryList.get(i);

        counrtyViewHolder.countryNameTextView.setText(result.getName());

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    class CounrtyViewHolder extends RecyclerView.ViewHolder {

        TextView countryNameTextView;

        public CounrtyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameTextView = itemView.findViewById(R.id.tv_country_name);
        }
    }
}
