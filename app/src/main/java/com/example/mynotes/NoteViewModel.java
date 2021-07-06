package com.example.mynotes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> listNotes;

    public NoteViewModel(Application application) {
        super(application);
        noteRepository = App.getNoteRepository();
        listNotes = noteRepository.getListNotes();
    }

    public LiveData<List<Note>> getListNotes() {
        return listNotes;
    }

    public void insert(Note note) {
        App.getNoteRepository().insert(note);
    }

    public void update(Note note) {
        App.getNoteRepository().update(note);
    }

    public void delete(Note note) {
        App.getNoteRepository().delete(note);
    }
}