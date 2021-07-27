package com.exam.service;

import com.exam.model.master.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.exam.datafactory.Factory.subjects;

@Service
public class SubjectService {

    public List<Subject> getSubjects(){
        return subjects;
    }

    public Subject addSubject(Subject subject){
        subject.setId(subjects.size()+1);
        subjects.add(subject);
        return subject;
    }

    public Subject getSubject(Integer subjectId){
        return subjects.stream().filter(s -> s.getId() == subjectId).findFirst().orElse(null);
    }

    public boolean removeSubject(Integer subjectId){
        return subjects.removeIf(s -> s.getId() == subjectId);
    }

    public boolean updateSubject(Integer id, Subject subject){
        for (int i=0; i<subjects.size();i++){
            Subject s = subjects.get(i);
            if(s.getId() == id){
                subjects.set(i, subject);
                return true;
            }
        }
        return false;
    }

    public List<Subject> getSubjectByGenre(Integer genreId){
        return subjects.stream().filter(s -> s.getGenreId() == genreId).collect(Collectors.toList());
    }
}
