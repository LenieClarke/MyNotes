package com.example.mynotes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteRoomDatabase database;
    private NoteDao noteDao;
    private LiveData<List<Note>> listNotes;

    public NoteRepository(Application application) {
        database = NoteRoomDatabase.getDatabase(application);
        noteDao = database.noteDao();
        listNotes = noteDao.getAllLiveData();
    }

    public LiveData<List<Note>> getListNotes() {
        return listNotes;
    }

    public void insert(Note note) {
        NoteRoomDatabase.executorService.execute(() -> noteDao.insert(note));
    }

    public void update(Note note) {
        NoteRoomDatabase.executorService.execute(() -> noteDao.update(note));
    }

    public void delete(Note note) {
        NoteRoomDatabase.executorService.execute(() -> noteDao.delete(note));
    }
}