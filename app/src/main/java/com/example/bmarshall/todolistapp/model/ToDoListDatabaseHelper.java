package com.example.bmarshall.todolistapp.model;

/**
 * Created by bmarshall on 2/6/17.
 */

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteException;


import java.util.Date;

/**
 * Created by bmarshall on 1/26/17.
 */

public class ToDoListDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "toDoList";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public ToDoListDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TODOITEM (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "START_DATE DATE, "
                + "DUE_DATE DATE, "
                + "IS_COMPLETE);");

        db.execSQL("CREATE TABLE REMINDER (_id INTEGER PRIMARY KEY, "
                + "REMINDER_DATE DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertToDoItem(String title, String startDate, String dueDate, boolean isComplete) {
        try {
            db = getReadableDatabase("password");
            ContentValues toDoItemValues = new ContentValues();
            toDoItemValues.put("TITLE", title);
            toDoItemValues.put("START_DATE", startDate);
            toDoItemValues.put("DUE_DATE", dueDate);
            toDoItemValues.put("IS_COMPLETE", isComplete);
            db.insert("TODOITEM", null, toDoItemValues);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
        }
    }

    public void insertReminder(int ItemId, String reminderDate) {
        try {
            db = getReadableDatabase("password");
            ContentValues reminderValues = new ContentValues();
            reminderValues.put("_id", ItemId);
            reminderValues.put("REMINDER_DATE", reminderDate);
            db.insert("REMINDER", null, reminderValues);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
        }
    }

    public Cursor getToDoItems() {
        try {
            db = getReadableDatabase("password");
            return db.query("TODOITEM", new String[]{"_id", "TITLE",},
                    null, null, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Cursor getToDoItem(int id) {
        try {
            db = getReadableDatabase("password");
            return db.query("TODOITEM", new String[]{"TITLE", "START_DATE", "DUE_DATE", "IS_COMPLETE"},
                    "_id = ?", new String[] {String.valueOf(id)}, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Cursor getReminders() {
        try {
            db = getReadableDatabase("password");
            return db.query("REMINDER", new String[]{"_id", "REMINDER_DATE"}, null, null, null, null, null);
        } catch (SQLiteException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}


