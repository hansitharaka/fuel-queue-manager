package com.example.fuelqueuemanager.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"FuelLogin.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("" +
                "CREATE TABLE UserLogin(" +
                "email TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "role TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int older, int newer) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String email, String password, String role){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentvalues = new ContentValues();
        contentvalues.put("email",email);
        contentvalues.put("password",password);
        contentvalues.put("role",role);

        long newUser = database.insert("user",null, contentvalues);
        database.close();

        return newUser != -1;
    }

    public Boolean findUser(String email){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM User where email = ?",new String[] { email } );

        return cursor.getCount() > 0;
    }

    public Boolean checkCredentials(String email, String password) {

        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery(
                "SELECT * FROM User " +
                        "WHERE email = ? " +
                        "AND password = ?", new String[]{ email, password } );

        return cursor.getCount() > 0;

    }

    public String getRole(String email){

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursorRole = database.rawQuery(
                "SELECT role FROM User " +
                        "WHERE email = ?", new String[]{ email } );

        cursorRole.moveToFirst();

        return cursorRole.getString(0);

    }
}
