package com.ashpex.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class registerForm extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtRetype;
    private RadioGroup radGender;
    private CheckBox checkBox;
    private Button btnCalendar;
    private Button btnReset;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerForm);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRetype = (EditText) findViewById(R.id.txtRetype);
        radGender = (RadioGroup) findViewById(R.id.radGender);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSignup = (Button) findViewById(R.id.btnSignup);
    }
}