package com.example.peran.suchedulestep3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewRec extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Courses> courseNamesList = null;
    private ArrayList<Courses> arraylist;


    public ListViewRec(Context context, List<Courses> courseNamesList) {
        mContext = context;
        this.courseNamesList = courseNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Courses>();
        this.arraylist.addAll(courseNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return courseNamesList.size();
    }

    @Override
    public Courses getItem(int position) {
        return courseNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ListViewRec.ViewHolder holder;
        if (view == null) {
            holder = new ListViewRec.ViewHolder();
            view = inflater.inflate(R.layout.list_view_reco, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ListViewRec.ViewHolder) view.getTag();
        }


        // Set the results into TextViews
        holder.name.setText(courseNamesList.get(position).name);
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        courseNamesList.clear();
        if (charText.length() == 0) {
            courseNamesList.addAll(arraylist);
        } else {
            for (Courses wp : arraylist) {
                if (wp.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    courseNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void Toaster(String text)
    {
        Toast.makeText(mContext, text , Toast.LENGTH_SHORT).show();
    }




}


