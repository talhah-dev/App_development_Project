package com.talha.app_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private DBHelper db;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new DBHelper(this);
        prefs = getSharedPreferences("session", MODE_PRIVATE);

        String oldEmail = prefs.getString("email", null);

        EditText etFullName = findViewById(R.id.etFullName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etRole = findViewById(R.id.etRole);
        EditText etBio = findViewById(R.id.etBio);

        // Pre-fill from DB if logged in
        if (oldEmail != null) {
            String name = db.getNameByEmail(oldEmail);
            if (name != null) etFullName.setText(name);
            etEmail.setText(oldEmail);
        }

        findViewById(R.id.btnBackEdit).setOnClickListener(v -> finish());
        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            if (oldEmail == null) {
                Toast.makeText(this, "Session expired. Please login again.", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            String newName = etFullName.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(newName)) {
                etFullName.setError("Enter name");
                etFullName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(newEmail)) {
                etEmail.setError("Enter email");
                etEmail.requestFocus();
                return;
            }

            // If user changed email, ensure it doesn't already exist
            if (!newEmail.equalsIgnoreCase(oldEmail) && db.isEmailExists(newEmail)) {
                etEmail.setError("Email already used");
                etEmail.requestFocus();
                return;
            }

            boolean updated = db.updateUserProfile(oldEmail, newName, newEmail);

            if (updated) {
                // Update session email (important)
                prefs.edit().putString("email", newEmail).apply();

                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
                finish(); // go back to profile screen
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
