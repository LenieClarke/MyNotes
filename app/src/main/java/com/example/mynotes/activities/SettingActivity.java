package com.example.mynotes.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.R;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.M)
public class SettingActivity extends AppCompatActivity {

    private TextView changePasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
    }

    private void init() {
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.settings);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        changePasswordText = findViewById(R.id.changePasswordText);

        changePasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, NewPasswordActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void backHome() {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            backHome();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backHome();
    }
}