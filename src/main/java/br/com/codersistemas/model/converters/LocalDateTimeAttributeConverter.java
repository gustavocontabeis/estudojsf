package br.com.codersistemas.model.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
 
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	
	private Logger log = LoggerFactory.getLogger(LocalDateTimeAttributeConverter.class);
 
    //TRANSFORMA EM Timestamp NA HORA DE PERSISTIR NO BANCO DE DADOS
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    	
    	log.info(">>> {}", localDateTime);
 
    	if(localDateTime != null)
    		return Timestamp.valueOf(localDateTime);
 
    	return null;
 
    }
 
    //TRANSFORMA UM Timestamp EM LocalDateTime QUANDO RETORNAR DO BANCO PARA ENTIDADE
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
 
    	log.info("<<< {}", timestamp);
    	
    	if(timestamp != null)
    		return timestamp.toLocalDateTime();
 
    	return null;
    }
}
