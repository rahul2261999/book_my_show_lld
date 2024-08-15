package org.example.bookmyshow.Repository;

import org.example.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Override
  Optional<User> findById(Long Id);
  Optional<User> findByEmail(String email);
   User save(User entity);
}
