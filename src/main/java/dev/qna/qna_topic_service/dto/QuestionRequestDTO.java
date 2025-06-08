package dev.qna.qna_topic_service.dto;

public class QuestionRequestDTO {
    private String topic;
    private String difficulty;

    public QuestionRequestDTO() {

    }
    public QuestionRequestDTO(String topic, String difficulty) {
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
