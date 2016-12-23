package com.android.architecture.practice.data.model;

import com.android.architecture.practice.data.db.DataCacheDatabase;
import com.android.architecture.practice.data.db.DbConstants;
import com.android.architecture.practice.data.db.converter.ConvertUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = DbConstants.Table.LATEST_NEWS, database = DataCacheDatabase.class)
public class LatestNews extends BaseModel {

    // 自增ID
    @Column(name = DbConstants.Column.ID)
    @PrimaryKey(autoincrement = true)
    private long id;

    @Column(name = DbConstants.Column.DATE)
    private String date;

    @Column(name = DbConstants.Column.STORIES, typeConverter = StoriesConverter.class)
    private List<StoriesEntity> stories;

    @Column(name = DbConstants.Column.TOP_STORIES, typeConverter = TopStoriesConverter.class)
    @JsonProperty("top_stories")
    private List<TopStoriesEntity> topStories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoriesEntity> topStories) {
        this.topStories = topStories;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StoriesEntity {
        private String title;
        @JsonProperty("ga_prefix")
        private String gaPrefix;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TopStoriesEntity {
        private String title;
        private String image;
        @JsonProperty("ga_prefix")
        private String gaPrefix;
        private int type;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "LatestNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", topStories=" + topStories +
                '}';
    }

    public static class StoriesConverter extends TypeConverter<String, List<StoriesEntity>> {

        @Override
        public String getDBValue(List<StoriesEntity> model) {
            return ConvertUtils.getDBValue(model);
        }

        @Override
        public List<StoriesEntity> getModelValue(String data) {
            return ConvertUtils.getModelListValue(data, StoriesEntity.class);
        }
    }

    public static class TopStoriesConverter extends TypeConverter<String, List<TopStoriesEntity>> {

        @Override
        public String getDBValue(List<TopStoriesEntity> model) {
            return ConvertUtils.getDBValue(model);
        }

        @Override
        public List<TopStoriesEntity> getModelValue(String data) {
            return ConvertUtils.getModelListValue(data, TopStoriesEntity.class);
        }
    }
}
