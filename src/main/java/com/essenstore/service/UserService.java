package com.essenstore.service;

import com.essenstore.entity.Role;
import com.essenstore.entity.User;
import com.essenstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<User, Long> implements UserDetailsService {

    private final UserRepository repository;

    public UserService(User emptyObject, UserRepository repository) {
        super(emptyObject, repository);
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(user ->
                        org.springframework.security.core.userdetails.User
                                .builder()
                                .username(user.getEmail())
                                .password(user.getPassword())
                                .authorities(user.getRole())
                                .disabled(!user.getVerified().isVerified())
                                .accountLocked(!user.getStatus().isActive())
                                .build())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
    }

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }


    @Override
    public User getBy(String email) {
        return repository.findByEmail(email).orElse(getEmptyObject());
    }
}
