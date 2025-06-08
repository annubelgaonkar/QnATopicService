package dev.qna.qna_topic_service.dto;

public class QuestionResponseDTO {
    private String topic;
    private String difficulty;
    private String questionText;

    //deafault constructor
    public QuestionResponseDTO() {

    }

    public QuestionResponseDTO(String topic, String difficulty, String questionText) {
        this.topic = topic;
        this.difficulty = difficulty;
        this.questionText = questionText;
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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
