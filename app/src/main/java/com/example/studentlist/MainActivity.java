package com.example.studentlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> data;
    FloatingActionButton addStudentBtn;

    public interface OnStudentClickListener {
        void onStudentClick(int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addStudentBtn = findViewById(R.id.add_student_btn);

        data = Model.instance.getAllStudents();
        Intent addIntent = new Intent(getApplicationContext(), AddStudent.class);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addIntent);
            }
        });

        RecyclerView list = findViewById(R.id.student_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter adapter = new StudentAdapter();
        list.setAdapter(adapter);

        adapter.setOnStudentCLickListener(new OnStudentClickListener() {
            @Override
            public void onStudentClick(int position) {
                Log.d("TAG","row clicked:" + position);
            }
        });
    }


    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView studentId;
        CheckBox studentCheckBox;

        public StudentViewHolder(@NonNull View itemView, OnStudentClickListener listener) {
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
                    listener.onStudentClick(position);
                    Intent detailsIntent = new Intent(getApplicationContext(), StudentDetails.class);
                    detailsIntent.putExtra("student_id", data.get(position).getId());
                    detailsIntent.putExtra("student_name", data.get(position).getName());
                    detailsIntent.putExtra("student_address", data.get(position).getAddress());
                    detailsIntent.putExtra("student_phone", data.get(position).getPhoneNumber());
                    detailsIntent.putExtra("student_checked", data.get(position).isFlag());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(detailsIntent);
                        }
                    });
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
        OnStudentClickListener listener;
        void setOnStudentCLickListener(OnStudentClickListener listenerParam){
            this.listener = listenerParam;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
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