package com.ashpex.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Student> studentList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(ArrayList<Student> studentList, Context context, LayoutInflater layoutInflater) {
        this.studentList = studentList;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    public CustomListAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtID = (TextView) convertView.findViewById(R.id.txtID);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student student = this.studentList.get(position);
        holder.txtName.setText(student.getName());
        holder.txtID.setText(student.getID().toString());
//        holder.txtID.setText("ID: " + studentList.size());
        int imageId = this.getMipmapResIdByName(student.getAvatar());

        holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
//        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView flagView;
        TextView txtName;
        TextView txtID;
    }
}

