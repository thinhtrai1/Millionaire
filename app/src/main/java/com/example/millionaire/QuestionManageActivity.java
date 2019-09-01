package com.example.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class QuestionManageActivity extends AppCompatActivity {

    EditText edtLevel, edtQuestion, edtA, edtB, edtC, edtD, edtTrue;
    Button btnAdd, btnDelete, btnUpdate;
    ListView lvQuestion;
    List<Question> questionList;
    QuestionAdapter questionAdapter;
    QuestionDatabase questionDatabase = new QuestionDatabase(this);
    String questionClick;
    boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        edtLevel = findViewById(R.id.level_add);
        edtQuestion = findViewById(R.id.question_add);
        edtA = findViewById(R.id.a_add);
        edtB = findViewById(R.id.b_add);
        edtC = findViewById(R.id.c_add);
        edtD = findViewById(R.id.d_add);
        edtTrue = findViewById(R.id.true_add);
        btnAdd = findViewById(R.id.add_btn);
        btnDelete = findViewById(R.id.delete_btn);
        btnUpdate = findViewById(R.id.update_btn);
        lvQuestion = findViewById(R.id.lv_question);

        questionList = questionDatabase.getAllQuestion();
        questionAdapter = new QuestionAdapter(this, R.layout.question_item, questionList);
        lvQuestion.setAdapter(questionAdapter);
        lvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question question = questionList.get(i);
                edtLevel.setText("" + question.getLv());
                edtQuestion.setText(question.getQuestion());
                edtA.setText(question.getA());
                edtB.setText(question.getB());
                edtC.setText(question.getC());
                edtD.setText(question.getD());
                edtTrue.setText(question.getR());
                questionClick = question.getQuestion();
                isClick = true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtLevel.getText().toString().equals("") || edtQuestion.getText().toString().equals("")
                        || edtA.getText().toString().equals("") || edtB.getText().toString().equals("") || edtC.getText().toString().equals("")
                        || edtD.getText().toString().equals("") || edtTrue.getText().toString().equals("")
                        || Integer.valueOf(edtLevel.getText().toString()) == 0
                        || (!edtTrue.getText().toString().toLowerCase().equals("a") && !edtTrue.getText().toString().toLowerCase().equals("b")
                        && !edtTrue.getText().toString().toLowerCase().equals("c") && !edtTrue.getText().toString().toLowerCase().equals("d"))) {
                    Toast.makeText(QuestionManageActivity.this, "Add failed", Toast.LENGTH_SHORT).show();
                } else {
                    Question question = createQuestion();
                    questionDatabase.addQuestion(question);
                    questionList.clear();
                    questionList.addAll(questionDatabase.getAllQuestion());
                    questionAdapter.notifyDataSetChanged();
                    lvQuestion.setAdapter(questionAdapter);
                    Toast.makeText(QuestionManageActivity.this, "Add question successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    questionDatabase.deleteStudent(edtQuestion.getText().toString());
                    questionList.clear();
                    questionList.addAll(questionDatabase.getAllQuestion());
                    questionAdapter.notifyDataSetChanged();
                    lvQuestion.setAdapter(questionAdapter);
                    Toast.makeText(QuestionManageActivity.this, "Delete question successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(QuestionManageActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionClick == null)
                    Toast.makeText(QuestionManageActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                else {
                    questionDatabase.deleteStudent(questionClick);
                    Question question = createQuestion();
                    questionDatabase.addQuestion(question);
                    questionList.clear();
                    questionList.addAll(questionDatabase.getAllQuestion());
                    questionAdapter.notifyDataSetChanged();
                    lvQuestion.setAdapter(questionAdapter);
                    Toast.makeText(QuestionManageActivity.this, "Update question successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Question createQuestion() {
        int lv = Integer.valueOf(edtLevel.getText().toString());
        String ques = edtQuestion.getText().toString();
        String a = edtA.getText().toString();
        String b = edtB.getText().toString();
        String c = edtC.getText().toString();
        String d = edtD.getText().toString();
        String r = edtTrue.getText().toString();
        Question question = new Question(lv, ques, a, b, c, d, r);
        return question;
    }

    public void onBackPressed() {
        Intent intent = new Intent(QuestionManageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
