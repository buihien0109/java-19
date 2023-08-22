package vn.techmaster.course.dao;

import vn.techmaster.course.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();

    Optional<User> findById(Integer id);
}
