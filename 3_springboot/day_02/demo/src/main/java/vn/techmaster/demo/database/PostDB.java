package vn.techmaster.demo.database;

import vn.techmaster.demo.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostDB {
    public static List<Post> postList = new ArrayList<>(
            List.of(
                    new Post(1, "Post title 1", "Author 1"),
                    new Post(2, "Post title 2", "Author 2"),
                    new Post(3, "Post title 3", "Author 3")
            )
    );
}
