package com.exam.topic.service;

import com.exam.topic.exception.TopicAlreadyExistsException;
import com.exam.topic.exception.TopicNotFoundException;
import com.exam.topic.model.Topic;
import com.exam.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getTopics(){
        return topicRepository.findAll();
    }

    public Topic addTopic(Topic topic, int loggedInUserId) throws TopicAlreadyExistsException {
        final boolean topicExistWithTitle = topicRepository.topicExistsByTitle(topic.getTitle());

        if(topicExistWithTitle){
            throw new TopicAlreadyExistsException(TOPIC_ALREADY_EXISTS+topic.getTitle());
        }else{
            int newTopicId = topicRepository.addTopic(topic, loggedInUserId);
            topic.setId(newTopicId);
        }

        return topic;
    }

    public Topic getTopicById(Integer topicId) throws TopicNotFoundException {
        final Topic topic = topicRepository.findById(topicId);
        if(topic == null) {
            throw new TopicNotFoundException(TOPIC_NOT_FOUND_FOR_ID+topicId);
        }
        return topic;
    }

    public Topic getTopicByTitle(String title) throws TopicNotFoundException {
        final Topic topic = topicRepository.findByTitle(title);
        if(topic == null) {
            throw new TopicNotFoundException(TOPIC_NOT_FOUND_FOR_TITLE+title);
        }
        return topic;
    }

    public List<Topic> getTopicsBySubjectId(Integer subjectId) throws TopicNotFoundException {
        final List<Topic> topics = topicRepository.findBySubjectId(subjectId);
        if(topics == null || topics.isEmpty()) {
            throw new TopicNotFoundException(TOPIC_NOT_FOUND_FOR_SUBJECT_ID+subjectId);
        }
        return topics;
    }

    public boolean removeTopic(Integer topicId){
        return topicRepository.delete(topicId);
    }

    public boolean removeAllTopics(){
        return topicRepository.deleteAll();
    }

    public boolean removeTopicsByIds(List<Integer> ids){
        return topicRepository.deleteByIds(ids);
    }

    public boolean updateTopic(Integer id, Topic topic, int loggedInUserId){
        return topicRepository.updateTopic(id, topic, loggedInUserId);
    }
}
