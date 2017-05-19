package com.example.hoang.truyenratngan.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.truyenratngan.R;
import com.example.hoang.truyenratngan.StoryApplication;
import com.example.hoang.truyenratngan.databases.models.DaoMaster;
import com.example.hoang.truyenratngan.databases.models.DaoSession;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.example.hoang.truyenratngan.databases.models.StoryDao;
import com.example.hoang.truyenratngan.networks.ImageLoader;
import com.example.hoang.truyenratngan.utils.TextViewUtils;

import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * Created by Hoang on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {

    private DaoMaster.DevOpenHelper daoHelper;
    private DaoSession daoSession;
    private Database db;


    private List<Story> stories;

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
        daoHelper = StoryApplication.getInstance().getDaoHelper();
        db = daoHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1: Load item's data (model)
        final Story story = stories.get(position);

        //2: Create view if necessary (view)
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.item_story, parent, false);
            TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
            TextViewUtils.makeFit(tvDescription);
        }

        //3: Config & return
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
        ImageView ivStory = (ImageView) convertView.findViewById(R.id.iv_story_image);
        ImageView ivFavorite = (ImageView) convertView.findViewById(R.id.iv_favorite);

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (story.isFavorite()) {
                    v.setSelected(false);
                    story.setIsFavorite(false);
                    Story storyDao = daoSession.getStoryDao().queryBuilder().where(StoryDao.Properties.Id.eq(story.getId())).list().get(0);
                    storyDao.setIsFavorite(false);
                    daoSession.update(storyDao);
                }
                else if (!story.isFavorite()){
                    v.setSelected(true);
                    story.setIsFavorite(true);
                    Story storyDao = daoSession.getStoryDao().queryBuilder().where(StoryDao.Properties.Id.eq(story.getId())).list().get(0);
                    storyDao.setIsFavorite(true);
                    daoSession.update(storyDao);
                }
            }
        });

        tvTitle.setText(story.getTitle());
        TextViewUtils.makeFit(tvDescription);
        tvDescription.setText(story.getDescription());
        if (story.isFavorite()) {
            ivFavorite.setSelected(true);
        } else ivFavorite.setSelected(false);

        new ImageLoader().setImageView(ivStory).loadImage(story.getImage());

        int chapterCount = StoryApplication.getInstance().getStoryDatabase().getChapterCount(story);
        Log.d("StoryAdapter", String.format("%d", chapterCount));
        return convertView;
    }
}
