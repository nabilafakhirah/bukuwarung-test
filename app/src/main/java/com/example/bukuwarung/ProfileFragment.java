package com.example.bukuwarung;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProfileFragment extends Fragment {
    private boolean status;
    private EditText etName;
    private EditText etEmail;
    private EditText etGender;
    private FloatingActionButton editButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        status = false;
        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        etName = getView().findViewById(R.id.et_name);
        etEmail = getView().findViewById(R.id.et_email);
        etGender = getView().findViewById(R.id.et_gender);
        editButton = getView().findViewById(R.id.bEdit);

        setEnability();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = !status;
                setEnability();
            }
        });

    }

    public void setEnability() {
        etName.setEnabled(status);
        etEmail.setEnabled(status);
        etGender.setEnabled(status);
    }

}
