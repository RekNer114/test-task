package me.vmachohan.auth_api.service;

import lombok.RequiredArgsConstructor;
import me.vmachohan.auth_api.entity.User;
import me.vmachohan.auth_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwdEncoder;
    private final JwtService jwtService;

    public void register(String email, String passwd){
        if(userRepository.findUserByEmail(email).isPresent()){
            throw new IllegalArgumentException("User with such email already exists");
        }

        User user = User.builder()
                .email(email)
                .passwdHash(passwdEncoder.encode(passwd))
                .build();
        userRepository.save(user);
    }

    public String login (String email, String passwd){
        User user = userRepository.findUserByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException("There's no user associated with such email"));

        if(!passwdEncoder.matches(passwd, user.getPasswdHash())){
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
