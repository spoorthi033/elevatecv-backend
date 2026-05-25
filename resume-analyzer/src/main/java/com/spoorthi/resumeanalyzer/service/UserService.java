package com.spoorthi.resumeanalyzer.service;

import com.spoorthi.resumeanalyzer.model.User;
import com.spoorthi.resumeanalyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
