package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewJpaRepository reviewRepository;

    public ReviewServiceImpl(ReviewJpaRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByHardwareCode(String code) {
        List<Review> reviews = reviewRepository.findAllByHardware_Code(code);

        return reviews.stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByRatingBetween(int min, int max) {
        return reviewRepository.findAllByRatingBetween(min, max).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(Review review){
        return new ReviewDTO(review.getTitle(), review.getText(), review.getRating());
    }
}
