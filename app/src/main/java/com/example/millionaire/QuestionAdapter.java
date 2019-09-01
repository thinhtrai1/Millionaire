package com.example.millionaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private Context context;
    private int resource;
    private List<Question> questionList;

    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.questionList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.lv = convertView.findViewById(R.id.lv);
        viewHolder.ques = convertView.findViewById(R.id.questn);
        viewHolder.a = convertView.findViewById(R.id.a);
        viewHolder.b = convertView.findViewById(R.id.b);
        viewHolder.c = convertView.findViewById(R.id.c);
        viewHolder.d = convertView.findViewById(R.id.d);
        viewHolder.r = convertView.findViewById(R.id.right);

        Question question = questionList.get(position);
        viewHolder.lv.setText(""+question.getLv());
        viewHolder.ques.setText(""+question.getQuestion());
        viewHolder.a.setText(question.getA());
        viewHolder.b.setText(question.getB());
        viewHolder.c.setText(question.getC());
        viewHolder.d.setText(question.getD());
        viewHolder.r.setText(question.getR());
        return convertView;
    }

    public class ViewHolder {
        TextView lv, ques, a, b, c, d, r;
    }
}
