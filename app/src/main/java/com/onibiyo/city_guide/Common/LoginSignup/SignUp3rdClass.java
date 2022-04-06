package com.onibiyo.city_guide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.onibiyo.city_guide.R;

public class SignUp3rdClass extends AppCompatActivity {

    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_class);

        phoneNumber = findViewById(R.id.signup_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);
    }

    public void callVerifyOtpScreen(View view) {

        if (!validatePhoneNumber()) {
            return;
        }

        //Get intents
        String _fullname = getIntent().getStringExtra("fullname");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String testPhone = "+2348012346789";

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;

        //pass intents
        Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
        intent.putExtra("fullname", _fullname);
        intent.putExtra("username", _username);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);


        //transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.signup_last_next_btn), "transition_next_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, options.toBundle());
    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            phoneNumber.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkSpaces)) {
            phoneNumber.setError("No white spaces are allowed");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}