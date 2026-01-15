package com.talha.app_project;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    private DBHelper db;
    private SharedPreferences prefs;

    private String groupName;
    private String myEmail;

    private RecyclerView rv;
    private MessageAdapter adapter;
    private ArrayList<MessageModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        db = new DBHelper(this);
        prefs = getSharedPreferences("session", MODE_PRIVATE);

        groupName = getIntent().getStringExtra("group_name");
        if (groupName == null) groupName = "Chat";

        myEmail = prefs.getString("email", "me@app.com");

        TextView tvChatTitle = findViewById(R.id.tvChatTitle);
        tvChatTitle.setText(groupName);

        MaterialButton btnBackChat = findViewById(R.id.btnBackChat);
        btnBackChat.setOnClickListener(v -> finish());

        rv = findViewById(R.id.rvMessages);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(items, myEmail);
        rv.setAdapter(adapter);

        EditText etMessage = findViewById(R.id.etMessage);
        MaterialButton btnSend = findViewById(R.id.btnSend);

        loadMessages();

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (TextUtils.isEmpty(msg)) return;

            boolean ok = db.insertMessage(groupName, myEmail, msg);
            if (ok) {
                etMessage.setText("");
                loadMessages();
            } else {
                Toast.makeText(this, "Failed to send", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMessages() {
        items.clear();
        Cursor c = db.getMessages(groupName);
        while (c.moveToNext()) {
            String sender = c.getString(0);
            String message = c.getString(1);
            long time = c.getLong(2);
            items.add(new MessageModel(sender, message, time));
        }
        c.close();
        adapter.notifyDataSetChanged();

        if (items.size() > 0) rv.scrollToPosition(items.size() - 1);
    }
}
