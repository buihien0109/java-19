package vn.techmaster.course.service;

import org.springframework.stereotype.Service;
import vn.techmaster.course.dao.CourseDAO;
import vn.techmaster.course.dao.UserDAO;
import vn.techmaster.course.dto.CourseDto;
import vn.techmaster.course.exception.ResouceNotFoundException;
import vn.techmaster.course.model.Course;
import vn.techmaster.course.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;
    private final UserDAO userDAO;

    public CourseServiceImpl(CourseDAO courseDAO, UserDAO userDAO) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Course> getAllCourse(String type, String name, String topic) {
        List<Course> courseList = courseDAO.findAll();
        return courseList.stream()
                .filter(course -> (type == null || course.getType().equals(type))
                        && (name == null || course.getName().toLowerCase().contains(name.toLowerCase()))
                        && (topic == null || course.getTopics().contains(topic)))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(Integer id) {
        Course course = courseDAO.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Not found course"));

        return mapToDto(course);
    }

    private CourseDto mapToDto(Course course) {
        User user = userDAO.findById(course.getUserId())
                .orElseThrow(() -> new ResouceNotFoundException("Not found user"));
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .type(course.getType())
                .topics(course.getTopics())
                .thumbnail(course.getThumbnail())
                .user(user)
                .build();
    }
}
