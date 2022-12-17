package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("student_id");
            String name = extras.getString("student_name");
            String address = extras.getString("student_address");
            String phone = extras.getString("student_phone");
            Boolean checked = extras.getBoolean("student_checked");

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
    }
}