package com.essenstore.service;

import com.essenstore.dto.RegisterUserDto;
import com.essenstore.entity.Role;
import com.essenstore.entity.Status;
import com.essenstore.entity.User;
import com.essenstore.entity.Verified;
import com.essenstore.exception.NotFoundException;
import com.essenstore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseEntityService<User, Long> implements UserDetailsService {

    private final UserRepository repository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(User emptyObject, UserRepository repository, ModelMapper modelMapper) {
        super(emptyObject, repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

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

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }


    @Override
    public User getBy(String email) throws NotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> {
            throw new NotFoundException();
        });
    }

    @Transactional
    public void save(RegisterUserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setVerified(Verified.DISABLED);
        super.save(user);
    }
}
