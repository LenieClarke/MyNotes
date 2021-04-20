package com.example.mynotes.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.mynotes.App;
import com.example.mynotes.Note;
import com.example.mynotes.R;

import java.util.Calendar;
import java.util.Objects;

import static com.example.mynotes.NoteAdapter.EXTRA_DEADLINE;
import static com.example.mynotes.NoteAdapter.EXTRA_DEADLINE_CHECKBOX;
import static com.example.mynotes.NoteAdapter.EXTRA_ID;
import static com.example.mynotes.NoteAdapter.EXTRA_SUBTITLE;
import static com.example.mynotes.NoteAdapter.EXTRA_TITLE;
import static com.example.mynotes.NoteAdapter.savingUpdate;

public class AddingNewNoteActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private EditText titleAdding;
    private EditText subtitleAdding;
    private CheckBox deadlineCheckBox;
    private EditText deadlineEditText;
    private ImageButton deadlineCalendar;

    private final Calendar calendar = Calendar.getInstance();
    private long deadlineDate;
    public int idUpdate;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_note);

        init();
        updateNote();
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
                setInitialDateTime();
                deadlineEditText.setEnabled(true);
                deadlineCalendar.setEnabled(true);
            } else {
                deadlineEditText.setText("");
                deadlineDate = 0;
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
        setInitialDateTime();
    });

    public void setTime(View v) {
        new TimePickerDialog(AddingNewNoteActivity.this, timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                true).show();
        setDate(v);
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        setInitialDateTime();
    };

    private void setInitialDateTime() {
        deadlineEditText.setText(DateUtils.formatDateTime(this, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE |
                        DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME |
                        DateUtils.FORMAT_24HOUR));
        deadlineDate = calendar.getTimeInMillis();
    }

    private void saveNote() {
        Note note = new Note(titleAdding.getText().toString(), subtitleAdding.getText().toString(),
                deadlineCheckBox.isChecked(), deadlineDate);
        if (savingUpdate) {
            note.setId(idUpdate);
            App.getNoteRepository().update(note);
            savingUpdate = false;
        } else {
            App.getNoteRepository().insert(note);
        }
    }

    private void updateNote(){
        if(savingUpdate){
            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.editingNote);
            idUpdate = (int) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_ID));
            String title =
                    Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_TITLE)).toString();
            String subtitle =
                    Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_SUBTITLE)).toString();
            boolean checkbox =
                    (boolean) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_DEADLINE_CHECKBOX));
            long deadline =
                    (long) Objects.requireNonNull(getIntent().getSerializableExtra(EXTRA_DEADLINE));

            titleAdding.setText(title);
            subtitleAdding.setText(subtitle);
            deadlineCheckBox.setChecked(checkbox);
            if (checkbox) {
                deadlineEditText.setText(DateUtils.formatDateTime(this, deadline,
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE |
                                DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME |
                                DateUtils.FORMAT_24HOUR));
                deadlineDate = deadline;
            }
        }
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
            if (titleAdding.getText().length() > 0 | subtitleAdding.getText().length() > 0) {
                saveNote();
                Toast.makeText(AddingNewNoteActivity.this, R.string.toastNoteIsSaved,
                    Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(AddingNewNoteActivity.this, R.string.toastNoteIsEmpty,
                        Toast.LENGTH_LONG).show();
            }
            finish();
            return true;
        }
        if (id == android.R.id.home) {
            savingUpdate = false;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}