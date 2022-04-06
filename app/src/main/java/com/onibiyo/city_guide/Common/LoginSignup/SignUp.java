package com.onibiyo.city_guide.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.onibiyo.city_guide.R;

public class SignUp extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText;

    TextInputLayout fullName, userName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);

        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);

        fullName = findViewById(R.id.signup_fullname);
        userName = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()) {
            return;
        }

        String _fullname = fullName.getEditText().getText().toString().trim();
        String _username = userName.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        intent.putExtra("fullname", _fullname);
        intent.putExtra("username", _username);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair(backBtn, "transition_arrow_back_btn");
        pairs[1] = new Pair(next, "transition_next_btn");
        pairs[2] = new Pair(login, "transition_login_btn");
        pairs[3] = new Pair(titleText, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String val = userName.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            userName.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            userName.setError("Username too large");
            return false;
        } else if (!val.matches(checkSpaces)) {
            userName.setError("No white spaces are allowed");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
//                "(?=.*[0-9])" +         // at least 1 digit
//                "(?=.*[a-z])" +         // at least 1 lower case letter
//                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      // any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=.\\S+$)" +          //no white space
                ".{4,}" +               //at least 4 chars
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


}