package com.exam.controller;

import com.exam.model.master.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @GetMapping("/")
    public List<Subject> getSubjects(){
        return null;
    }

    @PostMapping("/")
    public Subject addSubject(@RequestBody Subject subject){
        return null;
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable("id") Integer subjectId){
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean removeSubject(@PathVariable("id") Integer subjectId){
        return false;
    }

    @PutMapping("/{id}")
    public boolean updateSubject(@PathVariable("id") Integer id, @RequestBody Subject subject){
        return false;
    }
}
