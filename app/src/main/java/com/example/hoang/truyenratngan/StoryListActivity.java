package com.example.hoang.truyenratngan;

import android.content.Intent;
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

import java.util.List;

public class StoryListActivity extends AppCompatActivity {

    private List<Story> stories;
    private StoryAdapter storyAdapter;
    private ListView lvStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);

        lvStory = (ListView) findViewById(R.id.lv_storylist);

        loadData();
        setupUI();
    }

    public void loadData() {
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        stories = storyDatabase.loadAllStories();

        for (Story el : stories) {
            System.out.println(el.toString());
        }
    }

    public void setupUI() {
        //1: Create Adapter
        storyAdapter = new StoryAdapter(this, stories);

        //2: Connect Adapter to ListView and Model
        lvStory.setAdapter(storyAdapter);

        //3: Add event listener
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("StoryListActivity", String.format("onItemClick: %s", position));
                Intent intent = new Intent(StoryListActivity.this, StoryDetailActivity.class);
                intent.putExtra("Story", stories.get(position));
                startActivity(intent);
            }
        });

    }
}
