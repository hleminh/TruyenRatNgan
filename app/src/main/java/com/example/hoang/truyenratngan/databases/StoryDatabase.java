package com.example.hoang.truyenratngan.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hoang.truyenratngan.databases.models.Chapter;
import com.example.hoang.truyenratngan.databases.models.Story;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "story1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String STORY_ID = "id";
    private static final String STORY_TITLE = "title";
    private static final String STORY_IMAGE = "image";
    private static final String STORY_DESCRIPTION = "description";
    private static final String STORY_IS_FAVORITE = "is_favorite";
    private static final String STORY_AUTHOR = "author";
    private static final String STORY_GENRE = "genre";
    private static final String STORY_LAST_CHAPTER_NO = "last_chapter_no";

    private static final String CHAPTER_ID = "id";
    private static final String CHAPTER_TITLE = "title";
    private static final String CHAPTER_CONTENT = "content";


    private static final String[] STORY_ALL_COLUMNS = new String[]{
            STORY_ID,
            STORY_IMAGE,
            STORY_DESCRIPTION,
            STORY_TITLE,
            STORY_IS_FAVORITE
    };

    private static final String[] CHAPTER_ALL_COLUMNS = new String[]{
            CHAPTER_ID,
            CHAPTER_TITLE,
            CHAPTER_CONTENT
    };

    private static final String TAG = "DB";

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Story> loadAllStories() {
        List<Story> stories = new ArrayList<>();
        // Get readable database
        SQLiteDatabase db = getReadableDatabase();

        // Query -> cursor
        Cursor cursor = db.query("tbl_story", STORY_ALL_COLUMNS, null, null, null, null, null);

        // Go through rows
        while (cursor.moveToNext()) {
            int id = (cursor.getInt(cursor.getColumnIndex(STORY_ID)));
            String image = (cursor.getString(cursor.getColumnIndex(STORY_IMAGE)));
            String title = (cursor.getString(cursor.getColumnIndex(STORY_TITLE)));
            String description = (cursor.getString(cursor.getColumnIndex(STORY_DESCRIPTION)));
            boolean isFavorite = (cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE))) != 0;

            Story story = new Story(id, image, title, description, isFavorite);
            stories.add(story);
        }

        cursor.close();

        return stories;
    }

    public int getChapterCount(Story story) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM tbl_chapter WHERE novel_id = ?", new String[]{((Long) story.getId()).toString()});
        cursor.moveToFirst();

        int chapterCount = cursor.getInt(0);
        cursor.close();

        return chapterCount;
    }

    public List<Integer> getChapterIds(Story story) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM tbl_chapter WHERE novel_id = ?", new String[]{
                ((Long) story.getId()).toString()
        });

        List<Integer> chapterIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            chapterIds.add(cursor.getInt(0));
        }

        cursor.close();

        return chapterIds;
    }

    public Chapter getChapter(int chapterId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tbl_chapter", CHAPTER_ALL_COLUMNS, "id=?", new String[]{((Integer) chapterId).toString()}, null, null, null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(CHAPTER_TITLE));
        String content = cursor.getString(cursor.getColumnIndex(CHAPTER_CONTENT));

        cursor.close();

        return new Chapter(chapterId, title, content);
    }
}
