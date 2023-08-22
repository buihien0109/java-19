package vn.techmaster.course.dao.impl;

import org.springframework.stereotype.Repository;
import vn.techmaster.course.dao.UserDAO;
import vn.techmaster.course.db.UserDB;
import vn.techmaster.course.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() {
        return UserDB.userList;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return UserDB.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
