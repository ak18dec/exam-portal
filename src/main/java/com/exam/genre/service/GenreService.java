package com.exam.genre.service;

import com.exam.genre.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.datafactory.Factory.genres;

@Service
public class GenreService {

    public List<Genre> getGenres(){
        return genres;
    }

    public Genre addGenre(Genre genre){
        genre.setId(genres.size()+1);
        genres.add(genre);
        return genre;
    }

    public Genre getGenre(Integer genreId) {
        return genres.stream().filter(g -> g.getId() == genreId).findFirst().orElse(null);
    }

    public boolean removeGenre(Integer genreId){
        return genres.removeIf(g -> g.getId() == genreId);
    }

    public boolean updateGenre(Integer id, Genre genre){
        for (int i=0; i<genres.size();i++){
            Genre g = genres.get(i);
            if(g.getId() == id){
                genres.set(i, genre);
                return true;
            }
        }
        return false;
    }
}
