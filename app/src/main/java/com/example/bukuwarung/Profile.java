package com.example.bukuwarung;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "profile_table")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String email;

    private String gender;

    public Profile(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }
}
