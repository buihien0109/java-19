package vn.techmaster.course.service;

import vn.techmaster.course.model.Course;
import vn.techmaster.course.request.UpsertCourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();

    Course getCourseById(Integer id);

    Course createCourse(UpsertCourseRequest request);

    Course updateCourse(Integer id, UpsertCourseRequest request);

    void deleteCourse(Integer id);
}
