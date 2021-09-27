package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText edit_text;
    private TextView text_preview;
    private ConstraintLayout myScreen;
    private String PREFNAME = "myPrefFile1";
    private String chosenColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText)findViewById(R.id.edit_text);
        button = (Button)findViewById(R.id.exit_button);
        text_preview = (TextView)findViewById(R.id.text_preview);
        myScreen = (ConstraintLayout)findViewById(R.id.myScreen);



        // Exit button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // observe text changes made to editText box
        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // Set background color
            @Override
            public void afterTextChanged(Editable editable) {
                chosenColor = editable.toString().toLowerCase(Locale.US);
                text_preview.setText(chosenColor);
                if(chosenColor.length() > 0){
                    setBackgroundColor(chosenColor,myScreen);
                }

            }
        });


        Toast.makeText(this,"onCreate", Toast.LENGTH_SHORT).show();


    }



    @Override
    protected void onStart() {

        super.onStart();
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        super.onPause();
        saveData(chosenColor);
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {

        super.onResume();
        restoreSavedData();
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {

        super.onStop();
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    // Set background color
    private void setBackgroundColor(String chosenColor, ConstraintLayout myScreen){
        if(chosenColor.contains("vy")) myScreen.setBackgroundColor(0xff120729);
        if(chosenColor.contains("quan")) myScreen.setBackgroundColor(0xff120629);
        if(chosenColor.contains("nguyen")) myScreen.setBackgroundColor(0xff120605);
        if(chosenColor.contains("phuoc")) myScreen.setBackgroundColor(0xff120624);
        if(chosenColor.contains("vuong")) myScreen.setBackgroundColor(0xff120728);

    }

    // Save temporary data in memory
    private void saveData(String chosenColor){
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor myPrefEditor  = myPrefContainer.edit();
        String key = "chosenBackgroundColor", value = text_preview.getText().toString();
        myPrefEditor.putString(key,value);
        myPrefEditor.commit();

    }

    // Restored saved data on resume
    private void restoreSavedData(){
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String key = "chosenBackgroundColor";
        String defaultValue = "white";
        if((myPrefContainer != null) && myPrefContainer.contains(key)){
            String color = myPrefContainer.getString(key,defaultValue);
            edit_text.setText(color);
            setBackgroundColor(color,myScreen);
        }
    }
}

