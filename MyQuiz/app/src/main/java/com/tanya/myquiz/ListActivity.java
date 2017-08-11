package com.tanya.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    public String topics[] = {
            "Age",
            "Alligation or Mixture",
            "Area",
            "Average",
            "Bankers Discount",
            "Boats and Streams",
            "Calender",
            "Chain Rule",
            "Clock",
            "Compound Intrest",
            "Discount",
            "Decimal Fractions",
            "Hand Shake",
            "HCF and LCM",
            "Height and Distance",
            "Logarithm",
            "Missing number",
            "Number Problems",
            "Odd man out",
            "Partnerships",
            "Percentage",
            "Permutations and Combinations",
            "Pipes and Cistern",
            "Probability",
            "Profit and Loss",
            "Races and Games",
            "Ratio and Proportions",
            "Simple Interest",
            "Square and Cube Root",
            "Stock and Share",
            "Surds and Indices",
            "Time and Distance",
            "Time and Work",
            "Trains",
            "Volume and Surface Area"};
    public int[] icons = {
            R.drawable.age,
            R.drawable.allegation,
            R.drawable.area,
            R.drawable.averagge,
            R.drawable.bank_discount,
            R.drawable.boats,
            R.drawable.calender,
            R.drawable.chainrule,
            R.drawable.clock,
            R.drawable.compound_intrst,
            R.drawable.discount,
            R.drawable.fraction,
            R.drawable.handshake,
            R.drawable.hcf_lcm,
            R.drawable.height_distance,
            R.drawable.log,
            R.drawable.missing_no,
            R.drawable.numbers_logo2,
            R.drawable.oddmanout,
            R.drawable.partnership,
            R.drawable.fraction,
            R.drawable.permutation,
            R.drawable.pipes,
            R.drawable.probab,
            R.drawable.profit_loss,
            R.drawable.race,
            R.drawable.ratio,
            R.drawable.simple_intrst,
            R.drawable.square_rrot,
            R.drawable.stock_share,
            R.drawable.surd,
            R.drawable.time_distance,
            R.drawable.time_work,
            R.drawable.trains,
            R.drawable.volume_surface_arae
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView lv = (ListView) findViewById(R.id.mylist);
        CustomAdapter adapter = new CustomAdapter(this,topics,icons);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListActivity.this,lv.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                Intent i= new Intent(ListActivity.this,Questiondisplay.class);
                startActivity(i);
                finish();
            }
        });



           }



    }

