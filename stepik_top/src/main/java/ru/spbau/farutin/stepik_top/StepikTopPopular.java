package ru.spbau.farutin.stepik_top;

import org.jetbrains.annotations.NotNull;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.spbau.farutin.stepik_top.model.Course;
import ru.spbau.farutin.stepik_top.model.CoursesModel;

import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.min;

/**
 * StepikTopPopular - console application,
 * which prints most popular courses on Stepik platform.
 */
public class StepikTopPopular {
    private final static String BASE_URL = "https://stepik.org/";

    /**
     * @param args arguments, first argument is expected to be a positive integer
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("\nUsage: gradlew run -Parg1=<N>");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);

            if (n <= 0) {
                System.err.println("\nFailed. Positive number expected!");
                return;
            }

            List<Course> courses = getCourses();
            List<Course> topCourses = getTopCourses(courses, n);
            printCourseInfo(System.out, topCourses);

        } catch (NumberFormatException e) {
            System.err.println("\nFailed. Wrong number format!");
        } catch (IOException e) {
            System.err.println("\nFailed. Error while reading from server.");
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            System.err.println("\nFailed. Error while building http client.");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Prints learners count and title for given courses.
     * @param dest output stream
     * @param courses courses to print
     */
    public static void printCourseInfo(@NotNull PrintStream dest,
                                       @NotNull List<Course> courses) {
        int i = 1;
        for (Course course : courses) {
            dest.printf("%d) %d %s\n", i++, course.getLearnersCount(), course.getTitle());
        }
    }

    /**
     * Gets all courses info from server using Retrofit.
     * @return all courses
     * @throws IOException if failed while reading from server
     * @throws NoSuchAlgorithmException if failed while building http client
     * @throws KeyManagementException if failed while building http client
     */
    public static @NotNull List<Course> getCourses()
            throws IOException, NoSuchAlgorithmException, KeyManagementException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(StepikOkHttpClient.getStepikOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StepikCoursesAPI api = retrofit.create(StepikCoursesAPI.class);

        List<Course> courses = new ArrayList<>();
        boolean hasNextPage = true;

        for (int page = 1; hasNextPage; page++) {
            Response<CoursesModel> response = api.getCoursesModel(page).execute();
            CoursesModel coursesModel = response.body();

            if (coursesModel != null) {
                List<Course> currentCourses = coursesModel.getCourses();

                if (currentCourses != null) {
                    courses.addAll(currentCourses);
                }

                hasNextPage = coursesModel.getMeta().isHasNext();
            } else {
                hasNextPage = false;
            }
        }

        return courses;
    }

    /**
     * Sorts courses descendingly by learners count
     * and takes first <code>n</code> of them.
     * @param courses courses to sort
     * @param n number of top courses
     * @return most popular courses
     */
    public static @NotNull List<Course> getTopCourses(@NotNull List<Course> courses, int n) {
        courses.sort(Comparator.comparing(Course::getLearnersCount).reversed());
        return courses.subList(0, min(n, courses.size()));
    }
}
