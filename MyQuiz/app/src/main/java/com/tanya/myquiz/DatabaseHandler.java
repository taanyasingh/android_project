package com.tanya.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Tanya on 13-02-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "ques_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "questions";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +"(" +
                "qno INTEGER PRIMARY KEY, ques TEXT, optone TEXT, opttwo TEXT, optthree TEXT, optfour TEXT, correctoption INTEGER)";


       database.execSQL(CREATE_TABLE);
        String add1 ="INSERT INTO "+ TABLE_NAME +" VALUES(1,' WWW stands for??','world wide web','wide wolrd web','web world wide','none of these',1)";
        database.execSQL(add1);

        String add2 ="INSERT INTO "+ TABLE_NAME +" VALUES(2,' latest android version??','marshmallow','donut','lollypop','noughat',4)";
        database.execSQL(add2);

        String add3 ="INSERT INTO "+ TABLE_NAME +" VALUES(3,' which of the following is open source :','windows','linux','ubuntu','none of these',3)";
        database.execSQL(add3);

        String add4 ="INSERT INTO "+ TABLE_NAME +" VALUES(4,' XML stands for:','extensible markup language','HTML','CSS','JAVAScript',1)";
        database.execSQL(add4);

        String add5 ="INSERT INTO "+ TABLE_NAME +" VALUES(5,'PHP stands for ','hypertext preprocessor','pre hypertxt processor','preprocessor hypertext','NOT',1)";
        database.execSQL(add5);

        String add6 ="INSERT INTO "+ TABLE_NAME +" VALUES(6,'All of the following are examples of real security and privacy risks EXCEPT:','hackers','spam',' viruses','identity theft',2)";
        database.execSQL(add6);

        String add7 ="INSERT INTO "+ TABLE_NAME +" VALUES(7,'A process known as ____________ is used by large retailers to study trends. ','data mining','data selection','POS','data conversion',1)";
        database.execSQL(add7);

        String add8 ="INSERT INTO "+ TABLE_NAME +" VALUES(8,'Which of the following is NOT one of the four major data processing functions of a computer? ','gathering data',' processing data into information','analyzing the data or information','storing the data or information',3)";
        database.execSQL(add8);

        String add9 ="INSERT INTO "+ TABLE_NAME +" VALUES(9,'PHP stands for ','hypertext preprocessor','pre hypertext processor','preprocessor hypertext','NOT',1)";
        database.execSQL(add9);

        String add10 ="INSERT INTO "+ TABLE_NAME +" VALUES(10,'PHP stands for ','hypertext preprocessor','pre hypertext processor','preprocessor hypertext','NOT',1)";
        database.execSQL(add10);





    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(database);

    }



    public void addQuetion (Questions ques) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("qno", ques.getQno());
        values.put("ques", ques.getQuestion());
        values.put("optone",ques.getOption1());
        values.put("opttwo",ques.getOption2());
        values.put("optthree",ques.getOption3());
        values.put("optfour",ques.getOption4());
        values.put("correctoption",ques.getCorrect());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public Questions getQuestion_detail (int qno) {

        SQLiteDatabase db = getWritableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, new String[]{"qno", "ques", "optone","opttwo","optthree","optfour","correctoption"}, "qno=?",
                //new String[]{"" + qno}, null, null, null, null, null, null, null);
        Cursor cursor=db.query(TABLE_NAME, new  String[]{"qno","ques", "optone","opttwo","optthree","optfour","correctoption"}, "qno=?",
        new String[]{" "+ qno}, null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Questions question = new Questions(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),Integer.parseInt(cursor.getString(6)));

        return question;
    }

    public List<Questions> getAllQuestins() {

        List<Questions> ques = new java.util.ArrayList<>();

        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

       if (cursor.moveToFirst()) {
            do {
                Questions q = new Questions(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),Integer.parseInt(cursor.getString(6)));
                ques.add(q);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return ques;
    }

   /* public int updatePerson (Questions person) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("name", person.getName());
        //values.put("age", person.getAge());

        //int updated = database.update(TABLE_NAME, values, "id=?", new String[] {""+person.getId()});

        database.close();
        return updated;
    }*/

    public int getQuestionCount() {

        String countQuery = "SELECT *FROM "+ TABLE_NAME;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(countQuery, null);

        //cursor.close();
        return cursor.getCount();

    }
    public void deleteQuestion (Questions information){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, "qno=?", new String[] {""+ information.getQno()});

        database.close();
    }



}