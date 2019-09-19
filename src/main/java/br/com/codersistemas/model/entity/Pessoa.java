package br.com.codersistemas.model.entity;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="pessoa")
@Table(uniqueConstraints={
		@UniqueConstraint(name="uk_pessoa_cpf", columnNames={"cpf"}),
		@UniqueConstraint(name="uk_pessoa_email", columnNames={"email"}),
})
public class Pessoa implements IEntity {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="pessoa_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="pessoa_seq", initialValue=1, sequenceName="pessoa_sequence")
	@Column(name = "id_pessoa")
	private Long id;
 
	@Column(length=100, nullable=false)
	private String nome;
 
	@Column(length=20, nullable=false)
	private String cpf;
 
	@Enumerated(EnumType.STRING)
	@Column(length=10, nullable=false)
	private Sexo sexo;
 
	@Column(name="data_cadastro", nullable=false)
	private LocalDateTime dataCadastro;
 
	@Column(name="data_exclusao", nullable=true)
	private LocalDateTime exclusao;
 
	@Column(length=100)
	private String email;
 
	@Column(length=200)
	private String endereco;
 
	@OneToOne(cascade= {CascadeType.ALL, CascadeType.REMOVE})
	@JoinColumn(name="id_usuario", foreignKey = @ForeignKey(value=ConstraintMode.CONSTRAINT, name="fk_pessoa_usuario"))
	private Usuario usuario;
 
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public LocalDateTime getExclusao() {
		return this.exclusao;
	}
}
