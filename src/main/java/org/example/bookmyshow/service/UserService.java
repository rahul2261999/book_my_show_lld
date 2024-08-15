package org.example.bookmyshow.service;

import org.example.bookmyshow.Repository.UserRepository;
import org.example.bookmyshow.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
  private UserRepository userRepository;

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public User signUp(String email, String password) {
    Optional<User> findUser = userRepository.findByEmail(email);

    if(findUser.isPresent()) {
      throw new RuntimeException("User already exist");
    }

    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setBookings(new ArrayList<>());

    return userRepository.save(user);
  }
}
