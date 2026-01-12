package com.talha.app_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }
}

//package com.talha.app_project;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.google.android.material.button.MaterialButton;
//
//public class ExploreFragment extends Fragment {
//
//    private static final String PREFS = "enroll_prefs";
//    private static final String KEY_COURSE_1 = "enrolled_UI_Design_Essentials";
//    private static final String KEY_COURSE_2 = "enrolled_Android_XML_Masterclass";
//
//    private MaterialButton btnStart1, btnStart2;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_explore, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        btnStart1 = view.findViewById(R.id.btnStart1);
//        btnStart2 = view.findViewById(R.id.btnStart2);
//
//        SharedPreferences sp = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
//
//        boolean enrolled1 = sp.getBoolean(KEY_COURSE_1, false);
//        boolean enrolled2 = sp.getBoolean(KEY_COURSE_2, false);
//
//        applyEnrollUi(btnStart1, enrolled1);
//        applyEnrollUi(btnStart2, enrolled2);
//
//        btnStart1.setOnClickListener(v -> {
//            sp.edit().putBoolean(KEY_COURSE_1, true).apply();
//            applyEnrollUi(btnStart1, true);
//        });
//
//        btnStart2.setOnClickListener(v -> {
//            sp.edit().putBoolean(KEY_COURSE_2, true).apply();
//            applyEnrollUi(btnStart2, true);
//        });
//    }
//
//    private void applyEnrollUi(MaterialButton button, boolean enrolled) {
//        if (enrolled) {
//            button.setText("Enrolled");
//            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#16A34A")));
//            button.setTextColor(Color.WHITE);
//        } else {
//            button.setText("Enroll");
//            button.setBackgroundTintList(ColorStateList.valueOf(requireContext().getResources().getColor(R.color.primary_blue)));
//            button.setTextColor(Color.WHITE);
//        }
//    }
//}
