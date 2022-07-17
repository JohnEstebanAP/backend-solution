package co.com.sofka.questions.usecase.QuestionUseCase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class SaveQuestionUseCase {

  private final QuestionRepository questionRepository;
  private final QuestionMapper questionMapper;

  @Autowired
  public SaveQuestionUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper) {
    this.questionRepository = questionRepository;
    this.questionMapper = questionMapper;
  }

  public Mono<QuestionDTO> save(QuestionDTO questionDTO) {
    var respuesta = questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO);
    return questionRepository.save(respuesta).map(questionMapper.mapQuestionToDTO());
  }
}
