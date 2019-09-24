package br.com.codersistemas.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.codersistemas.model.entity.Pessoa.PessoaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="album")
public class Album implements IEntity { 
	
	@Id
	@GeneratedValue(generator="album_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="album_seq", initialValue=1, sequenceName="album_sequence")
	@Column(name = "id_album")
	private Long id;
 
	@Column(length=150, nullable=false)
	private String nome;
	
	@OneToMany()
	private List<Letra> letras;
	
	@Column(name="data_exclusao", nullable=true)
	private LocalDateTime exclusao;
	
	@Override
	public LocalDateTime getExclusao() {
		return exclusao;
	}

}
