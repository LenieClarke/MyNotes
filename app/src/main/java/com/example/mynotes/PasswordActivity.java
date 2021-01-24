package com.example.mynotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView pinFirstImage;
    private ImageView pinSecondImage;
    private ImageView pinThirdImage;
    private ImageView pinFourthImage;

    private String password = "";
    private SharedPreferences firstPasswordSharedPreferences;
    public static final String FIRST_PASSWORD_KEY = "firstPassword";
    private SharedPreferences passwordSharedPreferences;
    public static final String PASSWORD_KEY = "password";
    public boolean firstTime;
    public static final String IS_FIRST = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        init();
    }

    public void init() {
        firstPasswordSharedPreferences = getSharedPreferences(FIRST_PASSWORD_KEY, MODE_PRIVATE);
        firstTime = firstPasswordSharedPreferences.getBoolean(IS_FIRST, true);

        if (firstTime) {
            Intent intent = new Intent(PasswordActivity.this, SettingActivity.class);
            startActivity(intent);
        } else {
            passwordSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
            if (passwordSharedPreferences != null) {
                SharedPreferences.Editor editor = firstPasswordSharedPreferences.edit();
                editor.putBoolean(IS_FIRST, false).apply();
            }
        }

        initButtons();
    }

    public void initButtons() {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                if (password.length() < 4) {
                    password += "1";
                    changePinImageColor();
                }
                break;
            case R.id.button2:
                if (password.length() < 4) {
                    password += "2";
                    changePinImageColor();
                }
                break;
            case R.id.button3:
                if (password.length() < 4) {
                    password += "3";
                    changePinImageColor();
                }
                break;
            case R.id.button4:
                if (password.length() < 4) {
                    password += "4";
                    changePinImageColor();
                }
                break;
            case R.id.button5:
                if (password.length() < 4) {
                    password += "5";
                    changePinImageColor();
                }
                break;
            case R.id.button6:
                if (password.length() < 4) {
                    password += "6";
                    changePinImageColor();
                }
                break;
            case R.id.button7:
                if (password.length() < 4) {
                    password += "7";
                    changePinImageColor();
                }
                break;
            case R.id.button8:
                if (password.length() < 4) {
                    password += "8";
                    changePinImageColor();
                }
                break;
            case R.id.button9:
                if (password.length() < 4) {
                    password += "9";
                    changePinImageColor();
                }
                break;
            case R.id.button0:
                if (password.length() < 4) {
                    password += "0";
                    changePinImageColor();
                }
                break;
            case R.id.buttonClear:
                if (password != null && password.length() > 0) {
                    password = password.substring(0, password.length() - 1);
                    changePinImageColor();
                }
                break;
        }

        checkPassword();
    }

    private void changePinImageColor() {
        pinFirstImage = findViewById(R.id.pinFirstImage);
        pinSecondImage = findViewById(R.id.pinSecondImage);
        pinThirdImage = findViewById(R.id.pinThirdImage);
        pinFourthImage = findViewById(R.id.pinFourthImage);

        switch (password.length()) {
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

    private void checkPassword() {
        passwordSharedPreferences = getSharedPreferences(PASSWORD_KEY, MODE_PRIVATE);
        String pincode = passwordSharedPreferences.getString(PASSWORD_KEY, "");
        assert pincode != null;
        if (password.length() == 4) {
            if (pincode.equals(password)) {
                Intent intent = new Intent(PasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                password = "";
                pinFirstImage.setImageResource(R.drawable.ic_circle_grey);
                pinSecondImage.setImageResource(R.drawable.ic_circle_grey);
                pinThirdImage.setImageResource(R.drawable.ic_circle_grey);
                pinFourthImage.setImageResource(R.drawable.ic_circle_grey);
                Toast.makeText(PasswordActivity.this, R.string.toastPasswordIncorrect,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}