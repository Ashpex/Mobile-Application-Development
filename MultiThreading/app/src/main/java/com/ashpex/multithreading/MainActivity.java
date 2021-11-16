package com.ashpex.multithreading;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private ProgressBar progressBar;
    private Button button;
    private Long ref;
    private Double ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = Long.parseLong(editText.getText().toString());

                button.setEnabled(false);
                MyAsyncTask my = new MyAsyncTask();
                my.execute();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        Integer count;
        @Override
        protected void onPreExecute() {
            textView.setText("");
            progressBar.setMax(100);
            progressBar.setProgress(0);
            super.onPreExecute();
            button.setEnabled(false);
            count = 0;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Void... voids) {
            for(long i = 1;i<=ref;i++) {
                Integer token = Math.toIntExact((i * 100) / ref);
                Log.d("alo", String.valueOf(token));
                publishProgress(token);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            button.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            progressBar.setProgress(values[0]);
            textView.setText(values[0] + "%");
        }
    }
}