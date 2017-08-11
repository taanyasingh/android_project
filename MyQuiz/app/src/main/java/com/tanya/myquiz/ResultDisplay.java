package com.tanya.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


public class ResultDisplay extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);
        back=(Button)findViewById(R.id.back);
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        		//get text view
        TextView t=(TextView)findViewById(R.id.score);

        		//get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        t.setText(t.getText().toString()+ score);
        		//display score
        Bundle b1=getIntent().getExtras();
        int count=b1.getInt("count");
        //System.out.println("no of questions......"+count);
        //score=(int)(score/count*4)*5;
        if(score<1) {
            bar.setRating(0);
        }
        else
        {
            bar.setRating(score);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultDisplay.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
