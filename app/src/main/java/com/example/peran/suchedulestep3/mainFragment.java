package com.example.peran.suchedulestep3;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class mainFragment extends Fragment {

    databaseHelper db;

    private OnFragmentInteractionListener  mListener;
    Button insertAdd,deletebutton,recbutton,takenbut,refresh;
  TextView temp;
    TableLayout calender;
    TableRow row;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void onDeleting(int x);
        void onAdding(int x);
        void onRec(int x);
        void onTake(int x);
    }

    public mainFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static mainFragment newInstance() {
        mainFragment fragment = new mainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new databaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.main_fragment, container, false);
       calender = (TableLayout) view.findViewById(R.id.tableLayout);
        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db.getCalender();
                while(res.moveToNext())
                {
                  //  for(int i = 0; i< 11 ; i++)
                  //  {
                        String s = res.getString(2);
                        int i = Integer.parseInt(s.substring(1,s.length()));
                        row = (TableRow) calender.getChildAt(i-7);
                       char a=s.charAt(0);
                       if(a=='m') {
                           temp = (TextView) row.getChildAt(1);
                       temp.setText(res.getString(1));
                           row = (TableRow) calender.getChildAt(i-6);
                           temp = (TextView) row.getChildAt(1);
                           temp.setText(res.getString(1));}
                    if(a=='t') {
                        temp = (TextView) row.getChildAt(2);
                        temp.setText(res.getString(1));
                        row = (TableRow) calender.getChildAt(i-6);
                        temp = (TextView) row.getChildAt(2);
                        temp.setText(res.getString(1));}
                    if(a=='w') {
                        temp = (TextView) row.getChildAt(3);
                        temp.setText(res.getString(1));
                        row = (TableRow) calender.getChildAt(i-6);
                        temp = (TextView) row.getChildAt(3);
                        temp.setText(res.getString(1));}
                    if(a=='r') {
                        temp = (TextView) row.getChildAt(4);
                        temp.setText(res.getString(1));
                        row = (TableRow) calender.getChildAt(i-6);
                        temp = (TextView) row.getChildAt(4);
                        temp.setText(res.getString(1));}
                    if(a=='f') {
                        temp = (TextView) row.getChildAt(5);
                        temp.setText(res.getString(1));
                        row = (TableRow) calender.getChildAt(i-6);
                        temp = (TextView) row.getChildAt(5);
                        temp.setText(res.getString(1));}



                   // }
                }

            }
        });

        insertAdd =(Button) view.findViewById(R.id.insertButton);
        insertAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onAdding(6);
                }
            }
        });

        deletebutton =(Button) view.findViewById(R.id.deleteButton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDeleting(6);
                }
            }
        });

        recbutton =(Button) view.findViewById(R.id.reco);
        recbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onRec(6);
                }
            }
        });

        takenbut =(Button) view.findViewById(R.id.taken);
        takenbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onTake(6);
                }
            }
        });

        return view;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        Cursor res = db.getCalender();
        while(res.moveToNext())
        {
            //  for(int i = 0; i< 11 ; i++)
            //  {
            String s = res.getString(2);
            int i = Integer.parseInt(s.substring(1,s.length()));
            row = (TableRow) calender.getChildAt(i-7);
            char a=s.charAt(0);
            if(a=='m') {
                temp = (TextView) row.getChildAt(1);
                temp.setText(res.getString(1));
                row = (TableRow) calender.getChildAt(i-6);
                temp = (TextView) row.getChildAt(1);
                temp.setText(res.getString(1));}
            if(a=='t') {
                temp = (TextView) row.getChildAt(2);
                temp.setText(res.getString(1));
                row = (TableRow) calender.getChildAt(i-6);
                temp = (TextView) row.getChildAt(2);
                temp.setText(res.getString(1));}
            if(a=='w') {
                temp = (TextView) row.getChildAt(3);
                temp.setText(res.getString(1));
                row = (TableRow) calender.getChildAt(i-6);
                temp = (TextView) row.getChildAt(3);
                temp.setText(res.getString(1));}
            if(a=='r') {
                temp = (TextView) row.getChildAt(4);
                temp.setText(res.getString(1));
                row = (TableRow) calender.getChildAt(i-6);
                temp = (TextView) row.getChildAt(4);
                temp.setText(res.getString(1));}
            if(a=='f') {
                temp = (TextView) row.getChildAt(5);
                temp.setText(res.getString(1));
                row = (TableRow) calender.getChildAt(i-6);
                temp = (TextView) row.getChildAt(5);
                temp.setText(res.getString(1));}



            // }
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

