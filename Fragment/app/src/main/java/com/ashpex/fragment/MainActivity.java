package com.ashpex.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentListStudent.ISendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment1, new FragmentListStudent());
        fragmentTransaction.add(R.id.fragment2, new FragmentDetailStudent());
        fragmentTransaction.commit();
    }

    @Override
    public void sendDataStudent(Student student) {
        FragmentDetailStudent fragmentDetailStudent = (FragmentDetailStudent) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        fragmentDetailStudent.receivedDataFromFragment(student);
    }
}