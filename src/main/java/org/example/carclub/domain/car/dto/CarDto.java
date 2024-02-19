package org.example.carclub.domain.car.dto;

import jakarta.persistence.*;
import org.example.carclub.domain.genre.Genre;

public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;

    private Integer carYear;
    private String poster;

    private String genre;
    private boolean promoted;
    private double avgRating;
    private int ratingCount;

    public CarDto(Long id, String brand, String model, String shortDescription,
                  String description, String youtubeTrailerId, Integer carYear,
                  String genre, boolean promoted,String poster,
                  double avgRating,int ratingCount) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.shortDescription = shortDescription;
        this.description = description;
        this.youtubeTrailerId = youtubeTrailerId;
        this.carYear = carYear;
        this.poster=poster;
        this.genre = genre;
        this.promoted = promoted;
        this.avgRating=avgRating;
        this.ratingCount=ratingCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
