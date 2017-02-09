package com.example.bmarshall.todolistapp.model;

/**
 * Created by bmarshall on 2/8/17.
 */

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by bmarshall on 1/11/17.
 */

public class ToDoItem {

    private String title;
    private Calendar startDate;
    private Calendar dueDate;
    private Calendar reminderDateTime;

    private boolean startDateIsSet;
    private boolean dueDateIsSet;

    private boolean isCompleted;
    private boolean reminderIsSet;

    private int reminderHour;

    public ToDoItem() {
        this.title = "title not set";

        startDate = new GregorianCalendar();
        dueDate = new GregorianCalendar();

        startDateIsSet = false;
        dueDateIsSet = false;
        isCompleted = false;
        reminderIsSet = false;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate.getTime();
    }

    public Date getDueDate() {
        return dueDate.getTime();
    }

    public boolean getStartDateIsSet() {
        return startDateIsSet;
    }

    public boolean getDueDateIsSet() {
        return dueDateIsSet;
    }

    public boolean getIsComplete() {
        return isCompleted;
    }

    public int getReminderHour() {
        return reminderHour;
    }

    public boolean getReminderIsSet() {
        return reminderIsSet;
    }

    public Calendar getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(int newReminderTime) {
        reminderHour = newReminderTime;
        Calendar cal = new GregorianCalendar();
        cal.setTime(dueDate.getTime());
        cal.add(Calendar.HOUR, -reminderHour);
        reminderDateTime = cal;
        reminderIsSet = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(int year, int month, int dayOfMonth) {
        startDate.set(year, month, dayOfMonth, startDate.get(Calendar.HOUR_OF_DAY),
                startDate.get(Calendar.MINUTE));

        startDateIsSet = true;
    }

    public void setStartTime(int hourOfDay, int minute) {
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
    }

    public void setDueDate(int year, int month, int dayOfMonth) {
        dueDate.set(year, month, dayOfMonth, dueDate.get(Calendar.HOUR_OF_DAY),
                dueDate.get(Calendar.MINUTE));

        dueDateIsSet = true;
    }

    public void setDueTime(int hourOfDay, int minute) {
        dueDate.set(startDate.get(Calendar.YEAR), dueDate.get(Calendar.MONTH),
                dueDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
    }

    public void changeCompletionStatus() {
        isCompleted = !isCompleted;
    }
}
