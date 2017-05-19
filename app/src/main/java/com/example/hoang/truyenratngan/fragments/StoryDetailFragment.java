package com.example.hoang.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoang.truyenratngan.R;
import com.example.hoang.truyenratngan.StoryApplication;
import com.example.hoang.truyenratngan.adapters.ChapterAdapter;
import com.example.hoang.truyenratngan.databases.models.DaoMaster;
import com.example.hoang.truyenratngan.databases.models.DaoSession;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.example.hoang.truyenratngan.databases.models.StoryDao;

import org.greenrobot.greendao.database.Database;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment extends Fragment {

    private ViewPager vpChapter;
    private Story story;
    private DaoMaster.DevOpenHelper daoHelper;
    private DaoSession daoSession;
    private Database db;

    public StoryDetailFragment() {
        // Required empty public constructor
    }

    public StoryDetailFragment setStory(Story story) {
        this.story = story;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_story_detail, container, false);
        vpChapter = (ViewPager)view.findViewById(R.id.vp_chapter);
        daoHelper = StoryApplication.getInstance().getDaoHelper();
        db = daoHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();


        vpChapter.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Story storyDao = daoSession.getStoryDao().queryBuilder().where(StoryDao.Properties.Id.eq(story.getId())).list().get(0);
                storyDao.setLastChapterNo(position);
                daoSession.update(storyDao);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //        vpChapter.setPageTransformer(true, new MyPageTransformer());
        setupUI();
        return view;
    }

    private void setupUI() {
        vpChapter.setAdapter(new ChapterAdapter(this.getFragmentManager()).setStory(this.story));
        Story storyDao = daoSession.getStoryDao().queryBuilder().where(StoryDao.Properties.Id.eq(story.getId())).list().get(0);
        int lastChapterNo = storyDao.getLastChapterNo();
        if (lastChapterNo != -1) {
            Log.d("StoryDetailActivity", String.format("Last chapter no: %d", lastChapterNo));
            vpChapter.setCurrentItem(lastChapterNo);
        }
        daoSession.update(storyDao);
    }


}
