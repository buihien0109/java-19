package vn.techmaster.blogapp.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.blogapp.entity.Image;
import vn.techmaster.blogapp.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ImageResource {

    private final ImageService imageService;

    public ImageResource(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/admin/images")
    public ResponseEntity<?> getFilesOfCurrentUser() {
        return ResponseEntity.ok(imageService.getFilesOfCurrentUser());
    }

    @PostMapping("/admin/images")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(imageService.uploadFile(file));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image image = imageService.getFileById(id);
        // Code logic
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData()); // 200
    }

    @DeleteMapping("/admin/images/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        imageService.deleteFile(id);
        // Code logic
        return ResponseEntity.noContent().build(); // 204
    }
}
