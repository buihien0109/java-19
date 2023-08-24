package vn.techmaster.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.course.dto.CourseDto;
import vn.techmaster.course.service.CourseService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String topic) {
        List<CourseDto> courseDtoList = courseService.getAllCourse(type, name, topic);
        return ResponseEntity.ok(courseDtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer id) {
        CourseDto courseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }
}
