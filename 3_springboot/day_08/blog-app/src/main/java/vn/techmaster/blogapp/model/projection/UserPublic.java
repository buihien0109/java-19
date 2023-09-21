package vn.techmaster.blogapp.model.projection;

import vn.techmaster.blogapp.entity.Role;
import vn.techmaster.blogapp.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface UserPublic {
    Integer getId();

    String getName();

    String getEmail();

    String getAvatar();

    List<Role> getRoles();

    @RequiredArgsConstructor
    class UserPublicImpl implements UserPublic {
        private final User user;

        @Override
        public Integer getId() {
            return this.user.getId();
        }

        @Override
        public String getName() {
            return this.user.getName();
        }

        @Override
        public String getEmail() {
            return this.user.getEmail();
        }

        @Override
        public String getAvatar() {
            return this.user.getAvatar();
        }

        @Override
        public List<Role> getRoles() {
            return this.user.getRoles();
        }
    }

    static UserPublic of(User user) {
        return new UserPublicImpl(user);
    }
}
