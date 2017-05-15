package com.example.hoang.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.hoang.truyenratngan.R;
import com.example.hoang.truyenratngan.StoryApplication;
import com.example.hoang.truyenratngan.databases.StoryDatabase;
import com.example.hoang.truyenratngan.databases.models.Chapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {

    private TextView tvTitle;
    private WebView wvContent;
    private int chapterId;
    private Chapter chapter;

    public ChapterFragment() {
        // Required empty public constructor
    }

    public ChapterFragment setChapterId(int chapterId) {
        this.chapterId = chapterId;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);

        findReferences(view);
        getChapter();
        setupUI();

        return view;
    }

    private void getChapter() {
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        chapter = storyDatabase.getChapter(chapterId);
    }

    private void setupUI() {
        tvTitle.setText(chapter.getTitle());
        wvContent.loadData(chapter.getContent(), "text/html; charset=utf-8", "utf-8");
    }

    private void findReferences(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        wvContent = (WebView) view.findViewById(R.id.wv_content);
    }

}
