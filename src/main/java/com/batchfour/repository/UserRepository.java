package com.batchfour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batchfour.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
