package org.example.zoopark.service;

import lombok.RequiredArgsConstructor;
import org.example.zoopark.entity.Permission;
import org.example.zoopark.entity.UserModel;
import org.example.zoopark.repository.PermissionRepository;
import org.example.zoopark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User not found");
    }


    public void register(UserModel userModel) {
        UserModel check = userRepository.findByEmail(userModel.getEmail());
        if( check == null) {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            List<Permission> permissions = List.of(permissionRepository.findByName("ROLE_USER"));

            userModel.setPermissions(permissions);
            userRepository.save(userModel);
        }
    }


}