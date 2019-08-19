package br.com.codersistemas.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IEntity extends Serializable {
	
	Long getId();
	
	LocalDateTime getExclusao();

}
