package com.ashpex.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Customadapter extends BaseAdapter {
    Context context;
    ArrayList<Person> personArrayList;

    public Customadapter(Context context, ArrayList<Person> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
    }

    @Override
    public int getCount() {
        return personArrayList.size();
    }


    @Override
    public Object getItem(int i) {
        return personArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.person_list_view,null);

        TextView textPerson=(TextView) view.findViewById(R.id.textviewPerson);
        TextView textPhone=(TextView) view.findViewById(R.id.textviewPhone);
        ImageView imgAvatar=(ImageView) view.findViewById(R.id.imageviewAvatar);

        Person person = personArrayList.get(i);

        textPerson.setText(person.Name);
        textPhone.setText(person.Phonenumber);
        imgAvatar.setImageResource(person.Image);

        return view;
    }
}
