package com.example.nikhilg.mom_pin;

import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "Mom.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    private SQLiteDatabase sqLiteDatabase;

    static final String DATABASE_CREATE = "create table "+"MOM_PIN"+
            "( " +"ID integer primary key autoincrement,"+ "PIN text) ";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String pin,String con_pin)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("PIN", pin);
        db.insert("MOM_PIN", null, newValues);
    }
    public String getData(String mpin)
    {
        Cursor cursor=db.query("MOM_PIN",null," PIN=?",new String[]{mpin},null,null,null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PIN"));
        cursor.close();
        return password;
    }
}