package com.talha.app_project;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialToolbar topBarSettings = findViewById(R.id.topBarSettings);
        topBarSettings.setNavigationOnClickListener(v -> finish());

        MaterialButton btnManageAccount = findViewById(R.id.btnManageAccount);

        MaterialSwitch swNotifications = findViewById(R.id.swNotifications);
        MaterialSwitch swDarkMode = findViewById(R.id.swDarkMode);
        MaterialSwitch swWifiOnly = findViewById(R.id.swWifiOnly);

        MaterialButton btnChangePassword = findViewById(R.id.btnChangePassword);
        MaterialButton btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
        MaterialButton btnTerms = findViewById(R.id.btnTerms);

        MaterialButton btnHelpCenter = findViewById(R.id.btnHelpCenter);
        MaterialButton btnContactSupport = findViewById(R.id.btnContactSupport);
        MaterialButton btnAbout = findViewById(R.id.btnAbout);

        MaterialButton btnLogoutSettings = findViewById(R.id.btnLogoutSettings);
        TextView tvFooter = findViewById(R.id.tvFooter);

        btnManageAccount.setOnClickListener(v ->
                Toast.makeText(this, "Account screen (static for now)", Toast.LENGTH_SHORT).show()
        );

        swNotifications.setChecked(true);
        swWifiOnly.setChecked(true);

        swNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Notifications ON" : "Notifications OFF", Toast.LENGTH_SHORT).show()
        );

        swDarkMode.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Dark mode ON (static)" : "Dark mode OFF (static)", Toast.LENGTH_SHORT).show()
        );

        swWifiOnly.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Wi-Fi only ON" : "Wi-Fi only OFF", Toast.LENGTH_SHORT).show()
        );

        btnChangePassword.setOnClickListener(v ->
                Toast.makeText(this, "Change Password (static)", Toast.LENGTH_SHORT).show()
        );

        btnPrivacyPolicy.setOnClickListener(v ->
                Toast.makeText(this, "Privacy Policy (static)", Toast.LENGTH_SHORT).show()
        );

        btnTerms.setOnClickListener(v ->
                Toast.makeText(this, "Terms & Conditions (static)", Toast.LENGTH_SHORT).show()
        );

        btnHelpCenter.setOnClickListener(v ->
                Toast.makeText(this, "Help Center (static)", Toast.LENGTH_SHORT).show()
        );

        btnContactSupport.setOnClickListener(v ->
                Toast.makeText(this, "Contact Support (static)", Toast.LENGTH_SHORT).show()
        );

        btnAbout.setOnClickListener(v ->
                Toast.makeText(this, "About (static)", Toast.LENGTH_SHORT).show()
        );

        btnLogoutSettings.setOnClickListener(v ->
                Toast.makeText(this, "Logout (connect to your session if needed)", Toast.LENGTH_SHORT).show()
        );

        tvFooter.setOnClickListener(v ->
                Toast.makeText(this, "LMS App • Version 1.0.0", Toast.LENGTH_SHORT).show()
        );
    }
}
