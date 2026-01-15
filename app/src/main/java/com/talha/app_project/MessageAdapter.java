package com.talha.app_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import com.google.android.material.card.MaterialCardView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.VH> {

    private final ArrayList<MessageModel> list;
    private final String myEmail;

    public MessageAdapter(ArrayList<MessageModel> list, String myEmail) {
        this.list = list;
        this.myEmail = myEmail;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        MessageModel m = list.get(position);

        h.tvSender.setText(m.sender);
        h.tvMsg.setText(m.text);

        boolean isMine = m.sender != null && m.sender.equals(myEmail);

        // Align bubble
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) h.bubbleCard.getLayoutParams();
        p.gravity = isMine ? android.view.Gravity.END : android.view.Gravity.START;
        h.bubbleCard.setLayoutParams(p);

        // Show sender label only for others (optional)
        h.tvSender.setVisibility(isMine ? View.GONE : View.VISIBLE);

        // Bubble style
        if (isMine) {
            h.bubbleCard.setCardBackgroundColor(h.itemView.getResources().getColor(R.color.primary_blue));
            h.tvMsg.setTextColor(0xFFFFFFFF);
        } else {
            h.bubbleCard.setCardBackgroundColor(0xFFF1F5F9);
            h.tvMsg.setTextColor(0xFF0F172A);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvSender, tvMsg;
        MaterialCardView bubbleCard;
        VH(@NonNull View itemView) {
            super(itemView);
            tvSender = itemView.findViewById(R.id.tvSender);
            tvMsg = itemView.findViewById(R.id.tvMsg);
            bubbleCard = itemView.findViewById(R.id.bubbleCard);
        }
    }
}
