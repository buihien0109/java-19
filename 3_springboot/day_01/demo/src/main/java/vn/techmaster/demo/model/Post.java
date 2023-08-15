package vn.techmaster.demo.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    private Integer id;
    private String title;
    private String author;
}
