package com.talha.app_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class signup extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DBHelper(this);

        MaterialButton btnBack = findViewById(R.id.btnBack);
        TextView tvRegister = findViewById(R.id.tvRegister);
        MaterialButton btnSignup = findViewById(R.id.btnSignup);

        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnBack.setOnClickListener(v ->
                startActivity(new Intent(signup.this, MainActivity.class))
        );

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(signup.this, login.class))
        );

        btnSignup.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etName.setError("Enter full name");
                etName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter email");
                etEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Enter password");
                etPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                etPassword.requestFocus();
                return;
            }

            if (!password.equals(confirm)) {
                etConfirmPassword.setError("Passwords do not match");
                etConfirmPassword.requestFocus();
                return;
            }

            // ✅ SQLite signup
            if (db.isEmailExists(email)) {
                Toast.makeText(signup.this, "Email already registered", Toast.LENGTH_LONG).show();
                return;
            }

            boolean inserted = db.insertUser(name, email, password);

                if (inserted) {
                    Toast.makeText(signup.this, "Account created! Please login.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signup.this, login.class));
                    finish();
                } else {
                Toast.makeText(signup.this, "Signup failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
