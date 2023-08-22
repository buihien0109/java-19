package vn.techmaster.course.dto;

import lombok.*;
import vn.techmaster.course.model.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private List<String> topics;
    private String thumbnail;
    private User user;
}
