package ProjetoApiRest.GestaoDePecas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Operation(description = "retorna o status da aplicação")
    @ApiResponses(value = {
            @ApiResponse(description = "você não tem as permissões necessárias"),
            @ApiResponse(responseCode = "404", description = "Aplicação não encontrada")
    })
    @GetMapping(path = "/api/status")
    public String check(){
        return "online";
    }

}
