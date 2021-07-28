package userservice.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import userservice.dto.UserRequest;
import userservice.dto.UserResponse;
import userservice.entity.AppUser;
import userservice.entity.ConfirmationToken;
import userservice.repository.UserRepository;
import userservice.security.jwt.JwtUtil;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final static String EMAIL_NOT_FOUND_MSG = "email not found";

    public String registerUser(AppUser user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent()
                || userRepository.findByUsername(user.getUsername()).isPresent();
        String token = UUID.randomUUID().toString();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30), user);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        }
        return token;
    }

    public String resendUserToken(String email) {
        Optional<AppUser> user = userRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();
        if (user.isPresent()) {
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30), user.get());
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        } else {
            throw new IllegalStateException("invalid email");
        }
        return token;
    }

    public Boolean validateUsername(String username) {
        boolean isUnique = true;
        Optional<AppUser> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            isUnique = false;
        }
        return isUnique;
    }

    public Boolean validateEmail(String email) {
        boolean isUnique = true;
        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            isUnique = false;
        }
        return isUnique;
    }

    public void updateUser(String token, UserRequest request) {

        String tokenUsername = jwtUtil.getUsernameFromToken(token);
        System.out.println("\n\n" + tokenUsername + "\n\n");
        Optional<AppUser> userOptional = userRepository.findByUsername(tokenUsername);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException(USER_NOT_FOUND_MSG);
        }
        AppUser user = userOptional.get();
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public UserResponse getUser(String token) {
        String tokenUsername = jwtUtil.getUsernameFromToken(token);
        System.out.println("\n\n" + tokenUsername + "\n\n");
        Optional<AppUser> userOptional = userRepository.findByUsername(tokenUsername);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException(USER_NOT_FOUND_MSG);
        }
        return new UserResponse(userOptional.get().getEmail(), userOptional.get().getPhone(),
                userOptional.get().getUsername());
    }

    public void enableUser(String email) {
        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent())
            throw new IllegalStateException(EMAIL_NOT_FOUND_MSG);
        AppUser user = userOptional.get();
        user.setConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

}