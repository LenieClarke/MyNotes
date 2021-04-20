package com.example.mynotes;

import android.app.Application;

public class App extends Application {

    private static Keystore keystore;
    private static NoteRepository noteRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        /* Конкретная реализация выбирается только здесь.
           Изменением одной строчки здесь,
           мы заменяем реализацию во всем приложении!
        */

        keystore = new SimpleKeystore(this);
        noteRepository = new NoteRepository((Application) getApplicationContext());
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static Keystore getKeystore() {
        return keystore;
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }
}