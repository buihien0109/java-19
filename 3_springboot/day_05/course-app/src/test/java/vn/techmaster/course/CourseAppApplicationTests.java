package vn.techmaster.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.course.model.Course;
import vn.techmaster.course.service.CourseService;

import java.util.List;

@SpringBootTest
class CourseAppApplicationTests {
    @Autowired
    private CourseService courseService;

    @Test
    void test_get_all_course() {
        List<Course> courseList = courseService.getAllCourse("online", null, null);
        courseList.forEach(System.out::println);
    }
}
