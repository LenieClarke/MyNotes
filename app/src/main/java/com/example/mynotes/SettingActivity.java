package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

    private EditText enterNewPin;
    private ImageButton visibilityButton;
    private Button saveButton;

    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
    }

    private void init() {
        if (!App.getKeystore().notHasPincode()) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

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
                App.getKeystore().saveNewPincode(newPassword);
                Toast.makeText(SettingActivity.this, R.string.toastPasswordSaved,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}