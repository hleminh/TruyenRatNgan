package com.example.hoang.truyenratngan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hoang.truyenratngan.adapters.StoryAdapter;
import com.example.hoang.truyenratngan.databases.StoryDatabase;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.example.hoang.truyenratngan.fragments.StoryListFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);

        displayStartScreen();

    }

    private void displayStartScreen() {
        StoryListFragment storyListFragment = new StoryListFragment();
        changeScreen(storyListFragment, false);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void changeScreen(Fragment fragment, boolean addToBackStack) {
        //2: Start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        //3: Commit
        transaction.commit();
    }


}
