package org.example.carclub.domain.car;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CarRepository extends CrudRepository<Car,Long> {
    List<Car> findAllByPromotedIsTrue();
    List<Car> findAllByGenre_NameIgnoreCase(String genre);

    @Query("select c from Car c join c.ratings r group by c order by avg(r.rating) desc ")
    List<Car> findTopByRating(Pageable page);
    @Query("SELECT DISTINCT c FROM Car c")
    List<Car> findAllCars();
}
