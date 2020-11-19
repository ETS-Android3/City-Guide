package com.onibiyo.city_guide.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onibiyo.city_guide.HelperClasses.SliderAdapter;
import com.onibiyo.city_guide.R;
import com.onibiyo.city_guide.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    ViewPager mViewPager;
    LinearLayout dotsLayout;
    TextView[] dots;
    Button getStarted;
    Animation anim;
    int currentPos;

    SliderAdapter mSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        mViewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStarted = findViewById(R.id.get_started_btn);

        mSliderAdapter = new SliderAdapter(this);
        mViewPager.setAdapter(mSliderAdapter);

        addDots(0);
        mViewPager.addOnPageChangeListener(mChangeListener);
    }

    public void skip(View view) {
        startActivity(new Intent(this, UserDashboard.class));
        finish();
    }

    public void next(View view) {
        mViewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener mChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            switch (position) {
                case 0: case 1: case 2:
                    getStarted.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    anim = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                    getStarted.setAnimation(anim);
                    getStarted.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}