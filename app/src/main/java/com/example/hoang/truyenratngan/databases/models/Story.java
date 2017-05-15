package com.example.hoang.truyenratngan.databases.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Hoang on 4/18/2017.
 */
@Entity(
        createInDb = false,
        nameInDb = "tbl_story"
)
public class Story implements Serializable {
    private static final long serialVersionUID = 1L;
    @Property(nameInDb = "id")
    @Index(unique = true,
            name = "id")
    @Id
    private long id;
    @Property(nameInDb = "image")
    private String image;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "description")
    private String description;
    @Property(nameInDb = "is_favorite")
    private boolean isFavorite;
    @Property(nameInDb = "last_chapter_no")
    private int lastChapterNo;

    public Story(int id, String image, String title, String description, boolean isFavorite) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    @Generated(hash = 1867330935)
    public Story(long id, String image, String title, String description, boolean isFavorite,
            int lastChapterNo) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
        this.lastChapterNo = lastChapterNo;
    }

    @Generated(hash = 922655990)
    public Story() {
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public String toString() {
        return "Story{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getLastChapterNo() {
        return this.lastChapterNo;
    }

    public void setLastChapterNo(int lastChapterNo) {
        this.lastChapterNo = lastChapterNo;
    }

    public void setId(long id) {
        this.id = id;
    }
}
