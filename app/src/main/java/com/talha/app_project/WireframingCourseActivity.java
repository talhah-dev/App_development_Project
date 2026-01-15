package com.talha.app_project;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WireframingCourseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframing_course);

        View back = findViewById(R.id.btnBackWireframing);
        if (back != null) back.setOnClickListener(v -> finish());
    }
}
