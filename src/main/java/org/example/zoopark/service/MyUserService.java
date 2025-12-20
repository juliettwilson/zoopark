package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.entity.UserModel;
import org.example.zoopark.repository.PermissionRepository;
import org.example.zoopark.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User not found");
    }

    public UserModel register(UserModel userModel) {
        UserModel existing = userRepository.findByEmail(userModel.getEmail());
        if (existing == null) {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userModel.setPermissions(List.of(permissionRepository.findByName("ROLE_USER")));
            return userRepository.save(userModel);
        }
        return existing;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserModel update(Long id, UserModel userModel) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        if (userModel.getPassword() != null && !userModel.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }

        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}