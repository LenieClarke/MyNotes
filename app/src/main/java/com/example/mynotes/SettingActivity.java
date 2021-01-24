package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.mynotes.PasswordActivity.FIRST_PASSWORD_KEY;
import static com.example.mynotes.PasswordActivity.IS_FIRST;
import static com.example.mynotes.PasswordActivity.PASSWORD_KEY;

public class SettingActivity extends AppCompatActivity {

    private EditText enterNewPin;
    private ImageButton visibilityButton;
    private Button saveButton;

    private String newPassword;
    private SharedPreferences pinSharedPreferences;
    private SharedPreferences firstPinSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
    }

    private void init() {
        enterNewPin = findViewById(R.id.enterNewPin);
        visibilityButton = findViewById(R.id.visibilityButton);
        saveButton = findViewById(R.id.saveButton);

        final boolean[] changeVisibility = {true};

        visibilityButton.setOnClickListener(v -> {
            if (changeVisibility[0]) {
                visibilityButton.setImageResource(R.drawable.ic_baseline_visibility_24);
                enterNewPin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                changeVisibility[0] = false;
            } else {
                visibilityButton.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                enterNewPin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                changeVisibility[0] = true;
            }
        });

        saveButton.setOnClickListener(v -> {
            newPassword = enterNewPin.getText().toString();
            if (newPassword.length() < 4) {
                enterNewPin.setText("");
                Toast.makeText(SettingActivity.this, R.string.toastEnterPin, Toast.LENGTH_LONG).show();
            } else {
                pinSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = pinSharedPreferences.edit();
                editor.putString(PASSWORD_KEY, newPassword).apply();

                firstPinSharedPreferences = getSharedPreferences(FIRST_PASSWORD_KEY, MODE_PRIVATE);
                SharedPreferences.Editor firstEditor = firstPinSharedPreferences.edit();
                firstEditor.putBoolean(IS_FIRST, false).apply();

                Toast.makeText(SettingActivity.this, R.string.toastPasswordSaved,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}