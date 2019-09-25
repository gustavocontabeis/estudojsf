package br.com.codersistemas.view.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.codersistemas.annotations.AppLogger;
import br.com.codersistemas.model.entity.Album;
import br.com.codersistemas.model.entity.Letra;
import br.com.codersistemas.model.repository.AlbumRepository;
import br.com.codersistemas.model.repository.BaseRepository;
import br.com.codersistemas.model.repository.LetraRepository;

@RequestScoped
@Named(value="cadastrarLetraController")
public class CadastrarLetraController extends CrudController<Letra, Long> {
	
	@Inject
	@AppLogger
	private java.util.logging.Logger LOG;

	@Inject
	@SuppressWarnings("rawtypes")
	private LetraRepository repository;
	
	@Inject
	@SuppressWarnings("rawtypes")
	private AlbumRepository albumRepository;
	
	private List<Album> albuns;
	
	public void instanciarObjeto() {
		obj = new Letra();
		obj.setAlbum(new Album());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void initApos() {
		albuns = albumRepository.listar();
		for (Album album : albuns) 
			album.setLetras(null);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	protected BaseRepository getRepository(){
		return repository;
	}
	
	public Letra getObj() {
		return obj;
	}

	public void setObj(Letra obj) {
		this.obj = obj;
	}

	@Override
	protected Logger getLog() {
		return LOG;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

}
