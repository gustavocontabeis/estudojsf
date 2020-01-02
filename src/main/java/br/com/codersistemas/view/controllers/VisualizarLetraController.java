package br.com.codersistemas.view.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
@Named(value="visualizarLetraController")
public class VisualizarLetraController extends CrudController<Letra, Long> {
	
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
	private List<String> letras;
	
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
	
	public String selecionar(Letra obj) {
		getLog().log(Level.INFO, String.format("visualizar %s", obj));
		this.obj = obj;
		
		letras = new ArrayList<>();
		
		getLog().log(Level.INFO, this.obj.getPortugues());
		
		String[] splitEN = this.obj.getIngles().split("\n");
//		for (String string : splitEN) {
//			//getLog().log(Level.INFO, string);
//			letras.add(string);
//		}
		String[] splitPT = this.obj.getPortugues().split("\n");
//		for (String string : splitPT) {
//			//getLog().log(Level.INFO, string);
//			letras.add(string);
//		}
		
		int tamanho = splitPT.length > splitEN.length ? splitPT.length : splitEN.length;
		
		for (int i = 0; i < tamanho; i++) {
			if(i < splitEN.length) {
				letras.add("<span class='letra-en'>"+splitEN[i]+"</span>");
			}
			if(i < splitPT.length) {
				letras.add("<span class='letra-pt'>"+splitPT[i]+"</span>");
			}
		}
		
		getLog().log(Level.INFO, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		for(String a : letras) {
			//getLog().log(Level.INFO, a);
			
		}
		
		return "visualizar.xhtml";//?faces-redirect=true
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

	public List<String> getLetras() {
		return letras;
	}

	public void setLetras(List<String> letras) {
		this.letras = letras;
	}

}
