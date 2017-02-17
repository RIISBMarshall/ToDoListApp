package com.example.bmarshall.todolistapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bmarshall.todolistapp.R;
import com.example.bmarshall.todolistapp.model.ToDoItem;

import java.text.SimpleDateFormat;

public class ViewItemActivity extends AppCompatActivity {

    public static final String EXTRA_ITEMNO = "toDoItemNo";
    private int currentSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        currentSelectedItem = getIntent().getIntExtra(EXTRA_ITEMNO, 0);

        ToDoItem itemToView = ToDoItem.toDoItems.get(currentSelectedItem);

        Button completeButton = (Button) findViewById(R.id.completeButton);
        if (ToDoItem.toDoItems.get(currentSelectedItem).getIsComplete()) {
            completeButton.setText(R.string.setIncomplete);
        } else {
            completeButton.setText(R.string.completeItem);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm a");

        TextView titleView = (TextView) findViewById(R.id.titleView);
        titleView.setText("Item: " + itemToView.getTitle());

        TextView startDateView = (TextView) findViewById(R.id.startDateView);
        if (itemToView.getStartDateIsSet()) {
            String startDate = sdf.format(itemToView.getStartDate().getTime());
            startDateView.setText("Start Date: " + startDate);
        } else {
            startDateView.setText("Start Date: Not set");
        }

        TextView dueDateView = (TextView) findViewById(R.id.dueDateView);
        if (itemToView.getDueDateIsSet()) {
            String dueDate = sdf.format(itemToView.getDueDate().getTime());
            dueDateView.setText("Due Date: " + dueDate);
        } else {
            dueDateView.setText("Due Date: Not set");
        }

        TextView reminderView = (TextView) findViewById(R.id.reminderView);
        if (itemToView.getDueDateIsSet() && itemToView.getReminderIsSet()) {
            String reminderDateTime = sdf.format(itemToView.getReminderDateTime().getTime());
            reminderView.setText("Reminder Date: " + reminderDateTime);
        } else {
            reminderView.setText("Reminder: reminder or due date not set");
        }
    }

    public void changeCompletionStatus(View view) {
        ToDoItem.toDoItems.get(currentSelectedItem).changeCompletionStatus();
        Button completeButton = (Button) findViewById(R.id.completeButton);
        if (ToDoItem.toDoItems.get(currentSelectedItem).getIsComplete()) {
            completeButton.setText(R.string.setIncomplete);
        } else {
            completeButton.setText(R.string.completeItem);
        }
        Intent intent = new Intent(ViewItemActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteItem(View view) {
        ToDoItem.toDoItems.remove(currentSelectedItem);
        Intent intent = new Intent(ViewItemActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickEditItem(View view) {
        Intent intent = new Intent(ViewItemActivity.this, AddItemActivity.class);
        intent.putExtra("itemToEdit", currentSelectedItem);
        startActivity(intent);
    }
}
