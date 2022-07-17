package co.com.sofka.questions.usecase.QuestionUseCase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AnswerRepository answerRepository;

    public DeleteQuestionUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.answerRepository = answerRepository;
    }

    public Mono<Void> eliminarpregunta(String id) {
        return questionRepository.deleteById(id).switchIfEmpty(answerRepository.deleteByQuestionId(id));
    }
}
