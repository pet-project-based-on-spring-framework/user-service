package com.trl.userservice.core.service.impl;

import com.trl.userservice.core.entity.User;
import com.trl.userservice.core.repository.UserRepository;
import com.trl.userservice.core.service.UserService;
import com.trl.userservice.core.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        // TODO: Implement this functionality.
    }

    @Cacheable(value = "userCache", key = "'by_user_id_' + #id")
    @Override
    public User getById(Long id) {
        User result;

        Optional<User> userRepositoryById = userRepository.findById(id);

        if (userRepositoryById.isEmpty()) {
            String errorMessage = format("A user with this ID = [%d] does not found.", id);
            LOG.debug("In getById - {}", errorMessage);
            throw new UserNotFoundException(errorMessage);
        }

        result = userRepositoryById.get();

        LOG.debug("In getById - Found user : [{}]", result);

        return result;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        Page<User> pageResult;

        pageResult = userRepository.findAll(pageable);

        LOG.debug("In getAll - Found Page of Users : [{}]", pageResult);

        return pageResult;
    }

    @Override
    public User update(Long id, User user) {
        // TODO: Implement this functionality.
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO: Implement this functionality.
    }
}
