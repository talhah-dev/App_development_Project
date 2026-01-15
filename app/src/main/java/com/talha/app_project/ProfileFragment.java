package com.talha.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private DBHelper db;
    private SharedPreferences prefs;

    private TextView tvName;
    private TextView tvRole; // we will show email here (optional)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(requireContext());
        prefs = requireContext().getSharedPreferences("session", android.content.Context.MODE_PRIVATE);

        tvName = view.findViewById(R.id.tvName);
        tvRole = view.findViewById(R.id.tvRole);

        // Load user data on screen
        loadUserInfo();

        // Edit Profile
        View btnEditProfile = view.findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), EditProfileActivity.class))
            );
        }

        // My Courses
        View btnMyCourses = view.findViewById(R.id.btnMyCourses);
        if (btnMyCourses != null) {
            btnMyCourses.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), MyCoursesActivity.class))
            );
        }

        // Logout
        View btnLogout = view.findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                prefs.edit().clear().apply();

                Intent i = new Intent(requireContext(), login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh after coming back from EditProfileActivity
        loadUserInfo();
    }

    private void loadUserInfo() {
        if (prefs == null || db == null) return;

        String email = prefs.getString("email", null);

        if (email == null) {
            // No session → go to login
            Intent i = new Intent(requireContext(), login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return;
        }

        String name = db.getNameByEmail(email);

        if (tvName != null) {
            tvName.setText(name != null ? name : "User");
        }

        // Optional: show email in role line
        if (tvRole != null) {
            tvRole.setText(email);
        }
    }
}
