package dajava.dacs.service;


import dajava.dacs.model.Role;
import dajava.dacs.model.User;
import dajava.dacs.repository.IRoleRepository;
import dajava.dacs.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = Logger.getLogger(CustomOAuth2UserService.class.getName());

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Lấy thông tin người dùng từ OAuth2User
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        logger.info("OAuth2 User email: " + email);
        logger.info("OAuth2 User name: " + name);

        // Kiểm tra và lưu trữ thông tin người dùng trong cơ sở dữ liệu
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setUsername(email); // Thiết lập username là email
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString())); // Thiết lập password ngẫu nhiên

            // Gán vai trò mặc định cho người dùng Google
            Role userRole = roleRepository.findByName("USER");
            if (userRole == null) {
                userRole = new Role();
                userRole.setName("USER");
                roleRepository.save(userRole);
            }
            user.setRoles(Collections.singletonList(userRole));
            userRepository.save(user);

            logger.info("New user created with email: " + email);
        } else {
            logger.info("User already exists with email: " + email);
        }

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new DefaultOAuth2User(
                authorities,
                oauth2User.getAttributes(),
                "name");
    }

}
