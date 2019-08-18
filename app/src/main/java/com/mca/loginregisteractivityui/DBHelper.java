package com.mca.loginregisteractivityui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String KEY_ID = "eid";
    public static final String USER_NAME = "username";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";


   //------------------------DB table Details
    private static final String DATABASE_NAME = "TestDB";
    private static final String TABLE_NAME = "sampleTable";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    String[] COL={KEY_ID,USER_NAME,NAME,PASSWORD,PHONE,EMAIL};


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(eid integer primary key,username text,name text,password text, phone text,email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDetails(String username,String name,String password ,String phone,String email) {  // register new user in database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("username", username);
        contentValues1.put("name", name);
        contentValues1.put("password", password);
        contentValues1.put("phone", phone);
        contentValues1.put("email", email);
            db.insert(TABLE_NAME, null, contentValues1);
        db.close();
        return true;
    }
    public Cursor login(String username,String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

            cursor =db.query(TABLE_NAME,COL,"username=?",new String[]{username},null,null,null);
        return cursor;
    }
    public Boolean resetpassword(String username,String password,String email) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("password", password);
// check account details valid or not
        cursor =db.query(TABLE_NAME,COL,"username=?",new String[]{username},null,null,null);

        if(cursor.getCount()==0)
            return false;
        else
            db.update(TABLE_NAME,contentValues1,"username = ?",new String[] {username});   //update password

        return true;
    }

    public void closeDB() {

        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null && db.isOpen())
            db.close();
        SQLiteDatabase db1 = this.getWritableDatabase();

        if (db1 != null && db1.isOpen())
            db1.close();



    }

}
