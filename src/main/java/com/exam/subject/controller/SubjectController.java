package com.exam.subject.controller;

import com.exam.subject.model.Subject;
import com.exam.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }

    @PostMapping("/")
    public Subject addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable("id") Integer subjectId){
        return subjectService.getSubject(subjectId);
    }

    @DeleteMapping("/{id}")
    public boolean removeSubject(@PathVariable("id") Integer subjectId){
        return subjectService.removeSubject(subjectId);
    }

    @PutMapping("/{id}")
    public boolean updateSubject(@PathVariable("id") Integer id, @RequestBody Subject subject){
        return subjectService.updateSubject(id, subject);
    }
}
