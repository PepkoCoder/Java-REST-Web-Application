package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping(params = "hardwareCode")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReviewDTO>> getReviewByHardwareCode(@RequestParam final String hardwareCode){
        List<ReviewDTO> reviewDTOs = reviewService.findByHardwareCode(hardwareCode);

        System.out.println(reviewDTOs);

        if(reviewDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(reviewDTOs);
        }
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity<List<ReviewDTO>> getReviewByRatingBetween(@PathVariable("min") final int min, @PathVariable("max") final int max){
        List<ReviewDTO> reviewDTOs = reviewService.findByRatingBetween(min, max);

        System.out.println("Min: " + min + " Max: " + max);
        System.out.println(reviewDTOs);

        if(reviewDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(reviewDTOs);
        }
    }
}
