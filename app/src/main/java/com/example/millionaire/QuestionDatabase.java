package com.example.millionaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

public class QuestionDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "QuestionData";
    private Context context;
    Cursor cursor2;
    int x, questionnumber;
    static String text;

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

    void addQuestion() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int x = Integer.valueOf(AddQuestionActivity.levelEdt.getText().toString()) * 100;
        int y = 0;
        while (!cursor.isAfterLast()) {
            if (Integer.valueOf(cursor.getString(2)) > x && Integer.valueOf(cursor.getString(2)) > y && Integer.valueOf(cursor.getString(2)) < x + 99) {
                y = Integer.valueOf(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        ContentValues values = new ContentValues();
        values.put("level", Integer.valueOf(AddQuestionActivity.levelEdt.getText().toString()));
        values.put("code", y + 1);
        values.put("question", AddQuestionActivity.questionEdt.getText().toString());
        values.put("a", AddQuestionActivity.a.getText().toString());
        values.put("b", AddQuestionActivity.b.getText().toString());
        values.put("c", AddQuestionActivity.c.getText().toString());
        values.put("d", AddQuestionActivity.d.getText().toString());
        values.put("true", AddQuestionActivity.tru.getText().toString().toLowerCase());

        db.insert(TABLE_NAME, null, values);
        Toast.makeText(context, "Add question successfylly", Toast.LENGTH_SHORT).show();
        cursor.close();
        db.close();
    }

    void getAllQuestion() {
        text = "";
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            text = text + "➤" +
                    cursor.getString(1) + "." +       //level
                    cursor.getString(2) + " - " +        //code
                    cursor.getString(3) + "? A." +          //question
                    cursor.getString(4) + " B." +           //A
                    cursor.getString(5) + " C." +           //B
                    cursor.getString(6) + " D." +           //C
                    cursor.getString(7) + "  [" +          //D
                    cursor.getString(8).toUpperCase() + "]\n";             //True
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
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
