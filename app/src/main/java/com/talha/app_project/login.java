package com.talha.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class login extends AppCompatActivity {

    private DBHelper db;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DBHelper(this);
        prefs = getSharedPreferences("session", MODE_PRIVATE);

        // ✅ Auto-login (if already logged in)
        if (prefs.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(login.this, DashboardActivity.class));
            finish();
            return;
        }

        MaterialButton btnBack = findViewById(R.id.btnBack);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);

        btnBack.setOnClickListener(v ->
                startActivity(new Intent(login.this, MainActivity.class))
        );

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(login.this, signup.class))
        );

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

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

            boolean ok = db.checkLogin(email, password);

            if (ok) {
                // ✅ Save session (keep logged in)
                prefs.edit()
                        .putBoolean("isLoggedIn", true)
                        .putString("email", email)
                        .apply();

                Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, DashboardActivity.class));
                finish();
            } else {
                Toast.makeText(login.this, "Invalid email or password", Toast.LENGTH_LONG).show();
            }
        });
    }
}
