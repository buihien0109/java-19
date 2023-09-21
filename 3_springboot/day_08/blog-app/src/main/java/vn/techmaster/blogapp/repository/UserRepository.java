package vn.techmaster.blogapp.repository;

import vn.techmaster.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.blogapp.model.dto.UserInfo;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRoles_NameIn(List<String> roles);

    <T> T findByEmail(String email, Class<T> type);
}