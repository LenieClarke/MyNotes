package com.example.mynotes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String subtitle;

    @ColumnInfo(name = "deadline_checkbox")
    private boolean deadlineCheckbox;

    private long deadline;

    @ColumnInfo(name = "last_update")
    private long update;


    public Note(String title, String subtitle, boolean deadlineCheckbox, long deadline) {
        this.title = title;
        this.subtitle = subtitle;
        this.deadlineCheckbox = deadlineCheckbox;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isDeadlineCheckbox() {
        return deadlineCheckbox;
    }

    public long getDeadline() {
        return deadline;
    }

    public long getUpdate() {
        return update;
    }

    public void setUpdate(long update) {
        this.update = update;
    }
}