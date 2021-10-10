package com.exam.topic.controller;

import com.exam.topic.exception.TopicAlreadyExistsException;
import com.exam.topic.exception.TopicNotFoundException;
import com.exam.topic.model.Topic;
import com.exam.topic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@CrossOrigin("*")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public List<Topic> getTopics(){
        return topicService.getTopics();
    }

    @PostMapping("/")
    public Topic addTopic(@RequestBody Topic topic) throws TopicAlreadyExistsException {
        return topicService.addTopic(topic);
    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable("id") Integer topicId) throws TopicNotFoundException {
        return topicService.getTopicById(topicId);
    }

    @DeleteMapping("/{id}")
    public boolean removeTopic(@PathVariable("id") Integer topicId){
        return topicService.removeTopic(topicId);
    }

    @PutMapping("/{id}")
    public boolean updateTopic(@PathVariable("id") Integer id, @RequestBody Topic topic){
        return topicService.updateTopic(id, topic);
    }
}
