package vn.techmaster.course.service.impl;

import org.springframework.stereotype.Service;
import vn.techmaster.course.dao.UserDAO;
import vn.techmaster.course.model.User;
import vn.techmaster.course.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.findAll();
    }
}
