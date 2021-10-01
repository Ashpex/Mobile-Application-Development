package com.ashpex.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class registerform extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;
    private EditText editRetype;
    private EditText editBirthday;
    private RadioGroup radGender;
    private Button btnCalendar;
    private Button btnReset;
    private Button btnSignup;
    private CheckBox checkTennis;
    private CheckBox checkFutbal;
    private CheckBox checkOthers;
    private String gender;
    int day;
    int month;
    int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editRetype = (EditText) findViewById(R.id.editRetype);
        editBirthday = (EditText) findViewById(R.id.editBirthday);
        radGender = (RadioGroup) findViewById(R.id.radGender);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        checkTennis = (CheckBox) findViewById(R.id.checkTennis);
        checkFutbal = (CheckBox) findViewById(R.id.checkFutbal);
        checkOthers = (CheckBox) findViewById(R.id.checkOthers);

        eventsHandle();

    }

    private void eventsHandle() {

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar();
            }
        });

        radGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                gender = (String) radioButton.getText();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInfo()){
                    handleActivity();
                }
            }
        });
    }

    private boolean validateInfo() {

        if (editUsername.getText().toString().equals("")){
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!editRetype.getText().toString().equals(editPassword.getText().toString())){
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return false;

        }

        if (editPassword.getText().toString().equals("")){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editBirthday.getText().toString().equals("")){
            Toast.makeText(this, "Birthday cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radGender.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showCalendar() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                editBirthday.setText(d + "/" + m + "/" + y);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Material_Dialog,
                dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void handleActivity() {
        Intent myIntent = new Intent(registerform.this, resultform.class);
        Bundle myBundle = new Bundle();

        myBundle.putString("Username", editUsername.getText().toString());
        myBundle.putString("Password", editPassword.getText().toString());
        myBundle.putString("Date", editBirthday.getText().toString());
        myBundle.putString("Gender",gender);

        String hobby = "";
        if (checkFutbal.isChecked()){
            hobby += "Futbal ";
        }
        if (checkTennis.isChecked()){
            hobby += "Tennis";
        }
        if (checkOthers.isChecked()){
            hobby += "Others...";
        }

        myBundle.putString("Hobbies", hobby);
        myIntent.putExtras(myBundle);
        startActivityForResult(myIntent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 1234) && (resultCode == Activity.RESULT_OK)){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        editUsername.setText("");
        editPassword.setText("");
        editRetype.setText("");
        editBirthday.setText("");
        radGender.clearCheck();
        checkTennis.setChecked(false);
        checkFutbal.setChecked(false);
        checkOthers.setChecked(false);
    }
}