package br.com.codersistemas.view.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.codersistemas.annotations.AppLogger;
import br.com.codersistemas.exceptions.AppException;
import br.com.codersistemas.model.entity.Album;
import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Sexo;
import br.com.codersistemas.model.repository.AlbumRepository;
import br.com.codersistemas.model.repository.BaseRepository;
import br.com.codersistemas.model.repository.PessoaRepository;
import br.com.codersistemas.uteis.Uteis;

@RequestScoped
@Named(value="cadastrarAlbumController")
public class CadastrarAlbumController extends CrudController<Album, Long> {
	
	@Inject
	@AppLogger
	private java.util.logging.Logger LOG;

	@Inject
	@SuppressWarnings("rawtypes")
	private AlbumRepository repository;

	public void instanciarObjeto() {
		obj = new Album();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	protected BaseRepository getRepository(){
		return repository;
	}
	
	public Album getObj() {
		return obj;
	}

	public void setObj(Album obj) {
		this.obj = obj;
	}

	@Override
	protected Logger getLog() {
		return LOG;
	}

}
