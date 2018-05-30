package com.example.peran.suchedulestep3;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Courses> courseNamesList = null;
    private ArrayList<Courses> arraylist;
    private clicked a;
    Button add,taken;
    databaseHelper db;
    public ListViewAdapter(Context context, ArrayList<Courses> courseNamesList) {
        mContext = context;
        this.courseNamesList = courseNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Courses>();
        this.arraylist.addAll(courseNamesList);
        db = new databaseHelper(context);
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
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        add= view.findViewById(R.id.button);
        taken = view.findViewById(R.id.taken);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context temp = mContext;
                if(temp instanceof clicked)
                {
                    boolean vedat =true;

                    //TODO: Check

                    Cursor res = db.getCalender();
                    while (res.moveToNext())
                    {
                            String temp1 = res.getString(2).toString();
                            String tmep2 =courseNamesList.get(position).start;

                        if( temp1.equals(tmep2))
                        { vedat = false;}
                    }

                    if(vedat) {
                        db.insertDataCalender(courseNamesList.get(position).name, courseNamesList.get(position).start, courseNamesList.get(position).end);
                        db.deleteCourse(courseNamesList.get(position).name);
                        courseNamesList.remove(position);
                        arraylist.remove(position);
                        a = (clicked) temp;
                        a.click();
                    }else
                        Toaster("You can not");
                }

            }
        });

        taken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context temp = mContext;
                if(temp instanceof clicked)
                {

                    db.insertDataTaken(courseNamesList.get(position).name,courseNamesList.get(position).start,courseNamesList.get(position).end);
                    db.deleteCourse(courseNamesList.get(position).name);
                    courseNamesList.remove(position);
                    arraylist.remove(position);
                    a = (clicked) temp;
                    a.click();

                }

            }
        });
        holder.name.setText(courseNamesList.get(position).name);
        holder.time.setText(courseNamesList.get(position).start +"-" + courseNamesList.get(position).end);

        // Set the results into TextViews
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

    public interface clicked{

        boolean click();
    }


}
