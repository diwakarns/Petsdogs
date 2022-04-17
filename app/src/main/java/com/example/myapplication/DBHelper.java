package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(  Context context ) {
        super(context,  "Userdata.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(societyid INTEGER Primary Key Autoincrement, name TEXT , contact TEXT, dob TEXT )");
        DB.execSQL("create Table Feeds(feedsID TEXT primary key AutoIncrement, served_by TEXT," +
                "updated_by TEXT, created_date DATETIME,update_date DATETIME)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
        DB.execSQL("drop Table if exists Feeds");
        onCreate(DB);
        DB.execSQL("ALTER TABLE UserDetails ADD COLUMN created_date DATETIME");
        Log.d("Name", "Hello ");

    }

    public Boolean insertuserdata(String name, String contact, String dob, String created_date ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("created_date", created_date);
        Log.d("Name2", "Word ");

        long result = DB.insert("UserDetails" , null, contentValues);
        if (result == -1)
        {
            return false;

        }
        else{
            return true;

        }

    }
    public Boolean updateuserdata(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});

        if(cursor.getCount()>0){
        long result = DB.update("UserDetails",  contentValues, "name=?", new String[]{name});
        if (result == -1)
        {
            return false;

        }
        else{
            return true;

        }
        }else
        {
            return false;

        }
    }
    public Boolean deleteuserdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});
        if(cursor.getCount()>0){
            long result = DB.delete("UserDetails",  "name=?", new String[]{name});
            if (result == -1)
            {
                return false;

            }
            else{
                return true;

            }
        }else
        {
            return false;

        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from UserDetails", null);
        return cursor;

    }
}
