package com.talha.app_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View cardGD = view.findViewById(R.id.cardGroupGraphicDesign);
        View cardWF = view.findViewById(R.id.cardGroupWireframing);
        View cardWD = view.findViewById(R.id.cardGroupWebsiteDesign);
        View cardVE = view.findViewById(R.id.cardGroupVideoEditing);

        if (cardGD != null) cardGD.setOnClickListener(v -> openChat("Graphic Design"));
        if (cardWF != null) cardWF.setOnClickListener(v -> openChat("Wireframing"));
        if (cardWD != null) cardWD.setOnClickListener(v -> openChat("Website Design"));
        if (cardVE != null) cardVE.setOnClickListener(v -> openChat("Video Editing"));
    }

    private void openChat(String groupName) {
        Intent i = new Intent(requireContext(), ChatRoomActivity.class);
        i.putExtra("group_name", groupName);
        startActivity(i);
    }
}
