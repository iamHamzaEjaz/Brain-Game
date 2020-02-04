package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
     int randonNumOne,randomNumTow,actualAnswere,correctAnswerePositon;
     Random objectRandom;

     TextView questionTv,ans1,ans2,ans3,ans4,answereStatus,score,timer;
     ArrayList<Integer> objectAnswereArrayList;

     boolean stateOfTimer = true;
     int ourScore,totalNumberOfQuestion;

     Button playBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
           objectRandom=new Random();
           questionTv=findViewById(R.id.questionTv);

           objectAnswereArrayList=new ArrayList<>();
           ans1=findViewById(R.id.ans1);

            ans2=findViewById(R.id.ans2);
            ans3=findViewById(R.id.ans3);

            ans4=findViewById(R.id.ans4);
            answereStatus=findViewById(R.id.answerestatus);

            score=findViewById(R.id.score);
            timer=findViewById(R.id.timer);

            playBtn=findViewById(R.id.playBtn);

            startTimer();
            genrateQuestion();
        }
        catch (Exception e){
            Toast.makeText(this, "onCreate"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void genrateQuestion()
    {

        try {
            if(stateOfTimer==true) {
                objectAnswereArrayList.clear();

                randonNumOne = objectRandom.nextInt(30) + 0;
                randomNumTow = objectRandom.nextInt(30) + 0;
                actualAnswere = randonNumOne + randomNumTow;
                questionTv.setText(Integer.toString(randonNumOne) + " + " + Integer.toString(randomNumTow));

                correctAnswerePositon = objectRandom.nextInt(3) + 0;
                for (int count = 0; count < 4; count++) {
                    if (count == correctAnswerePositon) {
                        objectAnswereArrayList.add(actualAnswere);
                    } else {
                        int incorrectAnswere = objectRandom.nextInt(40) + 0;
                        while (actualAnswere == incorrectAnswere) {
                            incorrectAnswere = objectRandom.nextInt(40) + 0;
                        }
                        objectAnswereArrayList.add(incorrectAnswere);
                    }
                }
                ans1.setText(Integer.toString(objectAnswereArrayList.get(0)));
                ans2.setText(Integer.toString(objectAnswereArrayList.get(1)));
                ans3.setText(Integer.toString(objectAnswereArrayList.get(2)));
                ans4.setText(Integer.toString(objectAnswereArrayList.get(3)));
            }
            }
        catch (Exception e){
            Toast.makeText(this, "genrateQuestion"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void doTheMatch(View view)
    {
        if(stateOfTimer=true) {
            try {
                if (view.getTag().toString().equals(Integer.toString(correctAnswerePositon))) {
                    ourScore++;
                    answereStatus.setText("correct Answre");
                } else {
                    answereStatus.setText("incorrect Answre");
                }
                totalNumberOfQuestion++;
                score.setText(Integer.toString(ourScore)+ "/" +Integer.toString(totalNumberOfQuestion));
                genrateQuestion();
            } catch (Exception e) {
                Toast.makeText(this, "doTheMatch" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            answereStatus.setText("incorrect Answre");
        }
    }
    private void startTimer(){
        try
        {
            new CountDownTimer(30000,10000)
            {


                @Override
                public void onTick(long millisUntilFinished) {
                    if(millisUntilFinished<=0)
                    {
                        stateOfTimer=false;
                        timer.setText(Long.toString(millisUntilFinished/1000)+"s");
                    }
                    else
                    {
                        stateOfTimer=true;
                        timer.setText("00s");
                    }

                }

                @Override
                public void onFinish() {
                        stateOfTimer=false;
                        playBtn.setEnabled(true);
                }
            }.start();

        }
        catch (Exception e)
        {
            Toast.makeText(this, "startTimer"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void playAgain(View view)
    {
        ourScore=0;
        totalNumberOfQuestion=0;
        answereStatus.setText("0 / 0");
        startTimer();
    }
}
