package com.example.bmarshall.todolistapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.bmarshall.todolistapp.R;
import com.example.bmarshall.todolistapp.controller.Controller;
import com.example.bmarshall.todolistapp.model.ToDoListDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteException;

public class MainActivity extends AppCompatActivity {

    private int currentSelectedItem;
    private SimpleDateFormat dueDateFormat;
    private ListView toDoListView;
    private ListView reminderListView;

    private CursorAdapter toDoItemAdapter;
    private CursorAdapter reminderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Controller controller = (Controller) getApplicationContext();

        //Instantiate Views
        toDoListView = (ListView) findViewById(R.id.toDo_list);
        reminderListView = (ListView) findViewById(R.id.reminderList);

        //controller.insertToDoItem("testing it", "10 Jan 2017", "20 Jan 2017", false);

        //controller.insertReminder(6, "10 Jan 2017");

        showToDoItems(controller);

        showReminders(controller);
    }

    public void showToDoItems(Controller controller){
        toDoItemAdapter = new SimpleCursorAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                controller.getToDoItems(),
                new String[]{"TITLE"},
                new int[]{android.R.id.text1, 0});

        toDoListView.setAdapter(toDoItemAdapter);

        /*toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewItemActivity.class);
                currentSelectedItem = position;
                intent.putExtra(ViewItemActivity.EXTRA_ITEMNO, currentSelectedItem);
                startActivity(intent);
            }
        });*/
    }

    public void showReminders(Controller controller){
        reminderAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                controller.getReminders(), new String[]{"REMINDER_DATE"}, new int[]{android.R.id.text1});

        reminderListView.setAdapter(reminderAdapter);
    }

    //Set method to handle clicks on the add item button
    public void onClickAddItem(View view) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }
}
