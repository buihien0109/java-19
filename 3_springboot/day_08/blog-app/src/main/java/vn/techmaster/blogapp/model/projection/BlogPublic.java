package vn.techmaster.blogapp.model.projection;

import vn.techmaster.blogapp.entity.Blog;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPublic {
    Integer getId();

    String getTitle();

    String getSlug();

    String getDescription();

    String getContent();

    String getThumbnail();

    LocalDateTime getCreatedAt(); // dd/MM/yyyy

    LocalDateTime getUpdatedAt(); // dd/MM/yyyy

    LocalDateTime getPublishedAt(); // dd/MM/yyyy

    Boolean getStatus();

    List<CategoryPublic> getCategories();

    List<CommentPublic> getComments();

    UserPublic getUser();

    @RequiredArgsConstructor
    class BlogPublicImpl implements BlogPublic {
        private final Blog blog;

        @Override
        public Integer getId() {
            return this.blog.getId();
        }

        @Override
        public String getTitle() {
            return this.blog.getTitle();
        }

        @Override
        public String getSlug() {
            return this.blog.getSlug();
        }

        @Override
        public String getDescription() {
            return this.blog.getDescription();
        }

        @Override
        public String getContent() {
            return this.blog.getContent();
        }

        @Override
        public String getThumbnail() {
            return this.blog.getThumbnail();
        }

        @Override
        public LocalDateTime getCreatedAt() {
            return this.blog.getCreatedAt();
        }

        @Override
        public LocalDateTime getUpdatedAt() {
            return this.blog.getUpdatedAt();
        }

        @Override
        public LocalDateTime getPublishedAt() {
            return this.blog.getPublishedAt();
        }

        @Override
        public Boolean getStatus() {
            return this.blog.getStatus();
        }

        @Override
        public List<CategoryPublic> getCategories() {
            return this.blog.getCategories().stream()
                    .map(c -> CategoryPublic.of(c))
                    .toList();
        }

        @Override
        public List<CommentPublic> getComments() {
            return this.blog.getComments().stream()
                    .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                    .map(c -> CommentPublic.of(c))
                    .toList();
        }

        @Override
        public UserPublic getUser() {
            return UserPublic.of(this.blog.getUser());
        }

    }

    static BlogPublic of(Blog blog) {
        return new BlogPublicImpl(blog);
    }
}
