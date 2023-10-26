package vn.techmaster.blogapp.service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.techmaster.blogapp.exception.BadRequestException;
import vn.techmaster.blogapp.model.request.LoginRequest;

@Slf4j
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession httpSession;

    public AuthService(AuthenticationManager authenticationManager, HttpSession httpSession) {
        this.authenticationManager = authenticationManager;
        this.httpSession = httpSession;
    }

    public String login(LoginRequest request) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu đối tượng đã xác thực vào trong SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lưu vào trong session
            httpSession.setAttribute("MY_SESSION", authentication.getName()); // Lưu email -> session

            return "Đăng nhập thành công";
        } catch (Exception e) {
            log.info("Lỗi đăng nhập: " + e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
