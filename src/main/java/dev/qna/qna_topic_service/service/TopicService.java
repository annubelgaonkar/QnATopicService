package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.model.Topic;
import dev.qna.qna_topic_service.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic updateTopic(Long id, Topic updatedTopic) {
        return topicRepository.findById(id)
                .map(topic -> {
                    topic.setName(updatedTopic.getName());
                    return topicRepository.save(topic);
                })
                .orElseThrow(() -> new RuntimeException("Topic not found"));
    }
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }
}
