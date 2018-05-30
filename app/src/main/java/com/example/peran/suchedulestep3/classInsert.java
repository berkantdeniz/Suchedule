package com.example.peran.suchedulestep3;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.lang.StringBuffer;
import java.util.List;

public class classInsert extends Fragment implements SearchView.OnQueryTextListener/*, ListViewAdapter.clicked*/{




    private static final String ARG_PARAM1 = "count_param";
    private int count;
    databaseHelper db;
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<Courses> courseNameList;
    ArrayList<Courses> arraylist = new ArrayList<Courses>();
    Button add, taken;

    private OnFragmentInteractionListener mListener;

    public classInsert() {
        // Required empty public constructor
    }

    public static classInsert newInstance(int count) {
        classInsert fragment = new classInsert();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new databaseHelper(getActivity());

        View view = inflater.inflate(R.layout.classinsert_fragment, container, false);
        //View view1 = inflater.inflate(R.layout.list_view_items, container, false);
        // add =(Button) view1.findViewById(R.id.button);


        // Generate sample data
        //TODO: Database connection
       /* courseNameList = new String[]{"CS201", "CS204", "CS300",
                "CS301", "MATH101", "MATH102", "MATH201", "MATH204",
                "SPS303"};*/

        Cursor res = db.getAllCourses();
        courseNameList = new ArrayList<>();

        while (res.moveToNext())
        {
          //  try {
                courseNameList.add(new Courses(res.getInt(0), res.getString(1), res.getString(2), res.getString(3)));
         //   }catch(Exception e)
         //   {
                //Toaster("anan");
          //  }
        }

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.listview);

        for (int i = 0; i < courseNameList.size(); i++) {
            //courseNames courseNames = new courseNames(courseNameList.get(i).name);
            // Binds all strings into an array
            arraylist.add(courseNameList.get(i));
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);



        adapter.notifyDataSetChanged();




        //  updateUI();
        return view;
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

   /* @Override
    public boolean click() {
        if(mListener != null)
            mListener.onAdded(5);
    return true;}*/


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onAdded(int count);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    private void Toaster(String text)
    {
        Toast.makeText(super.getContext(), text , Toast.LENGTH_SHORT).show();
    }



}
