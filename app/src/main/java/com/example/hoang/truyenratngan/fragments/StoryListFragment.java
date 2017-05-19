package com.example.hoang.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hoang.truyenratngan.R;
import com.example.hoang.truyenratngan.StoryApplication;
import com.example.hoang.truyenratngan.MainActivity;
import com.example.hoang.truyenratngan.adapters.StoryAdapter;
import com.example.hoang.truyenratngan.databases.StoryDatabase;
import com.example.hoang.truyenratngan.databases.models.Story;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment {

    private List<Story> stories;
    private StoryAdapter storyAdapter;
    private ListView lvStory;

    public StoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
        setupUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        lvStory = (ListView) view.findViewById(R.id.lv_storylist);
        return view;
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
        storyAdapter = new StoryAdapter(stories);

        //2: Connect Adapter to ListView and Model
        lvStory.setAdapter(storyAdapter);

        //3: Add event listener
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("MainActivity", String.format("onItemClick: %s", position));
//                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
//                intent.putExtra("Story", stories.get(position));
//                startActivity(intent);
                //TODO: Change screen
                ((MainActivity) getActivity()).changeScreen(new StoryDetailFragment().setStory(stories.get(position)), true);
            }
        });

    }

}
