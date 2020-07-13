package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    CountDownTimer countDownTimer;

    public void updateTimer(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;

        String secondString = String.valueOf(seconds);
        String minutesString = String.valueOf(minutes);

        if (secondString.length() < 2) {
            secondString = "0" + secondString;
        }
        if (minutesString.length() < 2) {
            minutesString = "0" + minutesString;
        }

        timerTextView.setText(minutesString + ":" + secondString);
    }

    public void controlTimer(View view) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("00:00");

                    MediaPlayer mplayer = MediaPlayer.create(MainActivity.this,R.raw.airhorn);
                    mplayer.start();
                }
            }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
                if (countDownTimer != null){
                    countDownTimer.cancel();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}