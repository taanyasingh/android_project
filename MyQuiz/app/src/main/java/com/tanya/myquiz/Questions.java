package com.tanya.myquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Questions  {

   int qno;
    String quetion;
    String option1, option2,option3,option4;
    int correct;

    public Questions(int qno,String quetion, String option1, String option2, String option3, String option4, int correct)
    {
        this.qno=qno;
        this.quetion=quetion;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.correct=correct;
    }

    public int getQno(){return qno;}
    public String getQuestion(){return quetion;}
    public String getOption1(){ return option1;}
    public String getOption2(){ return option2;}
    public String getOption3(){ return option3;}
    public String getOption4(){ return option4;}
    public int getCorrect(){return correct;}

    public void setQno( int Qno) {qno=Qno;}
    public void setQuetion(String Question){quetion=Question;}
    public void setOption1(String Option1){ option1=Option1;}
    public void setOption2(String Option2){  option2=Option2;}
    public void setOption3(String Option3){  option3=Option3;}
    public void setOption4(String Option4){  option4=Option4;}
    public void setCorrect(int Correct){ correct=Correct;}


}
