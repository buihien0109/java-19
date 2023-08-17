package vn.techmaster.demo.utils;

import java.util.List;

import vn.techmaster.demo.model.Post;

public interface IFileReader {
    List<Post> readFile(String filePath);
}
