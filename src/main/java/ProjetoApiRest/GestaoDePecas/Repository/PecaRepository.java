package ProjetoApiRest.GestaoDePecas.Repository;

import ProjetoApiRest.GestaoDePecas.Model.PecaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PecaRepository extends JpaRepository<PecaModel, UUID> {

}
