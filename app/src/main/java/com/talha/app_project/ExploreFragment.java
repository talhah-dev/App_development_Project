package com.talha.app_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class ExploreFragment extends Fragment {

    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = requireContext().getSharedPreferences("enroll_prefs", 0);

        MaterialButton btnStart1 = view.findViewById(R.id.btnStart1);
        MaterialButton btnUnenroll1 = view.findViewById(R.id.btnUnenroll1);

        MaterialButton btnStart2 = view.findViewById(R.id.btnStart2);
        MaterialButton btnUnenroll2 = view.findViewById(R.id.btnUnenroll2);

        setupCourseButtons("course_1", btnStart1, btnUnenroll1);
        setupCourseButtons("course_2", btnStart2, btnUnenroll2);
    }

    private void setupCourseButtons(String key, MaterialButton enrollBtn, MaterialButton unenrollBtn) {
        boolean enrolled = prefs.getBoolean(key, false);
        applyState(enrolled, enrollBtn, unenrollBtn);

        enrollBtn.setOnClickListener(v -> {
            prefs.edit().putBoolean(key, true).apply();
            applyState(true, enrollBtn, unenrollBtn);
        });

        unenrollBtn.setOnClickListener(v -> {
            prefs.edit().putBoolean(key, false).apply();
            applyState(false, enrollBtn, unenrollBtn);
        });
    }

    private void applyState(boolean enrolled, MaterialButton enrollBtn, MaterialButton unenrollBtn) {
        if (enrolled) {
            enrollBtn.setText("Enrolled");
            enrollBtn.setEnabled(false);
            enrollBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF16A34A));
            unenrollBtn.setEnabled(true);
            unenrollBtn.setAlpha(1f);
        } else {
            enrollBtn.setText("Enroll");
            enrollBtn.setEnabled(true);
            enrollBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF0B3B8C));
            unenrollBtn.setEnabled(false);
            unenrollBtn.setAlpha(0.5f);
        }
    }
}
