package com.essenstore.service;

import com.essenstore.entity.Role;
import com.essenstore.entity.Status;
import com.essenstore.entity.User;
import com.essenstore.entity.Verified;
import com.essenstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService, EntityService<User, Long>, FieldUniqueService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(user ->
                        org.springframework.security.core.userdetails.User
                                .builder()
                                .username(user.getEmail())
                                .password(user.getPassword())
                                .roles(user.getRole().toString())
                                .disabled(!user.getVerified().isVerified())
                                .accountLocked(!user.getStatus().isActive())
                                .build())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
    }

    @Transactional
    public User register(User user) {
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setVerified(Verified.DISABLED);
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public User getBy(Long id) {
        return null;
    }

    @Override
    public User getBy(String name) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

}
