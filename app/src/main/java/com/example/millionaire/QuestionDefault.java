package com.example.millionaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class QuestionDefault extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "QuestionData";
    private Context context;

    public QuestionDefault(Context context) {
        super(context, "QuestionDB2", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_table = "CREATE TABLE " + TABLE_NAME + " (" +
                "id" + " integer primary key, " +
                "level" + " TEXT, " +
                "code" + " TEXT, " +
                "question" + " TEXT, " +
                "a" + " TEXT, " +
                "b" + " TEXT, " +
                "c" + " TEXT, " +
                "d" + " TEXT, " +
                "true" + " TEXT) ";
        sqLiteDatabase.execSQL(db_table);
        Toast.makeText(context, "Create data successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(context, "Upgrade table successfully", Toast.LENGTH_SHORT).show();
    }

    void addDefault() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("level", 1);
        values.put("code", 100);
        values.put("question", "9 + 17 = ?");
        values.put("a", 18);
        values.put("b", 16);
        values.put("c", 28);
        values.put("d", 26);
        values.put("true", "d");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 1);
        values.put("code", 101);
        values.put("question", "4 + 23 = ?");
        values.put("a", 21);
        values.put("b", 29);
        values.put("c", 27);
        values.put("d", 17);
        values.put("true", "c");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 1);
        values.put("code", 102);
        values.put("question", "18 + 8 = ?");
        values.put("a", 24);
        values.put("b", 26);
        values.put("c", 28);
        values.put("d", 36);
        values.put("true", "b");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 1);
        values.put("code", 103);
        values.put("question", "24 - 16 = ?");
        values.put("a", 14);
        values.put("b", 6);
        values.put("c", 8);
        values.put("d", 40);
        values.put("true", "c");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 2);
        values.put("code", 200);
        values.put("question", "27 + 23 = ?");
        values.put("a", 54);
        values.put("b", 40);
        values.put("c", 50);
        values.put("d", 60);
        values.put("true", "c");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 2);
        values.put("code", 201);
        values.put("question", "41 - 29 = ?");
        values.put("a", 11);
        values.put("b", 12);
        values.put("c", 20);
        values.put("d", 18);
        values.put("true", "b");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 2);
        values.put("code", 202);
        values.put("question", "8 x 6 = ?");
        values.put("a", 46);
        values.put("b", 44);
        values.put("c", 56);
        values.put("d", 48);
        values.put("true", "d");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 3);
        values.put("code", 300);
        values.put("question", "67 - 79 = ?");
        values.put("a", 26);
        values.put("b", 12);
        values.put("c", 52);
        values.put("d", -12);
        values.put("true", "d");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 3);
        values.put("code", 301);
        values.put("question", "86 x 8 = ?");
        values.put("a", 94);
        values.put("b", 524);
        values.put("c", 688);
        values.put("d", 528);
        values.put("true", "c");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 4);
        values.put("code", 400);
        values.put("question", "468 x 8 = ?");
        values.put("a", 3726);
        values.put("b", 3904);
        values.put("c", 3824);
        values.put("d", 3744);
        values.put("true", "d");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 1);
        values.put("code", 104);
        values.put("question", "Đất nước Việt Nam có hình chữ ?");
        values.put("a", "W");
        values.put("b", "O");
        values.put("c", "S");
        values.put("d", "X");
        values.put("true", "c");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 2);
        values.put("code", 203);
        values.put("question", "Có công mài sắt có ngày nên ... ?");
        values.put("a", "Chim");
        values.put("b", "Kim");
        values.put("c", "Phim");
        values.put("d", "Livestream");
        values.put("true", "b");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 2);
        values.put("code", 204);
        values.put("question", "Bỗng lòe chớp đỏ. Thôi rồi, ... ?");
        values.put("a", "Lượm ơi!");
        values.put("b", "Thịnh ơi!");
        values.put("c", "Mẹ ơi!");
        values.put("d", "Vợ ơi!");
        values.put("true", "a");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 4);
        values.put("code", 401);
        values.put("question", "Đâu là một nhân vật trong văn học Việt Nam ?");
        values.put("a", "Xuân tóc đỏ");
        values.put("b", "Thu tóc vàng");
        values.put("c", "Hạ tóc nâu");
        values.put("d", "Đông tóc bạc");
        values.put("true", "a");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 4);
        values.put("code", 402);
        values.put("question", "Hoàng Sa, Trường Sa là của ?");
        values.put("a", "Việt Nam");
        values.put("b", "Hoa Kỳ");
        values.put("c", "Nam Phi");
        values.put("d", "Lào");
        values.put("true", "a");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 4);
        values.put("code", 403);
        values.put("question", "1 vạn bằng bao nhiêu ?");
        values.put("a", "1 000");
        values.put("b", "10 000");
        values.put("c", "100 000");
        values.put("d", "1 000 000");
        values.put("true", "b");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 3);
        values.put("code", 302);
        values.put("question", "Đâu không phải tên một tỉnh của Việt Nam ?");
        values.put("a", "Quảng Nam");
        values.put("b", "Quảng Đông");
        values.put("c", "Quảng Bình");
        values.put("d", "Quảng Trị");
        values.put("true", "b");
        db.insert(TABLE_NAME, null, values);

        values.put("level", 3);
        values.put("code", 303);
        values.put("question", "Đâu không phải tên một hãng xe ô tô ?");
        values.put("a", "BMW");
        values.put("b", "Mecerdes-Benz");
        values.put("c", "TOYOTA");
        values.put("d", "LAZADA");
        values.put("true", "d");
        db.insert(TABLE_NAME, null, values);

        db.close();
    }
}

