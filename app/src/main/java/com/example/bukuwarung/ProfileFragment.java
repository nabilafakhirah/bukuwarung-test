package com.example.bukuwarung;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProfileFragment extends Fragment {
    private boolean status;
    private EditText etName;
    private EditText etEmail;
    private EditText etGender;
    private FloatingActionButton editButton;

    private ProfileViewModel profileViewModel;

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

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(@Nullable List<Profile> profiles) {
                etName.setText(profiles.get(0).getName());
                etEmail.setText(profiles.get(0).getEmail());
                etGender.setText(profiles.get(0).getGender());
            }
        });

        setEnability();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status) {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String gender = etGender.getText().toString();

                    Profile profile = new Profile(name, email, gender);
                    profile.setId(1);

                    profileViewModel.update(profile);
                    status = false;
                } else {
                    status = true;
                }
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
