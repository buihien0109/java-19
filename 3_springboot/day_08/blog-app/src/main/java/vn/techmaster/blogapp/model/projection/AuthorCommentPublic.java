package vn.techmaster.blogapp.model.projection;

import vn.techmaster.blogapp.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;

public interface AuthorCommentPublic {
    Integer getId();

    String getName();

    String getAvatar();

    @RequiredArgsConstructor
    class AuthorCommentPublicImpl implements AuthorCommentPublic {
        @JsonIgnore
        public final User user;

        @Override
        public Integer getId() {
            return this.user.getId();
        }

        @Override
        public String getName() {
            return this.user.getName();
        }

        @Override
        public String getAvatar() {
            return this.user.getAvatar();
        }
    }

    static AuthorCommentPublic of(User user) {
        return new AuthorCommentPublicImpl(user);
    }
}
