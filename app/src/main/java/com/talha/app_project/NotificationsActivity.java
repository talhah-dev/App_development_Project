package com.talha.app_project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        MaterialToolbar topBarNotifications = findViewById(R.id.topBarNotifications);
        MaterialButton btnClearAll = findViewById(R.id.btnClearAll);

        topBarNotifications.setNavigationOnClickListener(v -> finish());

        btnClearAll.setOnClickListener(v ->
                Toast.makeText(this, "Cleared (static)", Toast.LENGTH_SHORT).show()
        );
    }
}
