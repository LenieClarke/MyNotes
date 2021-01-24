package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> passwordList;

    private ImageView pinFirstImage;
    private ImageView pinSecondImage;
    private ImageView pinThirdImage;
    private ImageView pinFourthImage;

    private SharedPreferences passwordSharedPreferences;
    private static final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        init();
    }

    public void init() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);

        passwordList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                if (passwordList.size() < 4) {
                    passwordList.add("1");
                    changePinImageColor();
                }
                break;
            case R.id.button2:
                if (passwordList.size() < 4) {
                    passwordList.add("2");
                    changePinImageColor();
                }
                break;
            case R.id.button3:
                if (passwordList.size() < 4) {
                    passwordList.add("3");
                    changePinImageColor();
                }
                break;
            case R.id.button4:
                if (passwordList.size() < 4) {
                    passwordList.add("4");
                    changePinImageColor();
                }
                break;
            case R.id.button5:
                if (passwordList.size() < 4) {
                    passwordList.add("5");
                    changePinImageColor();
                }
                break;
            case R.id.button6:
                if (passwordList.size() < 4) {
                    passwordList.add("6");
                    changePinImageColor();
                }
                break;
            case R.id.button7:
                if (passwordList.size() < 4) {
                    passwordList.add("7");
                    changePinImageColor();
                }
                break;
            case R.id.button8:
                if (passwordList.size() < 4) {
                    passwordList.add("8");
                    changePinImageColor();
                }
                break;
            case R.id.button9:
                if (passwordList.size() < 4) {
                    passwordList.add("9");
                    changePinImageColor();
                }
                break;
            case R.id.button0:
                if (passwordList.size() < 4) {
                    passwordList.add("0");
                    changePinImageColor();
                }
                break;
            case R.id.buttonClear:
                if (passwordList.size() != 0) {
                    passwordList.remove(passwordList.size() - 1);
                    changePinImageColor();
                }
                break;
        }

        checkExistencePassword();
    }

    private void changePinImageColor() {
        pinFirstImage = findViewById(R.id.pinFirstImage);
        pinSecondImage = findViewById(R.id.pinSecondImage);
        pinThirdImage = findViewById(R.id.pinThirdImage);
        pinFourthImage = findViewById(R.id.pinFourthImage);

        switch (passwordList.size()) {
            case 0:
                pinFirstImage.setImageResource(R.drawable.ic_circle_grey);
                break;
            case 1:
                pinFirstImage.setImageResource(R.drawable.ic_circle_orange);
                pinSecondImage.setImageResource(R.drawable.ic_circle_grey);
                break;
            case 2:
                pinFirstImage.setImageResource(R.drawable.ic_circle_orange);
                pinSecondImage.setImageResource(R.drawable.ic_circle_orange);
                pinThirdImage.setImageResource(R.drawable.ic_circle_grey);
                break;
            case 3:
                pinFirstImage.setImageResource(R.drawable.ic_circle_orange);
                pinSecondImage.setImageResource(R.drawable.ic_circle_orange);
                pinThirdImage.setImageResource(R.drawable.ic_circle_orange);
                pinFourthImage.setImageResource(R.drawable.ic_circle_grey);
                break;
            case 4:
                pinFirstImage.setImageResource(R.drawable.ic_circle_orange);
                pinSecondImage.setImageResource(R.drawable.ic_circle_orange);
                pinThirdImage.setImageResource(R.drawable.ic_circle_orange);
                pinFourthImage.setImageResource(R.drawable.ic_circle_orange);
                break;
        }
    }

    private void checkExistencePassword() {
        passwordSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        if (passwordList.size() == 4) {
            if (passwordSharedPreferences.contains(PASSWORD_KEY)) {
                checkCorrectPassword();
            } else {
                createPassword();
            }
        }
    }

    private void createPassword() {
        passwordSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = passwordSharedPreferences.edit();
        editor.putString(PASSWORD_KEY, String.valueOf(passwordList));
        editor.apply();
        Toast.makeText(PasswordActivity.this, R.string.toastPasswordSaved,
                Toast.LENGTH_SHORT).show();
        transitionToNewActivity();
    }

    private void checkCorrectPassword() {
        passwordSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        String password = passwordSharedPreferences.getString(PASSWORD_KEY, "");
        assert password != null;
        if (password.equals(String.valueOf(passwordList))) {
            transitionToNewActivity();
        } else {
            passwordList.clear();
            pinFirstImage.setImageResource(R.drawable.ic_circle_grey);
            pinSecondImage.setImageResource(R.drawable.ic_circle_grey);
            pinThirdImage.setImageResource(R.drawable.ic_circle_grey);
            pinFourthImage.setImageResource(R.drawable.ic_circle_grey);
            Toast.makeText(PasswordActivity.this, R.string.toastPasswordIncorrect,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void transitionToNewActivity() {
        Intent intent = new Intent(PasswordActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}