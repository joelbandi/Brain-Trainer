package com.threefourfive.joel.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView time;
    TextView score;
    TextView a0,a1,a2,a3;
    Button button;
    Random random;
    private CountDownTimer count;
    TextView question;
    MediaPlayer mplayer;

    private static int rand(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);
        return randomNumber;
    }

    public void updateScore(boolean correct){

    }

    public void updateTime(int timeleft){
        time.setText(Integer.toString(timeleft)+"s");
    }

    public void onClick(View view){
        button.setVisibility(View.GONE);
        count = new CountDownTimer(60031, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime((int)(millisUntilFinished/1000));
                Log.i("messsssagee",Long.toString(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                time.setText("0s");
                mplayer = MediaPlayer.create(getApplicationContext(), R.raw.ping);
                mplayer.start();
                button.setText("Try Again!");
                button.setVisibility(View.VISIBLE);
            }
        }.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time = (TextView)findViewById(R.id.timeleft);
        question = (TextView)findViewById(R.id.question);
        score = (TextView)findViewById(R.id.score);
        button = (Button)findViewById(R.id.Button);
        a0 = (TextView)findViewById(R.id.a0);
        a1 = (TextView)findViewById(R.id.a1);
        a2 = (TextView)findViewById(R.id.a2);
        a3 = (TextView)findViewById(R.id.a3);
        random = new Random();






    }
}
