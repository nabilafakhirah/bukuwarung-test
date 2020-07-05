package com.example.bukuwarung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bukuwarung.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements UserAdapter.OnUsersListener {
    private RecyclerView rvUsers;
    private LinearLayoutManager layoutManager;
    private InterfaceApi interfaceApi;
    private UserAdapter userAdapter;
    private Context context;
    public List<Users> usersList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvUsers = getView().findViewById(R.id.rvUsers);
        layoutManager = new LinearLayoutManager(this.getActivity());
        interfaceApi = ApiClient.getRetrofitInstance().create(InterfaceApi.class);
        performSearch();
    }

    public UserAdapter.OnUsersListener getUserListener() {
        return this;
    }

    private void performSearch() {
        rvUsers.setLayoutManager(layoutManager);
        Call<ApiResponse> call = interfaceApi.getUsers();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    List<Users> users = response.body().getUsersList();
                    if (!users.isEmpty()) {
                        userAdapter = new UserAdapter(context, users, getUserListener());
                        rvUsers.setAdapter(userAdapter);
                    } else {
                        Toast toast = Toast.makeText(context,
                                "No matching results", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(context,
                            "No results", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void onUsersClick(int position) {
        Intent intent = new Intent(getActivity().getBaseContext(), UserActivity.class);
        intent.putExtra("id", Integer.toString(position));
        startActivity(intent);
    }
}