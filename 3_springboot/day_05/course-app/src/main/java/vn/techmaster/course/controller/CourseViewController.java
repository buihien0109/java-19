package vn.techmaster.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.techmaster.course.db.TopicDB;
import vn.techmaster.course.dto.CourseDto;
import vn.techmaster.course.service.CourseService;

import java.util.List;

@Controller
public class CourseViewController {
    private final CourseService courseService;

    public CourseViewController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/khoa-hoc")
    public String getCoursePage(Model model) {
        List<CourseDto> courseDtoList = courseService.getAllCourse(null, null, null);
        model.addAttribute("courseList", courseDtoList);
        model.addAttribute("topicList", TopicDB.topicList);

        return "course-list";
    }

    @GetMapping("/khoa-hoc/{id}")
    public String getCourseDetailPage(Model model, @PathVariable Integer id) {
        CourseDto courseDto = courseService.getCourseById(id);
        model.addAttribute("course", courseDto);

        return "detail";
    }
}
