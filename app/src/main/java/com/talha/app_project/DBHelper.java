package com.talha.app_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "app_project.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "email TEXT NOT NULL UNIQUE, " +
                        "password TEXT NOT NULL" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE messages (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "group_name TEXT NOT NULL, " +
                        "sender_email TEXT NOT NULL, " +
                        "message TEXT NOT NULL, " +
                        "created_at INTEGER NOT NULL" +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // Check if email already exists
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Insert new user
    public boolean insertUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password); // (For semester project OK, but not secure in real apps)

        long result = db.insert("users", null, values);
        return result != -1;
    }

    // Validate login
    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT 1 FROM users WHERE email = ? AND password = ?",
                new String[]{email, password}
        );
        boolean ok = cursor.moveToFirst();
        cursor.close();
        return ok;
    }

    // Get user name by email
    public String getNameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM users WHERE email = ?", new String[]{email});
        String name = null;
        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }

    // Update user profile (name + email) using oldEmail
    public boolean updateUserProfile(String oldEmail, String newName, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("email", newEmail);

        int rows = db.update("users", values, "email = ?", new String[]{oldEmail});
        return rows > 0;
    }

    public boolean insertMessage(String groupName, String senderEmail, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("group_name", groupName);
        values.put("sender_email", senderEmail);
        values.put("message", message);
        values.put("created_at", System.currentTimeMillis());
        return db.insert("messages", null, values) != -1;
    }

    public Cursor getMessages(String groupName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT sender_email, message, created_at FROM messages WHERE group_name = ? ORDER BY id ASC",
                new String[]{groupName}
        );
    }



}
