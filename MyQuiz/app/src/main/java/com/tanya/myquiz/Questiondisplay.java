package com.tanya.myquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class Questiondisplay extends AppCompatActivity {
        TextView qno,question,timertext;
        List<Questions> quesList;
        RadioButton r1,r2,r3,r4;
        int correct;
         int id=0;
        int t=0,count=0;
        int score=0;

    DatabaseHandler handler;
    Questions q;

    RadioGroup radioGroup;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiondisplay);

         handler = new DatabaseHandler(getApplicationContext());
        //handler = new DatabaseHandler(getApplicationContext());
        quesList= handler.getAllQuestins();
        q=quesList.get(id);

        qno=(TextView)findViewById(R.id.questext);
        question=(TextView)findViewById(R.id.question);
        radioGroup=(RadioGroup)findViewById(R.id.optionradio);
        r1=(RadioButton)findViewById(R.id.option1);
        r2=(RadioButton)findViewById(R.id.option2);
        r3=(RadioButton)findViewById(R.id.option3);
        r4=(RadioButton)findViewById(R.id.option4);
        timertext=(TextView)findViewById(R.id.timertext);
        submit=(Button)findViewById(R.id.submitbtn);

        setQuestionView();
        count=handler.getQuestionCount();
        System.out.println("no of questions......"+count);




        System.out.print("value of "+ r1.isSelected());


        ShowQuestion();



    }


    public void startTimer(long time)
    {
        CountDownTimer counter=new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //Log.d("counter_label", "Counter text should be changed");
                long   min,sec;
                min=millisUntilFinished/60000;
                sec=millisUntilFinished%60000;
                timertext.setText(min+":m " + sec/1000 +":s");

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"done....!!",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Questiondisplay.this,ResultDisplay.class);
                Bundle b = new Bundle();
                b.putInt("score", score); //Your score
                intent.putExtras(b); //Put your score to your next Intent
                Bundle b1= new Bundle();
                b1.putInt("count",count);
                intent.putExtras(b1);
                startActivity(intent);
                finish();
            }
        }.start();
    }



    public  void ShowQuestion()
    {

        /* private void startTimer(long time){
    CountDownTimer counter = new CountDownTimer(30000, 1000){
        public void onTick(long millisUntilDone){

           Log.d("counter_label", "Counter text should be changed");
          tv.setText("You have " + millisUntilDone + "ms");
        }

        public void onFinish() {
            tv.setText("DONE!");

        }
    }.start();
}*/


        startTimer(2000);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int select = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(select);


                if(correct==1)
                {
                    if(radioButton.getText().equals(q.getOption1()))
                    {
                   // Toast.makeText(getApplicationContext(),"CORRECT OPTION ",Toast.LENGTH_LONG).show();
                    t=1; }
                }
                if( correct==2)
                {
                    if(radioButton.getText().equals(q.getOption2()))
                    {
                    //Toast.makeText(getApplicationContext(),"CORRECT OPTION ",Toast.LENGTH_LONG).show();
                    t=1; }
                }
                if( correct==3)
                {
                    if(radioButton.getText().equals(q.getOption3()))
                    {
                    //Toast.makeText(getApplicationContext(),"CORRECT OPTION ",Toast.LENGTH_LONG).show();
                    t=1; }
                }
                if(correct==4)
                {
                    if(radioButton.getText().equals(q.getOption4()))
                    {
                    //Toast.makeText(getApplicationContext(),"CORRECT OPTION ",Toast.LENGTH_LONG).show();
                    t=1;}
                }

                if(t==0)
                {
                    score=score-1;
                    t=0;
                }
                else {
                    score = score + 4;
                    t=0;
                }

                id=id+1;
                if(id<=count-1)
                {

                    q=quesList.get(id);
                    setQuestionView();
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),"your score:"+score,Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(Questiondisplay.this,ResultDisplay.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    Bundle b1= new Bundle();
                    b1.putInt("count",count);
                    intent.putExtras(b1);
                    startActivity(intent);
                    finish();

                    //Intent i =new Intent()
                }

            }
        });






    }
    public void setQuestionView()
    {
        qno.setText("Question no. "+q.getQno());
        question.setText(q.getQuestion());
        r1.setText(q.getOption1());
        r2.setText(q.getOption2());
        r3.setText(q.getOption3());
        r4.setText(q.getOption4());
        correct=q.getCorrect();
    }


}
