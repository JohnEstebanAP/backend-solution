package co.com.sofka.questions.usecase;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class SaveQuestionUseCase {

        private QuestionRepository questionRepository;
        private QuestionMapper questionMapper;

        @Autowired
        public SaveQuestionUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper) {
            this.questionRepository = questionRepository;
            this.questionMapper = questionMapper;
        }

        public Mono<QuestionDTO> save(QuestionDTO questionDTO){
            return questionRepository
                    .save(questionMapper
                            .mapperToQuestion(null)
                            .apply(questionDTO))
                    .map(questionMapper.mapQuestionToDTO());
        }
}
