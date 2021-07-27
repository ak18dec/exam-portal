package com.exam.controller;

import com.exam.model.master.Genre;
import com.exam.model.master.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @GetMapping("/")
    public List<Genre> getGenres(){
        return null;
    }

    @PostMapping("/")
    public Genre addGenre(@RequestBody Genre genre){
        return null;
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") Integer genreId){
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean removeGenre(@PathVariable("id") Integer genreId){
        return false;
    }

    @PutMapping("/{id}")
    public boolean updateGenre(@RequestBody Genre genre, @PathVariable("id") Integer id){
        return false;
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectByGenre(@PathVariable("id") Integer id){
        return null;
    }


}
