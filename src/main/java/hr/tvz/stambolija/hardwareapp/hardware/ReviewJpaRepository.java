package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHardware_Code(String code);

    List<Review> findAllByRatingBetween(int min, int max);
}
