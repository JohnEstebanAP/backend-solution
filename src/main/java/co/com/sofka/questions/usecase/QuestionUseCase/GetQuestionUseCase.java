package co.com.sofka.questions.usecase.QuestionUseCase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class GetQuestionUseCase  implements Function<String, Mono<QuestionDTO>> {

  private final QuestionRepository questionRepository;
  private final QuestionMapper questionMapper;

  @Autowired
  public GetQuestionUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper) {
    this.questionRepository = questionRepository;
    this.questionMapper = questionMapper;
  }

  @Override
  public Mono<QuestionDTO> apply(String id) {
    return questionRepository.findById(id).map(questionMapper.mapQuestionToDTO());
  }
}
