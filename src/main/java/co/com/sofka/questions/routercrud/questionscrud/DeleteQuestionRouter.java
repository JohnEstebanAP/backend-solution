package co.com.sofka.questions.routercrud.questionscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.QuestionUseCase.DeleteQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class DeleteQuestionRouter {
    @Bean
    public RouterFunction<ServerResponse> deleteQuestion(DeleteQuestionUseCase deleteUseCase) {
        return route(DELETE("/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(deleteUseCase.eliminarpregunta(request.pathVariable("id")), QuestionDTO.class)
        );
    }
}
