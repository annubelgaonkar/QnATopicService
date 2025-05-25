package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.model.Topic;
import dev.qna.qna_topic_service.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }
}
