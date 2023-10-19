package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.TokenConfirm;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TokenConfirmRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenConfirmRepository tokenConfirmRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final HttpSession httpSession;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, TokenConfirmRepository tokenConfirmRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, HttpSession httpSession,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tokenConfirmRepository = tokenConfirmRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.httpSession = httpSession;
        this.roleRepository = roleRepository;
    }

    public String register(RegisterRequest request) {
        // check email exist
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại");
        }

        // get role user
        Role roleUser = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role"));

        // create user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsEnabled(false);
        user.setRoles(List.of(roleUser));
        userRepository.save(user);

        // create token
        TokenConfirm tokenConfirm = new TokenConfirm();
        tokenConfirm.setToken(UUID.randomUUID().toString());
        tokenConfirm.setCreatedAt(LocalDateTime.now());
        tokenConfirm.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        tokenConfirm.setUser(user);
        tokenConfirmRepository.save(tokenConfirm);

        return "http://localhost:8080/api/v1/auth/confirm?token=" + tokenConfirm.getToken();
    }

    public String confirm(String token) {
        // Token có tồn tại không
        TokenConfirm tokenConfirm = tokenConfirmRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Không tồn tại token"));

        // Token đã được xác nhận chưa (nếu trường confirmedAt = null là chưa xác nhận)
        if (tokenConfirm.getConfirmedAt() != null) {
            throw new BadRequestException("Token đã được xác nhận");
        }

        // Token đã hết hạn hay chưa
        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token đã hết hạn");
        }

        // set lại trường confirmedAt = thời điểm thực hiện
        tokenConfirm.setConfirmedAt(LocalDateTime.now());

        // set lại trường isEnabled của user = true
        tokenConfirm.getUser().setIsEnabled(true);

        // lưu lại tokenConfirm và user
        tokenConfirmRepository.save(tokenConfirm);
        userRepository.save(tokenConfirm.getUser());

        return "Xác nhận thành công";
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
            throw new BadRequestException(e.getMessage());
        }
    }
}
