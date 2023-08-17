package vn.techmaster.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.techmaster.demo.model.Post;

@Component
public class JsonReader implements IFileReader{

    // @Override
    // public List<Post> readFile(String filePath){
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     TypeReference<List<Post>> typeReference = new TypeReference<List<Post>>() {};

    //     // Load the JSON file from the classpath
    //     try {
    //         ClassPathResource resource = new ClassPathResource(filePath);
    //         InputStream inputStream = resource.getInputStream();

    //         // Convert JSON to List of Person objects
    //         List<Post> personList = objectMapper.readValue(inputStream, typeReference);

    //         return personList;
    //     } catch (IOException e) {
    //         System.out.println(e.getMessage());
    //     }

    //     return null;
    // }

    @Override
    public List<Post> readFile(String filePath){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file using java.nio.file.Path
            Path jsonFilePath = Paths.get(filePath);
            byte[] jsonData = Files.readAllBytes(jsonFilePath);

            // Convert JSON to List of Person objects
            List<Post> postList = objectMapper.readValue(jsonData, new TypeReference<List<Post>>() {});

            return postList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
