package com.trl.userservice.core.repository;

import com.trl.userservice.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
