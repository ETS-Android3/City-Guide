package com.onibiyo.city_guide.User;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.onibiyo.city_guide.Common.LoginSignup.RetailerStartUpScreen;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.onibiyo.city_guide.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.onibiyo.city_guide.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final float END_SCALE = 0.7f;
    RecyclerView featuredRecycler, mostViewRecycler, categoriesRecycler;
    FeaturedAdapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        navigationDrawer();

        featuredRecycler();
        mostViewRecycler();
        categoriesRecycler();
    }

    private void navigationDrawer() {
        //
        mNavigationView.bringToFront();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                else
                    mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

//        mDrawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimaryDark));

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the view, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;

            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                break;
        }
        return true;
    }

    public void callRetailerScreens(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }

    private void categoriesRecycler() {

        // All Gradients
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7addcf, 0xff7addcf});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe4});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd7cf9f, 0xffd7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd8d7f5, 0xffd8d7f5});

        ArrayList<CategoriesHelperClass> categoriesLocations = new ArrayList<>();
        categoriesLocations.add(new CategoriesHelperClass(gradient1, R.drawable.school_image, "Education"));
        categoriesLocations.add(new CategoriesHelperClass(gradient2, R.drawable.hospital_image, "Hospital"));
        categoriesLocations.add(new CategoriesHelperClass(gradient3, R.drawable.restaurant_image, "Restaurant"));
        categoriesLocations.add(new CategoriesHelperClass(gradient4, R.drawable.shopping_image, "Shopping"));
        categoriesLocations.add(new CategoriesHelperClass(gradient1, R.drawable.transport_image, "Transport"));

        categoriesRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoriesRecycler.setLayoutManager(manager);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesLocations);
        categoriesRecycler.setAdapter(categoriesAdapter);
    }

    private void mostViewRecycler() {
        mostViewRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mostViewRecycler.setLayoutManager(manager);

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mac_donald, "McDonald's"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.city_2, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.city_1, "JJ"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mac_donald, "Walmart"));

        MostViewedAdapter mostViewedAdapter = new MostViewedAdapter(mostViewedLocations);
        mostViewRecycler.setAdapter(mostViewedAdapter);
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        featuredRecycler.setLayoutManager(layoutManager);

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.mac_donald, "McDonald's", "jhasfjsfjsakjgfkqhv fqkwjhfrkhyrhvcwhfrvqr cqfriiwufhjjfjsf"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_1, "Eden robe", "jhasfjsfjsakjgfkqhv fqkwjhfrkhyrhvcwhfrvqr cqfriiwufhjjfjsf"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_2, "Sky Walker's", "jhasfjsfjsakjgfkqhv fqkwjhfrkhyrhvcwhfrvqr cqfriiwufhjjfjsf"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }
}