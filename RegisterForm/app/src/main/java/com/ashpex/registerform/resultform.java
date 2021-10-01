package com.ashpex.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultform extends AppCompatActivity {

    private Intent calendar;
    private Bundle myBundle;
    private TextView txtUsername;
    private TextView txtPassword;
    private TextView txtBirthdate;
    private TextView txtGender;
    private TextView txtHobbies;
    private Button btnExit;
    private String username;
    private String password;
    private String birthday;
    private String gender;
    private String hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultform);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtBirthdate = findViewById(R.id.txtBirthdate);
        txtGender = findViewById(R.id.txtGender);
        txtHobbies = findViewById(R.id.txtHobbies);
        btnExit = findViewById(R.id.btnExit);

        handleActivity();
        handleEvent();

    }

    private void handleActivity() {
        calendar = getIntent();
        myBundle = calendar.getExtras();
        username = "Username: " + myBundle.getString("Username");
        password = "Password: " + myBundle.getString("Password");
        birthday = "Birthdate: " + myBundle.getString("Date");
        gender = "Gender: " + myBundle.getString("Gender");
        hobbies = "Hobbies: " + myBundle.getString("Hobbies");

        txtUsername.setText(username);
        txtPassword.setText(password);
        txtBirthdate.setText(birthday);
        txtGender.setText(gender);
        txtHobbies.setText(hobbies);

    }

    private void handleEvent() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.putExtras(myBundle);
                setResult(Activity.RESULT_OK,calendar);
                finish();
            }
        });
    }
}