package com.exam.subject.controller;

import com.exam.subject.exception.SubjectAlreadyExistsException;
import com.exam.subject.exception.SubjectNotFoundException;
import com.exam.subject.model.Subject;
import com.exam.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }

    @PostMapping("/")
    public Subject addSubject(@RequestBody Subject subject) throws SubjectAlreadyExistsException {
        return subjectService.addSubject(subject);
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable("id") Integer subjectId) throws SubjectNotFoundException {
        return subjectService.getSubjectById(subjectId);
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
