package com.talha.app_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        View btnBack = findViewById(R.id.btnBackMyCourses);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        View btnOpenGD = findViewById(R.id.btnOpenGD);
        if (btnOpenGD != null) {
            btnOpenGD.setOnClickListener(v -> {
                // startActivity(new Intent(MyCoursesActivity.this, GraphicDesignCourseActivity.class));
            });
        }

        View btnOpenWF = findViewById(R.id.btnOpenWF);
        if (btnOpenWF != null) {
            btnOpenWF.setOnClickListener(v -> {
                // startActivity(new Intent(MyCoursesActivity.this, WireframingCourseActivity.class));
            });
        }

        View btnOpenWD = findViewById(R.id.btnOpenWD);
        if (btnOpenWD != null) {
            btnOpenWD.setOnClickListener(v -> {
                // startActivity(new Intent(MyCoursesActivity.this, WebsiteDesignCourseActivity.class));
            });
        }

        View btnOpenVE = findViewById(R.id.btnOpenVE);
        if (btnOpenVE != null) {
            btnOpenVE.setOnClickListener(v -> {
                // startActivity(new Intent(MyCoursesActivity.this, VideoEditingCourseActivity.class));
            });
        }
    }
}
