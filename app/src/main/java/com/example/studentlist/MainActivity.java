package com.example.studentlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = Model.instance.getAllStudents();

        RecyclerView list = findViewById(R.id.student_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new StudentAdapter());
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView studentId;
        CheckBox studentCheckBox;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById((R.id.studentListRow_name));
            studentId = itemView.findViewById(R.id.studentListRow_id);
            studentCheckBox = itemView.findViewById(R.id.StudentListRow_checkbox);
            studentCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) studentCheckBox.getTag();
                    Student student = data.get(position);
                    student.setFlag(studentCheckBox.isChecked());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("TAG","row clicked:" + position);
                }
            });
        }

        public void bind(Student student, int position) {
            studentName.setText(student.getName());
            studentId.setText(student.getId());
            studentCheckBox.setChecked(student.isFlag());
            studentCheckBox.setTag(position);

        }
    }

    class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = data.get(position);
            holder.bind(student, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}