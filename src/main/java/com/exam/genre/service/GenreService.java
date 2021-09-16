package com.exam.genre.service;

import com.exam.genre.exception.GenreAlreadyExistsException;
import com.exam.genre.exception.GenreNotFoundException;
import com.exam.genre.model.Genre;
import com.exam.genre.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;


@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre) throws GenreAlreadyExistsException {
        final boolean genreExistWithTitle = genreRepository.genreExistsByTitle(genre.getTitle());

        if(genreExistWithTitle){
            throw new GenreAlreadyExistsException(GENRE_ALREADY_EXISTS+genre.getTitle());
        }else{
            int newGenreId = genreRepository.addGenre(genre);
            genre.setId(newGenreId);
        }

        return genre;
    }

    public Genre getGenreById(Integer genreId) throws GenreNotFoundException {
        final Genre genre = genreRepository.findById(genreId);
        if(genre == null) {
            throw new GenreNotFoundException(GENRE_NOT_FOUND_FOR_ID+genreId);
        }
        return genre;
    }

    public Genre getGenreByTitle(String title) throws GenreNotFoundException {
        final Genre genre = genreRepository.findByTitle(title);
        if(genre == null) {
            throw new GenreNotFoundException(GENRE_NOT_FOUND_FOR_TITLE+title);
        }
        return genre;
    }

    public boolean removeGenre(Integer genreId){
        return genreRepository.delete(genreId);
    }

    public boolean removeAllGenres(){
        return genreRepository.deleteAll();
    }

    public boolean removeGenresByIds(List<Integer> ids){
        return genreRepository.deleteByIds(ids);
    }

    public boolean updateGenre(Integer id, Genre genre){
        return genreRepository.updateGenre(id, genre);
    }
}
