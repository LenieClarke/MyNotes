package com.example.mynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.App;
import com.example.mynotes.R;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView pinFirstImage;
    private ImageView pinSecondImage;
    private ImageView pinThirdImage;
    private ImageView pinFourthImage;

    private String password = "";
    public boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        init();
    }

    public void init() {
        firstTime = App.getKeystore().notHasPincode();

        if (firstTime) {
            Intent intent = new Intent(PasswordActivity.this, NewPasswordActivity.class);
            startActivity(intent);
        } else {
            App.getKeystore().hasPincode();
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
        if (password.length() == 4) {
            if (App.getKeystore().checkPincode(password)) {
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