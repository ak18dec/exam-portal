package com.exam.controller;

import com.exam.model.master.Genre;
import com.exam.model.master.Subject;
import com.exam.service.GenreService;
import com.exam.service.SubjectService;
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
    public Genre addGenre(@RequestBody Genre genre){
        return genreService.addGenre(genre);
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") Integer genreId){
        return genreService.getGenre(genreId);
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
    public List<Subject> getSubjectByGenre(@PathVariable("id") Integer id){
        return subjectService.getSubjectByGenre(id);
    }


}
