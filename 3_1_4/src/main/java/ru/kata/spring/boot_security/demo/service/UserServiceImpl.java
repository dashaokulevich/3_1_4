package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entites.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;



@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    public void update(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with email address '%s' not found", email));
        }

        return user;
    }
}
