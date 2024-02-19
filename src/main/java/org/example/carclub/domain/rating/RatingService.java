package org.example.carclub.domain.rating;

import jakarta.transaction.Transactional;
import org.example.carclub.domain.car.Car;
import org.example.carclub.domain.car.CarRepository;
import org.example.carclub.domain.user.User;
import org.example.carclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, CarRepository carRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }
    public void addOrUpdateRating(String userEmail, long carId, int rating){
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_EmailAndCar_Id(userEmail, carId)
                .orElseGet(Rating::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        Car car = carRepository.findById(carId).orElseThrow();
        ratingToSaveOrUpdate.setUser(user);
        ratingToSaveOrUpdate.setCar(car);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }
    public Optional<Integer> getUserRatingForCar(String userEmail, long carId){
        return ratingRepository.findByUser_EmailAndCar_Id(userEmail,carId)
               .map(Rating::getRating);
    }
    public List<Long> getAllUserRatings(Long id){
        return ratingRepository.findCarsIdByUser(id)
                .stream()
                .toList();
    }

    public void deleteByUserAndCarId(String email,Long id){
        Rating rating = ratingRepository.findByUser_EmailAndCar_Id(email, id).orElseThrow();
        ratingRepository.deleteById(rating.getId());
    }

}
