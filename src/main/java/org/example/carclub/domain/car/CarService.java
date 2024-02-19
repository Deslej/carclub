package org.example.carclub.domain.car;

import jakarta.transaction.Transactional;
import org.example.carclub.domain.car.dto.CarDto;
import org.example.carclub.domain.car.dto.CarSaveDto;
import org.example.carclub.domain.genre.Genre;
import org.example.carclub.domain.genre.GenreRepository;
import org.example.carclub.domain.rating.Rating;
import org.example.carclub.domain.rating.RatingRepository;
import org.example.carclub.storage.FileStorageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;
    private final FileStorageService fileStorageService;

    public CarService(CarRepository carRepository, GenreRepository genreRepository, RatingRepository ratingRepository, FileStorageService fileStorageService) {
        this.carRepository = carRepository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
        this.fileStorageService = fileStorageService;
    }
    public List<CarDto> findAllPromotedCars(){
        return carRepository.findAllByPromotedIsTrue().stream()
                .map(CarDtoMapper::map)
                .toList();
    }
    public Optional<CarDto> findCarById(Long id){
        return carRepository.findById(id).map(CarDtoMapper::map);
    }
    public List<CarDto> findCarByGenreName(String genre){
        return carRepository.findAllByGenre_NameIgnoreCase(genre).stream()
                .map(CarDtoMapper::map)
                .toList();
    }
    public void addCar(CarSaveDto carSaveDto){
        Car car = new Car();
        car.setCarYear(carSaveDto.getCarYear());
        car.setBrand(carSaveDto.getBrand());
        car.setModel(carSaveDto.getModel());
        car.setDescription(carSaveDto.getDescription());
        car.setShortDescription(carSaveDto.getShortDescription());
        car.setYoutubeTrailerId(carSaveDto.getYoutubeTrailerId());
        car.setPromoted(carSaveDto.isPromoted());
        Genre genre = genreRepository.findByNameIgnoreCase(carSaveDto.getGenre()).orElseThrow();
        car.setGenre(genre);
        if (carSaveDto.getPoster() != null && !carSaveDto.getPoster().isEmpty()) {
            String savedFileName = fileStorageService.saveImage(carSaveDto.getPoster());
            car.setPoster(savedFileName);
        }
        carRepository.save(car);
    }
    public void updateCar(CarDto carDto,MultipartFile poster){
        Car carEdit = carRepository.findById(carDto.getId()).orElseThrow();
        carEdit.setDescription(carDto.getDescription());
        carEdit.setShortDescription(carDto.getShortDescription());
        carEdit.setPromoted(carDto.isPromoted());
        carEdit.setYoutubeTrailerId(carDto.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(carDto.getGenre()).orElseThrow();
        carEdit.setGenre(genre);
        if (poster!= null && !poster.isEmpty()) {
            String savedFileName = fileStorageService.saveImage(poster);
            carEdit.setPoster(savedFileName);
        }
        carRepository.save(carEdit);
    }
    public void deleteCarById(Long id){
        ratingRepository.deleteCarById(id);
        carRepository.deleteById(id);
    }

    public List<CarDto> findAllCars(){
        return carRepository.findAllCars().stream().map(CarDtoMapper::map).toList();
    }
    public List<CarDto> findTopCars(int size){
        Pageable page = Pageable.ofSize(size);
        return carRepository.findTopByRating(page).stream()
                .map(CarDtoMapper::map)
                .toList();
    }
}

