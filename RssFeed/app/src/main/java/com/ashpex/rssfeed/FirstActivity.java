package com.ashpex.rssfeed;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstActivity extends Activity {
    TextView txtMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        txtMain = findViewById(R.id.txtMain);
        txtMain.setText("NEWS App");
        RecyclerView ryc = findViewById(R.id.ryc);
        ArrayList<Newspaper> list = new ArrayList<>();
        list.add(new Newspaper(R.drawable.ic_thanhnien,"Thanh niên"));
        list.add(new Newspaper(R.drawable.ic_tuoitre,"Tuổi trẻ"));
        list.add(new Newspaper(R.drawable.vietnamnet,"VietnamNet"));
        list.add(new Newspaper(R.drawable.vnexpress,"VNExpress"));
        Adapter adapter = new Adapter(list, this);

        ryc.setAdapter(adapter);
        ryc.setLayoutManager(new GridLayoutManager(this, 2));

    }
}