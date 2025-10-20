package com.practice.rk2.service;

import com.practice.rk2.dto.UserRequestDto;
import com.practice.rk2.model.Role;
import com.practice.rk2.model.User;
import com.practice.rk2.repository.UserRepository;
import com.practice.rk2.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepo, AuthenticationManager authManager, JwtService jwtService) {
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String displayStats(String username) {
        User user = userRepo.findByUsername(username);
        return  "Stats: " +
                "\nRank: " + user.getRank() +
                "\nLevel: " + user.getLevel() +
                "\nIntelligence: " + user.getIntelligence() +
                "  xp: " + user.getIntelligenceXP() + "/100" +
                "\nFitness: " + user.getFitness() +
                "  xp: " + user.getFitnessXP() + "/100" ;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    public User createUser(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
        return user;
    }



    public void updateRank(User user) {
        int level = user.getLevel();
        if (level >= 100) user.setRank("S");
        else if (level >= 80) user.setRank("A");
        else if (level >= 60) user.setRank("B");
        else if (level >= 40) user.setRank("C");
        else if (level >= 20) user.setRank("D");
        else user.setRank("E");
        userRepo.save(user);
    }

    public String verify(UserRequestDto user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "invalid credentials!";
        }
    }

    public User getUserByUserId(Long userId) {
        return userRepo.findById(userId).get();
    }
}


