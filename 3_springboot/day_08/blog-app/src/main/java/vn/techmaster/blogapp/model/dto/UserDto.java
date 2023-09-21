package vn.techmaster.blogapp.model.dto;

import lombok.*;
import vn.techmaster.blogapp.entity.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private List<Role> roles;
}
