
package com.fentury.testapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Model implements Parcelable {

    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("descendants")
    @Expose
    private Integer descendants;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kids")
    @Expose
    private List<Integer> kids = new ArrayList<Integer>();
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Model{" +
                "by='" + by + '\'' +
                ", descendants=" + descendants +
                ", id=" + id +
                ", kids=" + kids +
                ", score=" + score +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.by);
        dest.writeValue(this.descendants);
        dest.writeValue(this.id);
        dest.writeList(this.kids);
        dest.writeValue(this.score);
        dest.writeValue(this.time);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public Model() {
    }

    protected Model(Parcel in) {
        this.by = in.readString();
        this.descendants = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.kids = new ArrayList<Integer>();
        in.readList(this.kids, Integer.class.getClassLoader());
        this.score = (Integer) in.readValue(Integer.class.getClassLoader());
        this.time = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}