package org.example.bookmyshow.Repository;

import org.example.bookmyshow.models.Show;
import org.example.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

  @Override
  Optional<Show> findById(Long id);

  ShowSeat save(ShowSeat entity);
}
