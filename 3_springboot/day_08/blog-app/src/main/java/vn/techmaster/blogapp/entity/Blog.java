package vn.techmaster.blogapp.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.techmaster.blogapp.model.dto.CategoryDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "CategoryList",
                classes = @ConstructorResult(
                        targetClass = CategoryDto.class,
                        columns = {
                                @ColumnResult(name = "id", type=Integer.class),
                                @ColumnResult(name = "name", type=String.class),
                                @ColumnResult(name = "used", type=Long.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getAllCategoryDtoNQ",
        resultSetMapping = "CategoryList",
        query = """
            SELECT c.id, c.name, COUNT(b.id) AS used
            FROM category c
            JOIN blog_category bc ON c.id = bc.category_id
            JOIN blog b ON bc.blog_id = b.id
            WHERE b.status = true
            GROUP BY c.id, c.name
""")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "published_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime publishedAt;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "blog_category",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if(status) {
            publishedAt = createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        if(status) {
            publishedAt = updatedAt;
        }
    }
}

