package org.example.carclub.domain.genre;

import org.example.carclub.domain.genre.dto.GenreDto;

public class GenreDtoMapper {
    static GenreDto map(Genre genre){
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription());
    }
}
