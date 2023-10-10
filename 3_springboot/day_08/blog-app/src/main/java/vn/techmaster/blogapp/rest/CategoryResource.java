package vn.techmaster.blogapp.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryResource {
    // Tạo mới
    // yêu cầu không được trùng tên category. Nếu trùng -> throw BadRequestException

    // Cập nhập
    // yêu cầu không được trùng tên category. Nếu trùng -> throw BadRequestException

    // Xóa
    // Nếu category đang được sử dụng thì không được xóa. Nếu đang được sử dụng -> throw BadRequestException
}
