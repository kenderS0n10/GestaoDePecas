package ProjetoApiRest.GestaoDePecas.Controller;

import ProjetoApiRest.GestaoDePecas.Dto.PecaRecorDto;
import ProjetoApiRest.GestaoDePecas.Model.PecaModel;
import ProjetoApiRest.GestaoDePecas.Repository.PecaRepository;
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

    @PostMapping("/peca")
    public ResponseEntity<PecaModel> savePeca(@RequestBody @Valid PecaRecorDto pecaRecorDto){
        var pecaModel = new PecaModel();
        BeanUtils.copyProperties(pecaRecorDto, pecaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaRepository.save(pecaModel));
    }
    @GetMapping("/peca")
    public ResponseEntity<List<PecaModel>> getAllPecas(){
        return ResponseEntity.status(HttpStatus.OK).body(pecaRepository.findAll());
    }

    @GetMapping("/peca/{id}")
    public ResponseEntity<Object> getOnePeca(@PathVariable(value = "id") UUID id){
        Optional<PecaModel> peca0 = pecaRepository.findById(id);
        if(peca0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peca0.get());
    }

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

}
