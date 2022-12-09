package com.example.studentlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;

public class AddStudent extends AppCompatActivity {
    EditText name;
    EditText id;
    EditText address;
    EditText phoneNumber;
    CheckBox isChecked;

    Button cancelBtn;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        name = findViewById(R.id.name_input);
        id = findViewById(R.id.id_input);
        phoneNumber = findViewById(R.id.phone_input);
        address = findViewById(R.id.address_input);
        isChecked = findViewById(R.id.checkBox_input);

        cancelBtn = findViewById(R.id.cancel_btn);
        saveBtn = findViewById(R.id.save_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student newStudent = new Student(name.getText().toString(),
                                        id.getText().toString(),
                                        address.getText().toString(),
                                        phoneNumber.getText().toString(),
                                        isChecked.isChecked());
                Model.instance.addStudent(newStudent);
                finish();
            }
        });
    }
}
