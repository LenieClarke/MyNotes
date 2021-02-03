package com.example.mynotes;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SimpleKeystore implements Keystore {

    private final Context context;

    private SharedPreferences firstPasswordSharedPreferences;
    public static final String FIRST_PASSWORD_KEY = "firstPassword";
    private SharedPreferences passwordSharedPreferences;
    public static final String PASSWORD_KEY = "password";
    public static final String IS_FIRST = "first";

    SimpleKeystore(Context context) {
        this.context = context;
    }

    @Override
    public boolean notHasPincode() {
        firstPasswordSharedPreferences = context.getSharedPreferences(FIRST_PASSWORD_KEY, MODE_PRIVATE);
        return firstPasswordSharedPreferences.getBoolean(IS_FIRST, true);
    }

    @Override
    public void hasPincode() {
        passwordSharedPreferences = context.getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        if (passwordSharedPreferences != null) {
            SharedPreferences.Editor editor = firstPasswordSharedPreferences.edit();
            editor.putBoolean(IS_FIRST, false).apply();
        }
    }

    @Override
    public boolean checkPincode(String pin) {
        passwordSharedPreferences = context.getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        return pin.equals(passwordSharedPreferences.getString(PASSWORD_KEY, ""));
    }

    @Override
    public void saveNewPincode(String pin) {
        SharedPreferences pinSharedPreferences = context.getSharedPreferences(PASSWORD_KEY,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pinSharedPreferences.edit();
        editor.putString(PASSWORD_KEY, pin).apply();

        SharedPreferences firstPinSharedPreferences = context.getSharedPreferences(FIRST_PASSWORD_KEY,
                MODE_PRIVATE);
        SharedPreferences.Editor firstEditor = firstPinSharedPreferences.edit();
        firstEditor.putBoolean(IS_FIRST, false).apply();
    }
}