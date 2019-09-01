package com.example.millionaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "QuestionData";
    private Context context;
    Cursor cursor2;
    int x, questionnumber;

    public QuestionDatabase(Context context) {
        super(context, "QuestionDB2", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List<Question> getAllQuestion() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Question> questionList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setLv(cursor.getInt(1));
                question.setQuestion(cursor.getString(3));
                question.setA(cursor.getString(4));
                question.setB(cursor.getString(5));
                question.setC(cursor.getString(6));
                question.setD(cursor.getString(7));
                question.setR(cursor.getString(8).toUpperCase());
                questionList.add(question);

            } while (cursor.moveToNext());
        }
        db.close();
        return questionList;
    }

    void addQuestion(Question question) {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int x = question.getLv() * 100;
        int y = 0;
        while (!cursor.isAfterLast()) {
            if (Integer.valueOf(cursor.getString(2)) > x && Integer.valueOf(cursor.getString(2)) > y && Integer.valueOf(cursor.getString(2)) < x + 99) {
                y = Integer.valueOf(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        ContentValues values = new ContentValues();
        values.put("level", question.getLv());
        values.put("code", y + 1);
        values.put("question", question.getQuestion());
        values.put("a", question.getA());
        values.put("b", question.getB());
        values.put("c", question.getC());
        values.put("d", question.getD());
        values.put("true", question.getR().toLowerCase());

        db.insert(TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    public int deleteStudent(String que) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "question" + "=?", new String[]{que});
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    void displayQuesstion() {
        MainActivity.countDownTimer.cancel();
        MainActivity.video_num = MainActivity.n = MainActivity.m = 0;
        MainActivity.progressBar.setProgress(0);
        x++;
        if (x == 5) {
            if (MainActivity.soundSwitch.isChecked())
                MainActivity.soundPool.play(MainActivity.soundPoolWin, 1, 1, 0, 0, 1);
            Toast.makeText(context, "Xuất sắc!", Toast.LENGTH_LONG).show();
            MainActivity.tvProgress.setTextColor(Color.RED);
            MainActivity.bt5050.setVisibility(View.VISIBLE);
            MainActivity.btRelatives.setVisibility(View.VISIBLE);
            MainActivity.btSpectator.setVisibility(View.VISIBLE);
            x = 1;
        }
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor2 = db.rawQuery(selectQuery, null);
        cursor2.moveToFirst();
        questionnumber = (int) (Math.random() * 10 + (x * 100));
        f();
    }


    private void f() {
        if (Integer.valueOf(cursor2.getString(2)) == questionnumber) {
            MainActivity.tvNumber.setText("" + x);
            MainActivity.tvQuestion.setText("Câu " + x + ": " + cursor2.getString(3));
            MainActivity.btA.setText("A. " + cursor2.getString(4));
            MainActivity.btB.setText("B. " + cursor2.getString(5));
            MainActivity.btC.setText("C. " + cursor2.getString(6));
            MainActivity.btD.setText("D. " + cursor2.getString(7));
            MainActivity.countDownTimer.start();
        } else {
            cursor2.moveToNext();
            if (cursor2.isAfterLast()) {
                x--;
                displayQuesstion();
            } else f();
        }
    }
}
