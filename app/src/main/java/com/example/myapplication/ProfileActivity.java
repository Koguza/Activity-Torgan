package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private User currentUser;
    private EditText etEditAge;
    private EditText etEditEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvProfileInfo = findViewById(R.id.tv_profile_info);
        etEditAge = findViewById(R.id.et_edit_age);
        etEditEmail = findViewById(R.id.et_edit_email);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);

        currentUser = getIntent().getParcelableExtra("USER");

        if (currentUser != null) {
            tvProfileInfo.setText("Имя: " + currentUser.getName());
            etEditAge.setText(String.valueOf(currentUser.getAge()));
            etEditEmail.setText(currentUser.getEmail());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    String ageStr = etEditAge.getText().toString();
                    int newAge = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
                    String newEmail = etEditEmail.getText().toString();

                    currentUser.setAge(newAge);
                    currentUser.setEmail(newEmail);

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("UPDATED_USER", currentUser);
                    setResult(RESULT_OK, returnIntent);
                }
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}