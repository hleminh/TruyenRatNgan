package com.example.hoang.truyenratngan.databases.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Hoang on 5/14/2017.
 */

@Entity(
        createInDb = false,
        nameInDb = "tbl_chapter"
)
public class Chapter {
    @Property(nameInDb = "id")
    @Index(unique = true,
            name = "id")
    private int id;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "content")
    private String content;
    @Property(nameInDb = "novel_id")
    private int novelId;

    public Chapter(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Generated(hash = 126338013)
    public Chapter(int id, String title, String content, int novelId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.novelId = novelId;
    }

    @Generated(hash = 393170288)
    public Chapter() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNovelId() {
        return this.novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }
}
