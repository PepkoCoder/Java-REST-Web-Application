package hr.tvz.stambolija.hardwareapp.hardware;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findByHardwareCode(String code);
    List<ReviewDTO> findByRatingBetween(int min, int max);
}
