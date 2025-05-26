package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.model.Question;
import dev.qna.qna_topic_service.model.Topic;
import dev.qna.qna_topic_service.repository.QuestionRepository;
import dev.qna.qna_topic_service.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;

    public QuestionService(QuestionRepository questionRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> getQuestionsByTopicId(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        return questionRepository.findByTopic(topic);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.setQuestion(updatedQuestion.getQuestion());
                    question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
                    question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

}
