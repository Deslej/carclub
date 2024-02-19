package org.example.carclub.domain.car;

import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.rating.Rating;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CarDtoMapper {



    static CarDto map(Car car){
        double avgRating=car.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(value -> value)
                .average().orElse(0);
        int ratingCount=car.getRatings().size();
        return new CarDto(car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getShortDescription(),
                car.getDescription(),
                car.getYoutubeTrailerId(),
                car.getCarYear(),
                car.getGenre().getName(),
                car.isPromoted(),
                car.getPoster(),
                avgRating,
                ratingCount);
    }
}
