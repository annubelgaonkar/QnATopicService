package dev.qna.qna_topic_service.repository;
import dev.qna.qna_topic_service.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {


}
