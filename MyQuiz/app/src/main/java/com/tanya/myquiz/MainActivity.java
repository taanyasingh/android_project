package com.tanya.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text1=(TextView)findViewById(R.id.t1);
        TextView text2=(TextView)findViewById(R.id.t2);
        TextView text3=(TextView)findViewById(R.id.t3);
        TextView text4=(TextView)findViewById(R.id.t4);
        TextView text5=(TextView)findViewById(R.id.t5);
        TextView text6=(TextView)findViewById(R.id.t6);


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListActivity.class );
                startActivity(i);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListActivity.class );
                startActivity(i);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListActivity.class );
                startActivity(i);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,OnlineQuestionDisplay.class);
                startActivity(i);
                finish();
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListActivity.class );
                startActivity(i);
            }
        });

    }
}
