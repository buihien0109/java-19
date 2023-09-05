package vn.techmaster.course.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.course.db.TopicDB;
import vn.techmaster.course.model.Course;
import vn.techmaster.course.request.UpsertCourseRequest;
import vn.techmaster.course.service.CourseService;

import java.util.List;

@CrossOrigin
@Controller
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // API Trả về View
    @GetMapping("/admin/courses")
    public String getCourseListPage(Model model) {
        List<Course> courseList = courseService.getAllCourse();
        model.addAttribute("courseList", courseList);
        return "admin/course/index";
    }

    @GetMapping("/admin/courses/create")
    public String getCourseCreatePage(Model model) {
        model.addAttribute("topicList", TopicDB.topicList);
        return "admin/course/create";
    }

    @GetMapping("/admin/courses/{id}")
    public String getCourseDetailPage(@PathVariable Integer id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("topicList", TopicDB.topicList);
        return "admin/course/detail";
    }

    // API trả về JSON
    // 1. Lấy danh sách tất cả khóa học
    @GetMapping("/api/v1/admin/courses")
    public ResponseEntity<?> getAllCourse() {
        List<Course> courseList = courseService.getAllCourse();
        return ResponseEntity.ok(courseList);
    }

    // 2. Lấy chi tiết khóa học
    @GetMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    // 3. Tạo khóa học
    @PostMapping("/api/v1/admin/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody UpsertCourseRequest request) {
        Course course = courseService.createCourse(request);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    // 4. Cập nhật thông tin khóa học
    @PutMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @Valid @RequestBody UpsertCourseRequest request) {
        Course course = courseService.updateCourse(id, request);
        return ResponseEntity.ok(course);
    }

    // 5. Xóa khóa học
    @DeleteMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
