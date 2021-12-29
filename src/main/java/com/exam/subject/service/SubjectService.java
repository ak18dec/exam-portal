package com.exam.subject.service;

import com.exam.subject.exception.SubjectAlreadyExistsException;
import com.exam.subject.exception.SubjectNotFoundException;
import com.exam.subject.model.Subject;
import com.exam.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public Subject addSubject(Subject subject, int loggedInUserId) throws SubjectAlreadyExistsException {
        final boolean subjectExistWithTitle = subjectRepository.subjectExistsByTitle(subject.getTitle());

        if(subjectExistWithTitle){
            throw new SubjectAlreadyExistsException(SUBJECT_ALREADY_EXISTS+subject.getTitle());
        }else{
            int newSubjectId = subjectRepository.addSubject(subject, loggedInUserId);
            subject.setId(newSubjectId);
        }

        return subject;
    }

    public Subject getSubjectById(Integer subjectId) throws SubjectNotFoundException {
        final Subject subject = subjectRepository.findById(subjectId);
        if(subject == null) {
            throw new SubjectNotFoundException(SUBJECT_NOT_FOUND_FOR_ID+subjectId);
        }
        return subject;
    }

    public Subject getSubjectByTitle(String title) throws SubjectNotFoundException {
        final Subject subject = subjectRepository.findByTitle(title);
        if(subject == null) {
            throw new SubjectNotFoundException(SUBJECT_NOT_FOUND_FOR_TITLE+title);
        }
        return subject;
    }

//    public List<Subject> getSubjectsByGenreId(Integer genreId) throws SubjectNotFoundException {
//        final List<Subject> subjects = subjectRepository.findByGenreId(genreId);
//        if(subjects == null || subjects.isEmpty()) {
//            throw new SubjectNotFoundException(SUBJECT_NOT_FOUND_FOR_GENRE_ID+genreId);
//        }
//        return subjects;
//    }

    public boolean removeSubject(Integer subjectId){
        return subjectRepository.delete(subjectId);
    }

    public boolean removeAllSubjects(){
        return subjectRepository.deleteAll();
    }

    public boolean removeSubjectsByIds(List<Integer> ids){
        return subjectRepository.deleteByIds(ids);
    }

    public boolean updateSubject(Integer id, Subject subject, int loggedInUserId){
        return subjectRepository.updateSubject(id, subject, loggedInUserId);
    }
}
