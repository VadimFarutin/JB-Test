package ru.spbau.farutin.stepik_top;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.spbau.farutin.stepik_top.model.CoursesModel;

/**
 * StepikCoursesAPI - interface to get courses from stepik.org.
 */
public interface StepikCoursesAPI {
    /**
     * Gets courses listed on the given page.
     * @param page number of page with courses
     * @return courses from the given page stored in CoursesModel
     */
    @GET("api/courses")
    Call<CoursesModel> getCoursesModel(@Query("page") int page);
}
