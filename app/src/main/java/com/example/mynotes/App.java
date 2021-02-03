package com.example.mynotes;

import android.app.Application;

public class App extends Application {
//    private static NoteRepository noteRepository;
    private static Keystore keystore;
    @Override
    public void onCreate() {
        super.onCreate();

        /* Конкретная реализация выбирается только здесь.
           Изменением одной строчки здесь,
           мы заменяем реализацию во всем приложении!
        */

//        noteRepository = new FileNoteRepository(this);
        keystore = new SimpleKeystore(this);
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
//    public static NoteRepository getNoteRepository() {
//        return noteRepository;
//    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static Keystore getKeystore() {
        return keystore;
    }
}




// Создать статические переменные, хранящие необходимые нам реализации интерфейсов и геттеры для них;

// Переопределить метод onCreate() и в нём создать необходимые нам реализации классов.
// Обратите внимание класс Application является наследником Context,
// так что если нам нужен Context мы можем использовать this;

//В приложении, если нам понадобился NoteRepository или Keystore,
// мы можем обратиться к App.getNoteRepository() и получить реализацию.
// Причем нам не важно какую именно!