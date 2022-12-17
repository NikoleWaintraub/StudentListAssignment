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

public class StudentDetails extends AppCompatActivity {
    String id;
    String name;
    String address;
    String phone;
    Boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("student_id");
            name = extras.getString("student_name");
            address = extras.getString("student_address");
            phone = extras.getString("student_phone");
            checked = extras.getBoolean("student_checked");

            TextView nameView = findViewById(R.id.name_value);
            nameView.setText(name);
            TextView idView = findViewById(R.id.id_value);
            idView.setText(id);
            TextView phoneView = findViewById(R.id.phone_value);
            phoneView.setText(phone);
            TextView addressView = findViewById(R.id.address_value);
            addressView.setText(address);
            CheckBox checkBoxView = findViewById(R.id.checked_value);
            checkBoxView.setChecked(checked);
        }

        Button editStudentButton = findViewById(R.id.edit_button);

        Intent editStudentIntent = new Intent(getApplicationContext(), EditStudent.class);
        editStudentIntent.putExtra("student_id", id);
        editStudentIntent.putExtra("student_name", name);
        editStudentIntent.putExtra("student_address", address);
        editStudentIntent.putExtra("student_phone", phone);
        editStudentIntent.putExtra("student_checked", checked);
        editStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(editStudentIntent);
            }
        });

    }
}