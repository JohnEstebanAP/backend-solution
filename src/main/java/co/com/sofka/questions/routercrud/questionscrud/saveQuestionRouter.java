package co.com.sofka.questions.routercrud.questionscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.SaveQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class saveQuestionRouter {
    @Bean
    public RouterFunction<ServerResponse> saveQuestion(SaveQuestionUseCase saveQuestionUseCase ){
        return route(POST("/question").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> saveQuestionUseCase.save(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
                );
    }
}
