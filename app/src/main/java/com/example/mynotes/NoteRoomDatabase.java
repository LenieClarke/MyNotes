package com.example.mynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase database;
    static final ExecutorService executorService =
            Executors.newFixedThreadPool(4);

    static NoteRoomDatabase getDatabase(final Context context) {
        if (database == null) {
            synchronized (NoteRoomDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "database_notes3").build();
                }
            }
        }
        return database;
    }
}