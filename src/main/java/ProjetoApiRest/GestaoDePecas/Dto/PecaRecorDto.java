package ProjetoApiRest.GestaoDePecas.Dto;

import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record PecaRecorDto(@NotBlank String nome, @NotNull Integer numeroPeca, @NotBlank String descricao, @NotNull BigDecimal preco, @NotNull Integer quantidadeEstoque, @NotBlank String fornecedor) {
}