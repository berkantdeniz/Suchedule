package com.example.peran.suchedulestep3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "Courses.db";
    public static final String TABLE_NAME = "course_table";
    public static final String TABLE_NAME1 = "taken_table";
    public static final String TABLE_NAME2 = "calender_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DAY";
    public static final String COL_4 = "STARTT";
    public static final String COL_5 = "ENDT";

    public static final String QUERY = "select course_table.NAME, course_table.STARTT, course_table.ENDT from course_table, calender_table where course_table.STARTT != calender_table.STARTT";


    //Const
    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );
        db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);


    }



    //for insterting
    public boolean insertData(String name, String Start, String End){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_4,Start);
        contentValues.put(COL_5,End);
        //db.insert(TABLE_NAME,null,contentValues); // if fails returns -1

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertDataCalender(String name, String Start, String End){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_4,Start);
        contentValues.put(COL_5,End);
        //db.insert(TABLE_NAME,null,contentValues); // if fails returns -1

        long result = db.insert(TABLE_NAME2,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertDataTaken(String name, String Start, String End){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_4,Start);
        contentValues.put(COL_5,End);
        //db.insert(TABLE_NAME,null,contentValues); // if fails returns -1

        long result = db.insert(TABLE_NAME1,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllCourses(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public Cursor getTaken(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from taken_table",null);
        return res;

    }

    public Cursor getCalender(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from calender_table",null);
        return res;

    }

    public Integer deleteCourse(String name)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[] {name});

    }

    public Integer deleteAddedCourse(String name)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "NAME = ?", new String[] {name});

    }

    public Cursor getRec()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(QUERY,null);
        return res;

    }
/*
    public void dropTables()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table course_table");
        db.execSQL("drop table taken_table");
        db.execSQL("drop table calender_table");


    }

    public void createTables()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );
        db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, STARTT TEXT,ENDT  TEXT)" );



    }*/
}

