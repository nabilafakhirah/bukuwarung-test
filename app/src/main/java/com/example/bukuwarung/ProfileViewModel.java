package com.example.bukuwarung;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository repository;
    private LiveData<List<Profile>> profiles;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application);
        profiles = repository.getProfile();
    }

    public void insert(Profile profile) {
        repository.insert(profile);
    }

    public void update(Profile profile) {
        repository.update(profile);
    }

    public void delete(Profile profile) {
        repository.delete(profile);
    }

    public LiveData<List<Profile>> getProfiles() {
        return profiles;
    }
}
