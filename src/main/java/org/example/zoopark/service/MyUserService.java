package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.dto.UserDto;
import org.example.zoopark.entity.Permission;
import org.example.zoopark.entity.UserModel;
import org.example.zoopark.mapper.UserMapper;
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
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User not found");
    }



    public UserDto register(UserDto userDto) {

        UserModel check = userRepository.findByEmail(userDto.getEmail());
        if (check != null) {
            throw new RuntimeException("User with this email already exists");
        }

        UserModel user = new UserModel();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<Permission> permissions =
                List.of(permissionRepository.findByName("ROLE_USER"));
        user.setPermissions(permissions);

        return userMapper.toDto(userRepository.save(user));
    }



    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserModel getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));
    }

    public UserModel update(Long id, UserModel newUser) {

        UserModel existing = getById(id);

        existing.setUsername(newUser.getUsername());
        existing.setEmail(newUser.getEmail());

        if (newUser.getPassword() != null &&
                !newUser.getPassword().isBlank()) {

            existing.setPassword(
                    passwordEncoder.encode(newUser.getPassword())
            );
        }

        return userRepository.save(existing);
    }

    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}