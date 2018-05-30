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

public class classRecommend extends Fragment implements SearchView.OnQueryTextListener/*, ListViewRec.clicked*/{




    private static final String ARG_PARAM2 = "count_param2";
    private int count;

    ListView list;
    ListViewRec adapter;
    SearchView editsearch;
    ArrayList<Courses>courseNameList;
    ArrayList<Courses> arraylist = new ArrayList<Courses>();
    ArrayList<Courses> calenderList = new ArrayList<>();
    Button back;
    databaseHelper db;
    private OnFragmentInteractionListener mListener;

    public classRecommend() {
        // Required empty public constructor
    }

    public static classRecommend newInstance(int count) {
        classRecommend fragment = new classRecommend();
        Bundle args = new Bundle();args.putInt(ARG_PARAM2, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt(ARG_PARAM2);
        }
        db = new databaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.classrecommend_fragment, container, false);
        //View view1 = inflater.inflate(R.layout.list_view_items, container, false);
        // add =(Button) view1.findViewById(R.id.button);


        // Generate sample data
        //TODO: Database Connection
        Cursor res = db.getAllCourses();
        Cursor res1 =db.getCalender();
        courseNameList = new ArrayList<>();
        arraylist = new ArrayList<>();
        while (res.moveToNext())
        {
            courseNameList.add(new Courses(res.getInt(0),res.getString(1), res.getString(2), res.getString(3)));
        }
        while (res1.moveToNext())
        {
            calenderList.add(new Courses(res1.getInt(0),res1.getString(1), res1.getString(2), res1.getString(3)));
        }

        for(int x =0; x<courseNameList.size();x++)
        {
            for(int c=0; c<calenderList.size();c++)
            {
                if(courseNameList.get(x).start.equals(calenderList.get(c).start) ) {
                    courseNameList.remove(x);
                    x--;
                    break;
                }
            }

        }

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.listview);

        for (int i = 0; i < courseNameList.size(); i++) {
           // courseNames courseNames = new courseNames(courseNameList.get(i));
            // Binds all strings into an array
            arraylist.add(courseNameList.get(i));
        }

        // Pass results to ListViewRec Class
        adapter = new ListViewRec(getActivity(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        back = (Button) view.findViewById(R.id.reco);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    mListener.goBack();
                }

            }
        });


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

        void goBack();
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
