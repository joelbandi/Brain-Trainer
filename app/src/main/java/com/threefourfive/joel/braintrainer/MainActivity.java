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
    TextView scoreView;
    TextView a0,a1,a2,a3;
    Button button;
    Random random;
    private CountDownTimer count;
    TextView question;
    MediaPlayer mplayer;
    boolean active;
    int option;
    int questionNo;
    int Score;

    private static int rand(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        return (int)(fraction + aStart);
    }

    public void updateScore(boolean correct){
        if(correct){
            Score++;
            questionNo++;
        }else{
            questionNo++;
        }
        scoreView.setText(Integer.toString(Score)+"/"+Integer.toString(questionNo));
    }

    public void updateTime(int timeleft){
        time.setText(Integer.toString(timeleft)+"s");
    }

    public void answerSelect(View view){
        if(view.getTag().equals(Integer.toString(option-1))){
            updateScore(true);
        }
        else{
            updateScore(false);
        }
        setQAField();
    }

    public void onClick(View view){
        button.setVisibility(View.GONE);
        active = true;
        setButtonState();


        count = new CountDownTimer(60100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime((int) (millisUntilFinished / 1000));
                //Log.i("Time left: ",Long.toString(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                active = false;
                time.setText("0s");
                mplayer = MediaPlayer.create(getApplicationContext(), R.raw.ping);
                mplayer.start();
                button.setText("Your Score is : " + Integer.toString(Score) + " out of " + Integer.toString(questionNo) + "\nTry Again!");
                setButtonState();
                button.setVisibility(View.VISIBLE);

            }
        }.start();

        setQAField();
    }

    public void setQAField(){

        question.setVisibility(View.VISIBLE);
        int random1 = rand(1,100,random);
        int random2 = rand(1, 100, random);
        String solutionstring = String.valueOf(random1 + random2);
        option = rand(1, 4, random);
        String questionString = Integer.toString(random1)+" + "+Integer.toString(random2);
        question.setText(questionString);
        switch (option){
            case 1: a0.setText(solutionstring);
                    a1.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a2.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a3.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    break;
            case 2: a0.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a1.setText(solutionstring);
                    a2.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a3.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    break;
            case 3: a0.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a2.setText(solutionstring);
                    a1.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a3.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    break;
            case 4: a0.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a3.setText(solutionstring);
                    a2.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    a1.setText(Integer.toString(rand(random1+random2-25,random1+random2+25,random)));
                    break;
            default:Log.i("Error: ","1");
                    break;

        }


    }

    public void setButtonState(){
        a0.setClickable(active);
        a1.setClickable(active);
        a2.setClickable(active);
        a3.setClickable(active);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time = (TextView)findViewById(R.id.timeleft);
        question = (TextView)findViewById(R.id.question);
        scoreView = (TextView)findViewById(R.id.score);
        button = (Button)findViewById(R.id.Button);
        a0 = (TextView)findViewById(R.id.a0);
        a1 = (TextView)findViewById(R.id.a1);
        a2 = (TextView)findViewById(R.id.a2);
        a3 = (TextView)findViewById(R.id.a3);
        random = new Random();
        question.setVisibility(View.INVISIBLE);
        active = false;
        setButtonState();
        Score=questionNo=0;






    }
}
