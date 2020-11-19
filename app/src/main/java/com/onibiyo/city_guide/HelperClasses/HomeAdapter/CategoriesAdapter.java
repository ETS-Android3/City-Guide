package com.onibiyo.city_guide.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onibiyo.city_guide.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AllCategoriesHolder> {

    ArrayList<CategoriesHelperClass> allCategoriesLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> allCategoriesLocations) {
        this.allCategoriesLocations = allCategoriesLocations;
    }

    @NonNull
    @Override
    public AllCategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_card_design, parent, false);
        return new AllCategoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesHolder holder, int position) {
        CategoriesHelperClass helperClass = allCategoriesLocations.get(position);

        holder.image.setImageResource(helperClass.getImage());
        holder.title.setText(helperClass.getTitle());
        holder.relativeLayout.setBackground(helperClass.getGradient());
    }

    @Override
    public int getItemCount() {
        return allCategoriesLocations.size();
    }

    public static class AllCategoriesHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ImageView image;
        TextView title;

        public AllCategoriesHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.background_gradient);
            image = itemView.findViewById(R.id.categories_image);
            title = itemView.findViewById(R.id.categories_title);
        }
    }
}
