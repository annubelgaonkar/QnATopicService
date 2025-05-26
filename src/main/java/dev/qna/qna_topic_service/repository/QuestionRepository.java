package dev.qna.qna_topic_service.repository;

import dev.qna.qna_topic_service.model.Question;
import dev.qna.qna_topic_service.model.Topic;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopic(Topic topic);
}
