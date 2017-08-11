package com.tanya.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;



public class OnlineQuestionDisplay extends AppCompatActivity {

    TextView qno,question;
    List<Questions> quesList;
    RadioButton r1,r2,r3,r4;
    int correct;
    int id=1;
    int t=0;
    int score=0;

    RadioGroup radioGroup;
    Button submit;
    Questions q;
    int question_no=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_question_display);

        qno=(TextView)findViewById(R.id.questext);
        question=(TextView)findViewById(R.id.question);
        radioGroup=(RadioGroup)findViewById(R.id.optionradio);
        r1=(RadioButton)findViewById(R.id.option1);
        r2=(RadioButton)findViewById(R.id.option2);
        r3=(RadioButton)findViewById(R.id.option3);
        r4=(RadioButton)findViewById(R.id.option4);
        submit=(Button)findViewById(R.id.submitbtn);


        new OnlineQuestionDisplay.to_get_result().execute();
    }



    private class to_get_result extends AsyncTask<Void, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(OnlineQuestionDisplay.this);
        String question,opt_one,opt_two,opt_three,opt_four;
        int correct;
        Boolean success = true;
        public static final String CHECK="http://192.168.0.167/project.php";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait.......");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);

        }
        @Override
        protected Boolean doInBackground(Void... params) {
            //for_question forQuestion = new for_question("","", "", "");
            String line, response = "";
            //System.out.println("name is"+type+class_of);
            String POST_PARAMS = "Question_no="+question_no;
            //POST_PARAMS += "&class=" + class_of;


            try {

                URL url = new URL(CHECK);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                    //conn.setRequestProperty("Connection", "close");
                }
                //conn.setRequestProperty("Accept-Encoding", "");
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                // For POST only - BEGIN
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(POST_PARAMS.getBytes());
                os.flush();
                os.close();
                // For POST only - END
                //conn.connect();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
// read the response
                    System.out.println("Response Code: " + conn.getResponseCode());
                    //InputStream in = new BufferedInputStream(conn.getInputStream());

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                    Log.e("RESPONSE", response);

//                    JSONObject obj = new JSONObject(response);
//                    //forQuestion=convert_response(obj);
//                    class_of=obj.getString("Class");
//                    sub1 = obj.getString("Sub1");
//                    sub2 = obj.getString("Sub2");
//                    sub3 = obj.getString("Sub3");
//                    Questions = new Questions(Class,sub1,sub2,sub3);
//                    System.out.println("new"+class_of+"1"+sub1);
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++)
                    {
                        JSONObject object = arr.getJSONObject(i);
                        q=convert_question(object);
                    }


                }
                //System.out.println(response);
            } catch (Exception e) {
                e.printStackTrace();

            }

            return success;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            dialog.dismiss();
            set_val();
        }
    }
    private Questions convert_question(JSONObject obj) throws JSONException {

        int qno=obj.getInt("qno");
        String q = obj.getString("ques");
        String s1 = obj.getString("opt1");
        String s2 = obj.getString("opt2");
        String s3 = obj.getString("opt3");
        String s4 = obj.getString("opt4");
        int c=obj.getInt("correctoption");
        System.out.println("values returned"+qno+q+s1+s2+s3+s4);
        return new Questions(qno,q,s1,s2,s3,s4,c);
    }
    public void set_val()
    {

        setQuestionView2();
        //Log.e("RESPONSE", Questions.getClass_for());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int select=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(select);
                switch (correct)
                {
                    case 1: if(radioButton.getText().equals(q.getOption1())) { t=1;}
                        break;
                    case 2: if(radioButton.getText().equals(q.getOption2())) { t=1;}
                        break;
                    case 3: if(radioButton.getText().equals(q.getOption3())) { t=1;}
                        break;
                    case 4: if(radioButton.getText().equals(q.getOption4())) { t=1;}
                        break;



                }
                if(t==1)
                {score=score+4; t=0;

                }
                else
                {score=score-1; t=0;}

                if(question_no<5) {
                    question_no = question_no + 1;
                    new OnlineQuestionDisplay.to_get_result().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"your score:"+score,Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(OnlineQuestionDisplay.this,ResultDisplay.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    //Bundle b1= new Bundle();
                    //b1.putInt("count",count);
                    //intent.putExtras(b1);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }


    public void setQuestionView2()
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
