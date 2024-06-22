package ProjetoApiRest.GestaoDePecas.Controller;

import ProjetoApiRest.GestaoDePecas.Dto.PecaRecorDto;
import ProjetoApiRest.GestaoDePecas.Model.PecaModel;
import ProjetoApiRest.GestaoDePecas.Repository.PecaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PecaController {

    @Autowired
    private PecaRepository pecaRepository;

    @Operation(description = "Posta uma peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A peça foi adicionada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "A peça NÃO foi adicionada")
    })
    @PostMapping("/peca")
    public ResponseEntity<PecaModel> savePeca(@RequestBody @Valid PecaRecorDto pecaRecorDto){
        var pecaModel = new PecaModel();
        BeanUtils.copyProperties(pecaRecorDto, pecaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaRepository.save(pecaModel));
    }
    @Operation(description = "Retorna todas as peças cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos as peças"),
            @ApiResponse(responseCode = "400", description = "Nenhuma peça cadastrada")
    })
    @GetMapping("/peca")
    public ResponseEntity<List<PecaModel>> getAllPecas(){
        return ResponseEntity.status(HttpStatus.OK).body(pecaRepository.findAll());
    }

    @Operation(description = "Busca a peça pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna a peca"),
            @ApiResponse(responseCode = "400", description = "Não existe a peça com ID")
    })
    @GetMapping("/peca/{id}")
    public ResponseEntity<Object> getOnePeca(@PathVariable(value = "id") UUID id){
        Optional<PecaModel> peca0 = pecaRepository.findById(id);
        if(peca0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peca0.get());
    }

    @Operation(description = "Modifica a peça pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peça modificada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Modificação não concluída")
    })
    @PutMapping(path = "/peca/{id}")
    public ResponseEntity<Object> updatePeca(@PathVariable(value = "id") UUID id, @RequestBody @Valid PecaRecorDto pecaRecorDto){
        Optional<PecaModel> peca0 = pecaRepository.findById(id);
        if(peca0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var pecaModel = peca0.get();
        BeanUtils.copyProperties(pecaRecorDto, pecaModel);
        return ResponseEntity.status(HttpStatus.OK).body(pecaRepository.save(pecaModel));
    }

    @Operation(description = "Deleta uma peça pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peça deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Peça não encontrada")
    })
    @DeleteMapping("/peca/{id}")
    public ResponseEntity<Object> deletePeca(@PathVariable(value = "id") UUID id){
        Optional<PecaModel> peca0 = pecaRepository.findById(id);
        if(peca0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        pecaRepository.delete(peca0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
