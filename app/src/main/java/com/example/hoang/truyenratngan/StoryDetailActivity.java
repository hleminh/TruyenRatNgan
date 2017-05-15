package com.example.hoang.truyenratngan;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hoang.truyenratngan.adapters.ChapterAdapter;
import com.example.hoang.truyenratngan.databases.models.DaoMaster;
import com.example.hoang.truyenratngan.databases.models.DaoSession;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.example.hoang.truyenratngan.databases.models.StoryDao;
import com.example.hoang.truyenratngan.pagetransformers.MyPageTransformer;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class StoryDetailActivity extends AppCompatActivity {
    private ViewPager vpChapter;
    private Story story;
    private DaoMaster.DevOpenHelper daoHelper;
    private DaoSession daoSession;
    private Database db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        daoHelper = StoryApplication.getInstance().getDaoHelper();
        db = daoHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        vpChapter = (ViewPager) findViewById(R.id.vp_chapter);
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

        getStory();
        setupUI();

        vpChapter.setPageTransformer(true, new MyPageTransformer());
    }

    private void setupUI() {
        vpChapter.setAdapter(new ChapterAdapter(this.getSupportFragmentManager()).setStory(this.story));
        Story storyDao = daoSession.getStoryDao().queryBuilder().where(StoryDao.Properties.Id.eq(story.getId())).list().get(0);
        int lastChapterNo = storyDao.getLastChapterNo();
        if (lastChapterNo != -1) {
            Log.d("StoryDetailActivity", String.format("Last chapter no: %d", lastChapterNo));
            vpChapter.setCurrentItem(lastChapterNo);
        }
        daoSession.update(storyDao);
    }

    private void getStory() {
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("Story");
        Log.d("StoryDetailActivity", story.toString());
    }
}
