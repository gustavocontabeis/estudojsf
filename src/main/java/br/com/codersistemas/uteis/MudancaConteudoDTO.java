package br.com.codersistemas.uteis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MudancaConteudoDTO {

	private Long valorIdRegistro;
	private String noCampo;
	private String coAntes;
	private String coDepois;
	
}
