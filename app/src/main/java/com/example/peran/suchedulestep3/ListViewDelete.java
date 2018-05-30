package com.example.peran.suchedulestep3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewDelete extends BaseAdapter {

    databaseHelper db;
    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Courses> courseNamesList = null;
    private ArrayList<Courses> arraylist;
    private ListViewDelete.deleted a;
    Button add;
    public ListViewDelete(Context context, ArrayList<Courses> courseNamesList) {
        mContext = context;
        this.courseNamesList = courseNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Courses>();
        this.arraylist.addAll(courseNamesList);
        db= new databaseHelper(mContext);
    }

    public class ViewHolder {
        TextView name;
        TextView time;
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

        final ListViewDelete.ViewHolder holder;
        if (view == null) {
            holder = new ListViewDelete.ViewHolder();
            view = inflater.inflate(R.layout.list_view_delete, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(holder);
        } else {
            holder = (ListViewDelete.ViewHolder) view.getTag();
        }
        add= view.findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    db.insertData(courseNamesList.get(position).name,courseNamesList.get(position).start,courseNamesList.get(position).end);
                     db.deleteAddedCourse(courseNamesList.get(position).name);
                    courseNamesList.remove(position);
                    arraylist.remove(position);
                    //a = (ListViewDelete.deleted) temp;
                   // a.delete();
                notifyDataSetChanged();


            }
        });

        // Set the results into TextViews
        holder.name.setText(courseNamesList.get(position).name);
        holder.time.setText(courseNamesList.get(position).start +"-" + courseNamesList.get(position).end);
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

    public interface deleted{

        boolean delete();
    }



}
