package com.example.bmarshall.todolistapp.controller;

import android.app.Application;

import com.example.bmarshall.todolistapp.model.ToDoListDatabaseHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by bmarshall on 2/6/17.
 */

public class Controller extends Application {

    private ToDoListDatabaseHelper toDoListDatabaseHelper;

    @Override
    public void onCreate() {
        SQLiteDatabase.loadLibs(this);
        toDoListDatabaseHelper = new ToDoListDatabaseHelper(this);
    }

    public Cursor getToDoItems(){
            return toDoListDatabaseHelper.getToDoItems();
    }

    public Cursor getReminders(){
        return toDoListDatabaseHelper.getReminders();
    }
}
