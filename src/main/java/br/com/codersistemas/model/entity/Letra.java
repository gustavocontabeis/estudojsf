package br.com.codersistemas.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="letra")
public class Letra implements IEntity { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="letra_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="letra_seq", initialValue=1, sequenceName="letra_sequence")
	@Column(name = "id_letra")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_album")
	private Album album;
 
	@Column(length=10000, nullable=false)
	private String portuges;
	
	@Column(length=10000, nullable=false)
	private String ingles;
	
	@Column(name="data_exclusao", nullable=true)
	private LocalDateTime exclusao;
	
	@Override
	public LocalDateTime getExclusao() {
		return exclusao;
	}

}
