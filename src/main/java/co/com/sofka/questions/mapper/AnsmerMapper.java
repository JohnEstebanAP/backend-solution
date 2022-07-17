package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AnsmerMapper {

    public Function<Answer, AnswerDTO> fromAnswerDTO(){
        return entity ->
                new AnswerDTO(
                        entity.getUserId(),
                        entity.getQuestionId(),
                        entity.getAnswer()
                );
    }

    public Function<AnswerDTO, Answer> fromAnswerDtoToAnswer(String id){
           return updateAnswer -> {
               var answer = new Answer();
               answer.setId(id);
               answer.setUserId(updateAnswer.getUserId());
               answer.setQuestionId(updateAnswer.getQuestionId());
               answer.setAnswer(updateAnswer.getAnswer());
               answer.setPosition(updateAnswer.getPosition());
               return answer;
           };
    }
}
