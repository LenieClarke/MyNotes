package com.example.mynotes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Objects;

import static com.example.mynotes.MainActivity.adapter;

public class AddingNewNoteActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private EditText titleAdding;
    private EditText subtitleAdding;
    private CheckBox deadlineCheckBox;
    private EditText deadlineEditText;
    private ImageButton deadlineCalendar;

    private final Calendar calendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_note);

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void init() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        scrollView = findViewById(R.id.scrollable);
        scrollView.scrollToDescendant(deadlineEditText);

        titleAdding = findViewById(R.id.titleAdding);
        subtitleAdding = findViewById(R.id.subtitleAdding);
        deadlineCheckBox = findViewById(R.id.deadlineCheckBox);
        deadlineEditText = findViewById(R.id.deadlineEditText);
        deadlineCalendar = findViewById(R.id.deadlineCalendar);

        deadlineEditText.setEnabled(false);
        deadlineCalendar.setEnabled(false);

        deadlineCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (deadlineCheckBox.isChecked()) {
                setInitialDate();
                deadlineEditText.setEnabled(true);
                deadlineCalendar.setEnabled(true);
            } else {
                deadlineEditText.setText("");
                deadlineEditText.setEnabled(false);
                deadlineCalendar.setEnabled(false);
            }
        });
    }


    public void setDate(View v) {
        new DatePickerDialog(AddingNewNoteActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dateSetListener = ((view, year, month, dayOfMonth) -> {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setInitialDate();
    });

    private void setInitialDate() {
        deadlineEditText.setText(DateUtils.formatDateTime(this, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            adapter.addNote(new NoteData(titleAdding.getText().toString(),
                    subtitleAdding.getText().toString(), deadlineEditText.getText().toString()));
            Toast.makeText(AddingNewNoteActivity.this, R.string.toastNoteIsSaved,
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddingNewNoteActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}