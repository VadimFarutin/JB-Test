package ru.spbau.farutin.stepik_top.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Meta - POJO class to store meta information about page with courses.
 */
public class Meta {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("has_next")
    @Expose
    private boolean hasNext;
    @SerializedName("has_previous")
    @Expose
    private boolean hasPrevious;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
