package com.example.peran.suchedulestep3;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;




public class  MainActivity extends AppCompatActivity implements classTaken.OnFragmentInteractionListener,classRecommend.OnFragmentInteractionListener,classDelete.OnFragmentInteractionListener, ListViewDelete.deleted, mainFragment.OnFragmentInteractionListener, classInsert.OnFragmentInteractionListener, ListViewAdapter.clicked {


    databaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        myDB = new databaseHelper(this);

       myDB.insertData("CS201","m12","m14");
        myDB.insertData("CS204","m12","m14");
        myDB.insertData("CS210","m14","m16");
        myDB.insertData("CS301","t8","t10");
        myDB.insertData("CS305","t12","t14");
        myDB.insertData("CS306","t16","t18");
        myDB.insertData("CS308","w8","w10");
        myDB.insertData("CS310","w12","w14");
        myDB.insertData("CS404","w16","w18");
        myDB.insertData("CS400","r8","r10");
        myDB.insertData("CS412","r14","r16");
        myDB.insertData("MATH101","r12","r14");
        myDB.insertData("MATH102","f8","f10");
        myDB.insertData("MATH201","m10","m12");
        myDB.insertData("MATH202","m12","m14");
        myDB.insertData("MATH204","m16","m18");
        myDB.insertData("MATH306","t12","t14");
        myDB.insertData("MATH311","r10","r12");
        myDB.insertData("ME302","f10","f12");
        myDB.insertData("EE200","m16","m18");
        myDB.insertData("EE310","m12","m14");
        myDB.insertData("EE410","m16","m18");
        myDB.insertData("VA201","t12","t14");
        myDB.insertData("VA310","t10","t12");
        myDB.insertData("VA333","r12","r14");
        myDB.insertData("SPS303","r16","r18");
        myDB.insertData("MGMT203","r12","r14");
        myDB.insertData("PSY201","r8","r10");
        myDB.insertData("ENS201","w8","w10");
        myDB.insertData("ENS205","w10","w12");
        myDB.insertData("HUM201","w14","w16");
        myDB.insertData("HUM202","w16","w18");
        myDB.insertData("HUM203","r14","r16");
        myDB.insertData("HUM212","f8","f10");
        myDB.insertData("ENG101","f12","f14");
        myDB.insertData("ENG102","w10","w12");
        myDB.insertData("NS101","r10","r12");
        myDB.insertData("NS102","r12","r14");
        myDB.insertData("SPS101","w10","w12");
        myDB.insertData("SPS102","r12","r14");
        myDB.insertData("HIST191","t14","t16");
        myDB.insertData("HIST192","t8","t10");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mainFragment mainfragment = mainFragment.newInstance();

        fragmentTransaction.add(R.id.fragment_container, mainfragment, "mainFragment");
        fragmentTransaction.commit();


    }


    @Override
    public void onAdding(int x) {
        classInsert deneme = classInsert.newInstance(x);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container,deneme);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        //TODO

    }

    @Override
    public void onAdded(int count) {


        getSupportFragmentManager().popBackStackImmediate();
    }


    @Override
    public boolean click() {
        getSupportFragmentManager().popBackStackImmediate();
        return true;
    }

    @Override
    public boolean delete() {
        getSupportFragmentManager().popBackStackImmediate();
        return true;
    }


    @Override
    public void onDeleting(int count) {
        classDelete deneme = classDelete.newInstance(count);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container,deneme);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        //TODO
    }
    @Override
    public void onRec(int count)
    {

        classRecommend deneme = classRecommend.newInstance(count);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container,deneme);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    @Override
    public void onTake(int count)
    {

        classTaken deneme = classTaken.newInstance(count);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container,deneme);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStackImmediate();

    }
}
