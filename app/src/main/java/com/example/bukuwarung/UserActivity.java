package com.example.bukuwarung;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bukuwarung.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private InterfaceApi interfaceApi;
    private TextView tvName;
    private TextView tvEmail;
    private ImageView ivUser;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        interfaceApi = ApiClient.getRetrofitInstance().create(InterfaceApi.class);
        context = this.getBaseContext();
        setDisplay();
    }

    public void setDisplay() {
        Call<ApiResponse> call = interfaceApi.getUsers();
        String id = getIntent().getExtras().getString("id");
        final int id_no = Integer.parseInt(id);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        ivUser = findViewById(R.id.iv_profile);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    List<Users> users = response.body().getUsersList();
                    if (!users.isEmpty()) {
                        Users user = users.get(id_no);
                        String name = user.getFirstName() + " " + user.getLastName();
                        tvName.setText(name);
                        tvEmail.setText(user.getEmail());
                        Glide.with(context).load(user.getAvatar()).into(ivUser);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
