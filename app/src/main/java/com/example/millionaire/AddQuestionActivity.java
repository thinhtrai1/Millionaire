package com.example.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddQuestionActivity extends AppCompatActivity {

    static EditText levelEdt, questionEdt, a, b, c, d, tru;
    static Button addButton, viewQuestionBtn, back;
    static TextView viewQuestionTv;
    QuestionDatabase questionDatabase = new QuestionDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        levelEdt = findViewById(R.id.level_add);
        questionEdt = findViewById(R.id.question_add);
        a = findViewById(R.id.a_add);
        b = findViewById(R.id.b_add);
        c = findViewById(R.id.c_add);
        d = findViewById(R.id.d_add);
        tru = findViewById(R.id.true_add);
        viewQuestionBtn = findViewById(R.id.view_btn);
        back = findViewById(R.id.back_btn);
        viewQuestionTv = findViewById(R.id.question_view);
        addButton = findViewById(R.id.add_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (levelEdt.getText().toString().equals("") || questionEdt.getText().toString().equals("")
                        || a.getText().toString().equals("") || b.getText().toString().equals("") || c.getText().toString().equals("")
                        || d.getText().toString().equals("") || tru.getText().toString().equals("")
                        || Integer.valueOf(levelEdt.getText().toString()) == 0
                        || (!tru.getText().toString().toLowerCase().equals("a") && !tru.getText().toString().toLowerCase().equals("b")
                        && !tru.getText().toString().toLowerCase().equals("c") && !tru.getText().toString().toLowerCase().equals("d"))) {
                    Toast.makeText(AddQuestionActivity.this, "Add question fail", Toast.LENGTH_SHORT).show();
                } else {
                    questionDatabase.addQuestion();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddQuestionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionDatabase.getAllQuestion();
                viewQuestionTv.setText(QuestionDatabase.text);
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(AddQuestionActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
