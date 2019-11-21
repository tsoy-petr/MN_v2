package com.android.hootr.my_androidpaginglibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hootr.my_androidpaginglibrary.model.User;
import com.android.hootr.my_androidpaginglibrary.ui.UserAdapter;
import com.android.hootr.my_androidpaginglibrary.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        final UserAdapter adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserViewModel itemViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        itemViewModel.userPagedList.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}

