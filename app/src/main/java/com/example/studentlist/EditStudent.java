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
            id = extras.getString("student_id");
            name = extras.getString("student_name");
            address = extras.getString("student_address");
            phone = extras.getString("student_phone");
            checked = extras.getBoolean("student_checked");

            nameET.setText(name);
            idET.setText(id);
            phoneNumberET.setText(phone);
            addressET.setText(address);
            isCheckedCB.setChecked(checked);
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
                Model.instance.deleteStudent(id);
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student newStudent = new Student(nameET.getText().toString(),
                        idET.getText().toString(),
                        addressET.getText().toString(),
                        phoneNumberET.getText().toString(),
                        isCheckedCB.isChecked());
                Model.instance.editStudent(newStudent);
                finish();
            }
        });
    }
}