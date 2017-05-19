package com.example.hoang.truyenratngan.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hoang.truyenratngan.StoryApplication;
import com.example.hoang.truyenratngan.databases.StoryDatabase;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.example.hoang.truyenratngan.fragments.ChapterFragment;

import java.util.List;

/**
 * Created by Hoang on 5/14/2017.
 */

public class ChapterAdapter extends FragmentStatePagerAdapter {
    private Story story;
    private StoryDatabase storyDatabase;
    private List<Integer> chapterIds;

    public ChapterAdapter(FragmentManager fm) {
        super(fm);
        storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    }

    public ChapterAdapter setStory(Story story) {
        this.story = story;
        this.chapterIds = storyDatabase.getChapterIds(story);
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        int chapterId = this.chapterIds.get(position);
        ChapterFragment chapterFragment = new ChapterFragment().setChapterId(chapterId);
        return chapterFragment;
    }

    @Override
    public int getCount() {
        return storyDatabase.getChapterCount(story);
    }
}
