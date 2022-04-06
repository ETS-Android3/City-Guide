package com.onibiyo.city_guide.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onibiyo.city_guide.R;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_class);

        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.age_picker);

    }

    public void call3rdSignupScreen(View view) {
        if (!validateGender() | !validateAge()) {
            return;
        }

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String _date = day+"-"+month+"-"+year;

        //Get intents
        String _fullName = getIntent().getStringExtra("fullname");
        String _userName = getIntent().getStringExtra("username");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");

        Intent intent = new Intent(getApplicationContext(), SignUp3rdClass.class);
        intent.putExtra("fullname", _fullName);
        intent.putExtra("username", _userName);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);
        intent.putExtra("gender", _gender);
        intent.putExtra("date", _date);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair(backBtn, "transition_arrow_back_btn");
        pairs[1] = new Pair(next, "transition_next_btn");
        pairs[2] = new Pair(login, "transition_login_btn");
        pairs[3] = new Pair(titleText, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 12) {
            Toast.makeText(this, "You are not eligible to use", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
}