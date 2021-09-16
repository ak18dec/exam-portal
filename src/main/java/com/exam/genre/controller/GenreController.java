package com.exam.genre.controller;

import com.exam.genre.exception.GenreAlreadyExistsException;
import com.exam.genre.exception.GenreNotFoundException;
import com.exam.genre.model.Genre;
import com.exam.subject.exception.SubjectNotFoundException;
import com.exam.subject.model.Subject;
import com.exam.genre.service.GenreService;
import com.exam.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Genre> getGenres(){
        return genreService.getGenres();
    }

    @PostMapping("/")
    public Genre addGenre(@RequestBody Genre genre) throws GenreAlreadyExistsException {
        return genreService.addGenre(genre);
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") Integer genreId) throws GenreNotFoundException {
        return genreService.getGenreById(genreId);
    }

    @DeleteMapping("/{id}")
    public boolean removeGenre(@PathVariable("id") Integer genreId){
        return genreService.removeGenre(genreId);
    }

    @PutMapping("/{id}")
    public boolean updateGenre(@RequestBody Genre genre, @PathVariable("id") Integer id){
        return genreService.updateGenre(id, genre);
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectByGenre(@PathVariable("id") Integer id) throws SubjectNotFoundException {
        return subjectService.getSubjectsByGenreId(id);
    }


}
