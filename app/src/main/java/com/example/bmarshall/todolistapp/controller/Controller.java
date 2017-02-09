package com.example.bmarshall.todolistapp.controller;

import android.app.Application;

import com.example.bmarshall.todolistapp.model.ToDoItem;
import com.example.bmarshall.todolistapp.model.ToDoListDatabaseHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bmarshall on 2/6/17.
 */

public class Controller extends Application {

    private ToDoListDatabaseHelper toDoListDatabaseHelper;
    private ToDoItem toDoItem;

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

    public void insertToDoItem(String title, String startDate, String dueDate, boolean isComplete){
        toDoListDatabaseHelper.insertToDoItem(title, startDate, dueDate, isComplete);
    }

    public void insertReminder(int ItemId, String reminderDate){
        toDoListDatabaseHelper.insertReminder(ItemId, reminderDate);
    }

    public void createToDoItem(){
        toDoItem = new ToDoItem();
    }

    public void setItemReminderDateTime(int newReminderTime) {
        toDoItem.setReminderDateTime(newReminderTime);
    }

    public void setItemTitle(String title) {
        toDoItem.setTitle(title);
    }

    public void setItemStartDate(int year, int month, int dayOfMonth) {
        toDoItem.setStartDate(year, month, dayOfMonth);
    }

    public void setItemStartTime(int hourOfDay, int minute) {
        toDoItem.setStartTime(hourOfDay, minute);
    }

    public void setItemDueDate(int year, int month, int dayOfMonth) {
        toDoItem.setDueDate(year, month, dayOfMonth);
    }

    public void setItemDueTime(int hourOfDay, int minute) {
        toDoItem.setDueTime(hourOfDay, minute);
    }

    public boolean getItemDueDateIsSet() {
        return toDoItem.getDueDateIsSet();
    }

    public String getItemTitle() {
        return toDoItem.getTitle();
    }

    public Date getStartDate() {
        return toDoItem.getStartDate();
    }

    public Date getDueDate() {
        return toDoItem.getDueDate();
    }

    public void changeItemCompletionStatus() {
        toDoItem.changeCompletionStatus();
    }
}
