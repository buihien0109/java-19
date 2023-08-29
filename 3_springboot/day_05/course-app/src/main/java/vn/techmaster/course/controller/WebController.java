package vn.techmaster.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.course.db.TopicDB;
import vn.techmaster.course.dto.CourseDto;
import vn.techmaster.course.service.WebService;

import java.util.List;

@Controller
public class WebController {
    private final WebService webService;

    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/khoa-hoc")
    public String getCoursePage(Model model) {
        List<CourseDto> courseDtoList = webService.getAllCourse(null, null, null);
        model.addAttribute("courseList", courseDtoList);
        model.addAttribute("topicList", TopicDB.topicList);

        return "web/course-list";
    }

    @GetMapping("/khoa-hoc/online")
    public String getCourseOnlinePage(Model model) {
        List<CourseDto> courseDtoList = webService.getAllCourse("online", null, null);
        model.addAttribute("courseList", courseDtoList);
        model.addAttribute("topicList", TopicDB.topicList);

        return "web/course-online-list";
    }

    @GetMapping("/khoa-hoc/onlab")
    public String getCourseOnlabPage(Model model) {
        List<CourseDto> courseDtoList = webService.getAllCourse("onlab", null, null);
        model.addAttribute("courseList", courseDtoList);
        model.addAttribute("topicList", TopicDB.topicList);

        return "web/course-onlab-list";
    }

    @GetMapping("/khoa-hoc/{id}")
    public String getCourseDetailPage(Model model, @PathVariable Integer id) {
        CourseDto courseDto = webService.getCourseById(id);
        model.addAttribute("course", courseDto);

        return "web/detail";
    }

    @GetMapping("/api/v1/courses")
    public ResponseEntity<List<CourseDto>> getAllCourse(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String topic) {
        List<CourseDto> courseDtoList = webService.getAllCourse(type, name, topic);
        return ResponseEntity.ok(courseDtoList);
    }

    @GetMapping("/api/v1/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer id) {
        CourseDto courseDto = webService.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }
}
