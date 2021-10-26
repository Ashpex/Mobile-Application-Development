package com.ashpex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetailStudent extends Fragment {

    private View view;
    private TextView txtID;
    private TextView txtDiem;
    private TextView txtHoTen;
    private TextView txtLop;
    private static Button btnFirst;
    private static Button btnPrevious;
    private static Button btnNext;
    private static Button btnLast;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_detail_student, container, false);

        txtID = (TextView) view.findViewById(R.id.txtID);
        txtDiem = (TextView) view.findViewById(R.id.txtDiem);
        txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);
        txtLop = (TextView) view.findViewById(R.id.txtLop);

        btnFirst = (Button) view.findViewById(R.id.btnFirst);
        btnLast = (Button) view.findViewById(R.id.btnLast);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnPrevious = (Button) view.findViewById(R.id.btnPrevious);

        listView = com.ashpex.fragment.FragmentListStudent.listView;

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentListStudent.index = 0;
                FragmentListStudent.setItemClickAtIndex(FragmentListStudent.index);
                setEnabledButton(FragmentListStudent.index);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FragmentListStudent.index != listView.getCount() - 1) {
                    FragmentListStudent.index++;
                    FragmentListStudent.setItemClickAtIndex(FragmentListStudent.index);
                    setEnabledButton(FragmentListStudent.index);
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FragmentListStudent.index != 0) {
                    FragmentListStudent.index--;
                    FragmentListStudent.setItemClickAtIndex(FragmentListStudent.index);
                    setEnabledButton(FragmentListStudent.index);
                }
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentListStudent.index = listView.getCount() - 1;
                FragmentListStudent.setItemClickAtIndex(FragmentListStudent.index);
                setEnabledButton(FragmentListStudent.index);
            }
        });

        return view;
    }

    public static void setEnabledButton(int index) {
        if (index == 0) {
            btnFirst.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        } else if (index == FragmentListStudent.listView.getCount() - 1) {
            btnLast.setEnabled(false);
            btnNext.setEnabled(false);
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
        } else {
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(true);
            btnFirst.setEnabled(true);
            btnLast.setEnabled(true);
        }
    }

    private void setDataStudent(int index) {
        com.ashpex.fragment.Student student = (Student) listView.getItemAtPosition(index);
        txtID.setText(student.getID());
        txtHoTen.setText("Họ tên: " + student.getName());
        txtLop.setText("Lớp: 19/3");
        txtDiem.setText("Điểm trung bình: 10");
    }

    public void receivedDataFromFragment(Student student) {
        txtID.setText(student.getID());
        txtHoTen.setText("Họ tên: " + student.getName());
        txtLop.setText("Lớp: 19/3");
        txtDiem.setText("Điểm trung bình: 10");
    }
}
