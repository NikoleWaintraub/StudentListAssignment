package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;

public class EditStudent extends AppCompatActivity {

    private int currentStudentPosition;
    private Student currentStudent;

    String id;
    String name;
    String address;
    String phone;
    Boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        EditText nameET = findViewById(R.id.nameInput);
        EditText idET = findViewById(R.id.idInput);
        EditText phoneNumberET = findViewById(R.id.phoneInput);
        EditText addressET = findViewById(R.id.addressInput);
        CheckBox isCheckedCB = findViewById(R.id.checkBox);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.currentStudentPosition = extras.getInt("position");
            this.currentStudent = Model.instance.getAllStudents().get(currentStudentPosition);

            nameET.setText(currentStudent.getName());
            idET.setText(currentStudent.getId());
            phoneNumberET.setText(currentStudent.getPhoneNumber());
            addressET.setText(currentStudent.getAddress());
            isCheckedCB.setChecked(currentStudent.isFlag());
        }

        Button cancelBtn = findViewById(R.id.cancelButton);
        Button saveBtn = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance.getAllStudents().remove(currentStudent);
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentStudent.setName(nameET.getText().toString());
                currentStudent.setId(idET.getText().toString());
                currentStudent.setAddress(addressET.getText().toString());
                currentStudent.setPhoneNumber(phoneNumberET.getText().toString());
                currentStudent.setFlag(isCheckedCB.isChecked());
                finish();
            }
        });
    }
}