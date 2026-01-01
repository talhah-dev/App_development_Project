package com.talha.app_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        data.add(new Course("Graphic Design", "By Syed Hasnain", 4.5f, 45, R.drawable.img1));
        data.add(new Course("Wireframing", "By Shoaib Hassan", 4.0f, 45, R.drawable.img2));
        data.add(new Course("Website Design", "By Dawar Hanif", 4.5f, 45, R.drawable.img1));
        data.add(new Course("Video Editing", "By Ammar Ijaz", 4.0f, 45, R.drawable.img2));

        rv.setAdapter(new CourseAdapter(data));

        return v;
    }
}
