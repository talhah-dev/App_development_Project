package com.talha.app_project;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GraphicDesignCourseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_design);

        View back = findViewById(R.id.btnBackGraphicDesign);
        if (back != null) back.setOnClickListener(v -> finish());
    }
}
