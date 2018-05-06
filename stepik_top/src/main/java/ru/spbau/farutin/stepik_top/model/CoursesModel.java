package ru.spbau.farutin.stepik_top.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * CourseModel - POJO class to store one page with courses from stepik.
 */
public class CoursesModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;
    @SerializedName("enrollments")
    @Expose
    private List<Object> enrollments = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Object> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Object> enrollments) {
        this.enrollments = enrollments;
    }
}
