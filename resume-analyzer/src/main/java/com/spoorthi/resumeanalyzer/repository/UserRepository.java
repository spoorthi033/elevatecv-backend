package com.spoorthi.resumeanalyzer.repository;

import com.spoorthi.resumeanalyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
