package org.example.carclub.domain.rating;

import jakarta.transaction.Transactional;
import org.example.carclub.domain.car.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating,Long> {

    Optional<Rating> findByUser_EmailAndCar_Id(String email, Long carId);

    @Query("SELECT r.car.id FROM Rating r WHERE r.user.id = :userId")
    List<Long> findCarsIdByUser(@Param("userId") Long userId);
    @Transactional
    @Modifying
    @Query("DELETE FROM Rating c WHERE c.car.id = :id")
    void deleteCarById(@Param("id") Long id);
}
