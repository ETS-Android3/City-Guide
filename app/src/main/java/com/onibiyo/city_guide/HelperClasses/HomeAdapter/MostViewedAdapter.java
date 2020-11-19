package com.onibiyo.city_guide.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onibiyo.city_guide.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedHolder> {

    ArrayList<MostViewedHelperClass> mostViewLocations;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewLocations) {
        this.mostViewLocations = mostViewLocations;
    }

    @NonNull
    @Override
    public MostViewedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.most_viewed_card_design, parent, false);
        return new MostViewedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedHolder holder, int position) {
        MostViewedHelperClass mostViewedClass = mostViewLocations.get(position);

        holder.image.setImageResource(mostViewedClass.getImage());
        holder.title.setText(mostViewedClass.getTitle());
        holder.desc.setText(mostViewedClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostViewLocations.size();
    }

    public static class MostViewedHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;

        public MostViewedHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }
    }
}
