package co.com.sofka.questions.routercrud.questionscrud;


import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.QuestionUseCase.ListQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListQuestion {

    @Bean
    public RouterFunction<ServerResponse> listQuestions (ListQuestionUseCase listUseCase){
        return route(GET("/questions").and(accept(MediaType.APPLICATION_JSON)),
                request-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.listQuestion(), QuestionDTO.class)));
    }
}