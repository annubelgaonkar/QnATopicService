package dev.qna.qna_topic_service.mapper;

import dev.qna.qna_topic_service.dto.TopicDTO;
import dev.qna.qna_topic_service.model.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TopicMapper {

    //Convert the entity to DTO
    public static TopicDTO toDTO(Topic topic) {
        if(topic == null){
            return null;
        }
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        return topicDTO;
    }

    //convert DTO to entity
    public static Topic toEntity(TopicDTO topicDTO) {
        if(topicDTO == null){
            return null;
        }
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        return topic;
    }

    // Convert list of Entities to list of DTOs
    public static List<TopicDTO> toDTOList(List<Topic> topics) {
        if(topics == null){
            return null;
        }

        return topics.stream()
                .map(TopicMapper::toDTO)
                .collect(Collectors.toList());
    }

    //convert list of Dtos to list of entities
    public static List<Topic> toEntityList(List<TopicDTO> topicDTOList) {
        if(topicDTOList == null){
            return null;
        }
        return topicDTOList.stream()
                .map(TopicMapper::toEntity)
                .collect(Collectors.toList());
    }
}
