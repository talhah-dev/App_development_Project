package com.talha.app_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rv = v.findViewById(R.id.rvCourses);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<Course> data = new ArrayList<>();
        data.add(new Course("Graphic Design", "By Syed Hasnain", 4.5f, 45, R.drawable.graphic));
        data.add(new Course("Website Design", "By Dawar Hanif", 4.5f, 45, R.drawable.web));
        data.add(new Course("Wireframing", "By Shoaib Hassan", 4.0f, 45, R.drawable.wire));
        data.add(new Course("Video Editing", "By Ammar Ijaz", 4.0f, 45, R.drawable.video));

        ImageView ivSettings = v.findViewById(R.id.ivSettings);
        ivSettings.setOnClickListener(clickView ->
                startActivity(new Intent(requireContext(), SettingsActivity.class))
        );

        ImageView ivBell = v.findViewById(R.id.ivBell);
        ivBell.setOnClickListener(clickView ->
                startActivity(new android.content.Intent(requireContext(), NotificationsActivity.class))
        );

        rv.setAdapter(new CourseAdapter(data, course -> {

            Intent i;

            if (course.title.equals("Graphic Design")) {
                i = new Intent(requireContext(), GraphicDesignCourseActivity.class);

            } else if (course.title.equals("Wireframing")) {
                i = new Intent(requireContext(), WireframingCourseActivity.class);

            } else if (course.title.equals("Website Design")) {
                i = new Intent(requireContext(), WebsiteDesignCourseActivity.class);

            } else if (course.title.equals("Video Editing")) {
                i = new Intent(requireContext(), VideoEditingCourseActivity.class);

            } else {
                // fallback
                i = new Intent(requireContext(), MyCoursesActivity.class);
            }

            startActivity(i);
        }));


        return v;
    }
}
