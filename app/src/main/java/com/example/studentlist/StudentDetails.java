package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;

public class StudentDetails extends AppCompatActivity {

    int currentStudentPosition;
    Student currentStudent;
    int currentAmountOfStudents;

    TextView nameView;
    TextView idView;
    TextView phoneView;
    TextView addressView;
    CheckBox checkBoxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentStudentPosition = extras.getInt("position");
            currentStudent = Model.instance.getAllStudents().get(currentStudentPosition);
            currentAmountOfStudents = Model.instance.getAllStudents().size();

            nameView = findViewById(R.id.name_value);
            nameView.setText(currentStudent.getName());
            idView = findViewById(R.id.id_value);
            idView.setText(currentStudent.getId());
            phoneView = findViewById(R.id.phone_value);
            phoneView.setText(currentStudent.getPhoneNumber());
            addressView = findViewById(R.id.address_value);
            addressView.setText(currentStudent.getAddress());
            checkBoxView = findViewById(R.id.checked_value);
            checkBoxView.setChecked(currentStudent.isFlag());
        }

        Button editStudentButton = findViewById(R.id.edit_button);

        Intent editStudentIntent = new Intent(getApplicationContext(), EditStudent.class);
        editStudentIntent.putExtra("position", currentStudentPosition);
        editStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(editStudentIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(currentAmountOfStudents > Model.instance.getAllStudents().size()) {
            finish();
        }

        this.currentStudent = Model.instance.getAllStudents().get(currentStudentPosition);
        this.nameView.setText(this.currentStudent.getName());
        this.idView.setText(this.currentStudent.getId());
        this.checkBoxView.setChecked(this.currentStudent.isFlag());
        this.addressView.setText(this.currentStudent.getAddress());
        this.phoneView.setText(this.currentStudent.getPhoneNumber());
    }
}