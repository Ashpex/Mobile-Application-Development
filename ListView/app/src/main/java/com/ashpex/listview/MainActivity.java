package com.ashpex.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    com.ashpex.listview.Customadapter customadapter;
    ArrayList<Person> personArrayList;
    int currentId=0;
    TextView personChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creater();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personChoose.setText("You choose: " + personArrayList.get(i).Name);
            }
        });
    }



    public void creater(){
        listView=(ListView) findViewById(R.id.listview);
        personChoose = (TextView) findViewById(R.id.personChoose);
        personArrayList=new ArrayList<Person>();
        customadapter = new com.ashpex.listview.Customadapter(MainActivity.this,personArrayList);
        listView.setAdapter(customadapter);

        personArrayList.add(new Person(++currentId,"Bùi Ngọc Thảo Vy","0922334455",R.drawable.avatar02));
        personArrayList.add(new Person(++currentId,"Lê Hồng Quân","0933668899",R.drawable.avatar01));
        personArrayList.add(new Person(++currentId,"Huỳnh Ngọc Nguyên","0923456789",R.drawable.avatar03));
        personArrayList.add(new Person(++currentId,"Nguyễn Trọng Phước","098989873",R.drawable.avatar04));
        personArrayList.add(new Person(++currentId,"Trương Quốc Vương","0987654323",R.drawable.avatar05));


    }
}