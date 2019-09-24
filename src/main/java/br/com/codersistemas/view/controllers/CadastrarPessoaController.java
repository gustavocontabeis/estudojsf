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
import br.com.codersistemas.model.entity.Pessoa;
import br.com.codersistemas.model.entity.Sexo;
import br.com.codersistemas.model.entity.Usuario;
import br.com.codersistemas.model.repository.BaseRepository;
import br.com.codersistemas.model.repository.PessoaRepository;
import br.com.codersistemas.uteis.Uteis;

@RequestScoped
@Named(value="cadastrarPessoaController")
public class CadastrarPessoaController extends CrudController<Pessoa, Long> {
	
	@Inject
	@AppLogger
	private java.util.logging.Logger LOG;

	@Inject
	private UsuarioController usuarioController;

	@Inject
	@SuppressWarnings("rawtypes")
	private PessoaRepository repository;

	private UploadedFile file;
	
	public void instanciarObjeto() {
		obj = new Pessoa();
		obj.setUsuario(new Usuario());
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	protected BaseRepository getRepository(){
		return repository;
	}
	
	public void uploadRegistros() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			if(this.file.getFileName().equals("")){
				Uteis.mensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(this.file.getInputstream());
			Element element = doc.getDocumentElement();
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node  = nodes.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elementPessoa =(Element) node;
					//PEGANDO OS VALORES DO ARQUIVO XML
					String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
					String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
					String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

					Pessoa newPessoaModel = new Pessoa();

					newPessoaModel.setUsuario(this.usuarioController.getUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setSexo(Sexo.valueOf(sexo));

					//SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
					repository.salvar(newPessoaModel);
				}
			}
			Uteis.mensagemInfo("Registros cadastrados com sucesso!");
		} catch (AppException e) {
			Uteis.mensagemInfo(e.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Pessoa getObj() {
		return obj;
	}

	public void setObj(Pessoa pessoaModel) {
		this.obj = pessoaModel;
	}

	@Override
	protected Logger getLog() {
		return LOG;
	}

}
