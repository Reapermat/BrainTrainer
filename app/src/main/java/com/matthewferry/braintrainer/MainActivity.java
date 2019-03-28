package com.matthewferry.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView time;
    TextView score;
    TextView equation;
    TextView correct;
    TextView End;
    CountDownTimer countDownTimer;
    int a;
    int b;
    ArrayList<Integer> answer = new ArrayList<>();
    int LocationOfCorrectAnswer;
    Random rand;
    int i=0;
    int j=0;


    public void findView()
    {
        start = findViewById(R.id.ButtonStart);
        score = findViewById(R.id.score);
        time = findViewById(R.id.time);
        equation = findViewById(R.id.equation);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgain = findViewById(R.id.playAgain);
        correct = findViewById(R.id.correct);
        End = findViewById(R.id.End);
    }

    public void setVis()
    {
        start.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        equation.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        End.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void setUnVis()
    {
        time.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        equation.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.VISIBLE);
        End.setVisibility(View.VISIBLE);

    }

    public void start(View view)
    {
        setVis();
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(Long.toString(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                setUnVis();
                End.setText("Your score was: " + Integer.toString(i) + " / " + Integer.toString(j));
                correct.setVisibility(View.INVISIBLE);
            }
        }.start();
        CreateGame();
    }
    public void playAgain(View view)
    {
        i=0;
        j=0;
        score.setText("0 / 0");
        CreateGame();
        start(view);
    }

    public void nextStage()//not needed now= added answer.clear to CreateGame;
    {
        answer.clear();
        CreateGame();
    }
    public void CreateGame()
    {
        answer.clear();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        equation.setText(Integer.toString(a) + " + " + Integer.toString(b));

        LocationOfCorrectAnswer = rand.nextInt(4);

        for(int i=0 ;i<4; i++)
        {
            if(i == LocationOfCorrectAnswer)
                answer.add(a+b);
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b)
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view)
    {

        if(Integer.toString(LocationOfCorrectAnswer).equals(view.getTag().toString()))
        {
            i++;
            j++;
            score.setText(Integer.toString(i) + " / " + Integer.toString(j));
            correct.setVisibility(View.VISIBLE);
            correct.setText("Correct!");
            //nextStage();
            CreateGame();
        }
        else
        {
            j++;
            score.setText(Integer.toString(i) + " / " + Integer.toString(j));
            correct.setVisibility(View.VISIBLE);
            correct.setText("Wrong!");
           // nextStage();
            CreateGame();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);


    }
}
