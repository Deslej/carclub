package org.example.carclub.domain.user;

import jakarta.transaction.Transactional;
import org.example.carclub.domain.user.dto.UserCredentialsDto;
import org.example.carclub.domain.user.dto.UserRegistrationDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String DEFAULT_USER_ROLE = "USER";
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<UserCredentialsDto> findCredentialsByEmail(String email){
        return userRepository.findByEmail(email).map(UserCredentialsDtoMapper::map);
    }
    @Transactional
    public void registerUserWithDefaultRole(UserRegistrationDto userRegistration){
        UserRole userRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.getRoles().add(userRole);
        userRepository.save(user);
    }
    public String getUserEmail(Authentication authentication){
        return authentication.getName();
    }
    public void editUser(UserCredentialsDto userCredentialsDto){
        User userEdit = userRepository.findByEmail(userCredentialsDto.getEmail())
                .orElseThrow();
        userEdit.setPassword(userCredentialsDto.getPassword());
        userRepository.save(userEdit);
    }
   public Long getUserIdByEmail(String email){
       User user = userRepository.findByEmail(email).orElseThrow();
       return user.getId();
   }
}
