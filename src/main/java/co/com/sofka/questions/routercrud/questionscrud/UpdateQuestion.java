package co.com.sofka.questions.routercrud.questionscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.QuestionUseCase.UpdateQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateQuestion {

    @Bean
    public RouterFunction<ServerResponse> modificarQuestions(UpdateQuestionUseCase updateUseCase){
        return route(PUT("/modificarQuestion").and(accept(MediaType.APPLICATION_JSON)),
                request ->request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> updateUseCase.update(questionDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }
}