package com.trl.userservice.core.service;

import com.trl.userservice.core.entity.User;
import org.springframework.data.domain.Page;

/**
 * This interface is designed to support service for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface UserService {

    void create(User user);

    User get(Long id);

    Page<User> getAll(Long id);

    User update(Long id, User user);

    void delete(Long id);
}

