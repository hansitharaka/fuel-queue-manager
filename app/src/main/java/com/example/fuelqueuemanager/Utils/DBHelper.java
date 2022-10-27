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

    public Boolean checkUser(String username){

        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usersNew where username = ?",new String[] {username});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkCredentials(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usersNew where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0) {

//            Cursor cursorRole = myDB.rawQuery("select role from users where username = ?", new String[]{username});
//            String userRole = String.valueOf(cursorRole);
//            return userRole;
            return true;
        }
        else {
            return false;
        }

    }

    public Cursor getRole(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursorRole = myDB.rawQuery("select role from usersNew where username = ?", new String[]{username});
        String userRole = String.valueOf(cursorRole);
        Log.i("UserRole", "getRole: "+userRole);
        return cursorRole;

    }
}
