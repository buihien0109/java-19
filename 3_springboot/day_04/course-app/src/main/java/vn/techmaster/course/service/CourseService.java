package vn.techmaster.course.service;

import vn.techmaster.course.dto.CourseDto;
import vn.techmaster.course.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse(String type, String name, String topic);

    CourseDto getCourseById(Integer id);
}
