package br.com.engenhariareversa.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.tomcat.jni.User;
import java.util.List;

import br.com.engenhariareversa.dao.AnimalDAO;
import br.com.engenhariareversa.dao.UsuarioDAO;
import br.com.engenhariareversa.domain.Animal;
import br.com.engenhariareversa.domain.Usuario;
import br.com.engenhariareversa.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AnimalBean {
	
	private Animal animalCadastrar;
	private List<Animal> lstAnimais;
	private List<Animal> lstAnimaisFind;
	private String acao = "novo";
	private Long idAnimal;
	
	public Animal getAnimalCadastrar() {
		if(animalCadastrar == null) {
			animalCadastrar = new Animal();
		}
		return animalCadastrar;
	}
	
	public void setAnimalCadastrar(Animal animalCadastrar) {
		this.animalCadastrar = animalCadastrar;
	}
	
	public List<Animal> getLstAnimais() {
		return lstAnimais;
	}
	
	public void setLstAnimais(List<Animal> lstAnimal) {
		this.lstAnimais = lstAnimal;
	}
	
	public List<Animal> getLstAnimaisFind() {
		return lstAnimaisFind;
	}
	
	public void setLstAnimaisFind(List<Animal> lstAnimalFind) {
		this.lstAnimaisFind = lstAnimalFind;
	}
	
	public String getAcao() {
		return acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
	
	public void salvar() {
		
		
		
		try {
			
			AnimalDAO dao = new AnimalDAO();
			
			Usuario user = new Usuario();
			UsuarioDAO userDao = new UsuarioDAO();
			
			user = userDao.buscarPorId(1L);
						
			animalCadastrar.setUsuario(user);
			dao.salvar(animalCadastrar);

			FacesUtil.adicionarMsgInfo("Animal Cadastrado com sucesso");
			animalCadastrar = new Animal();
			
			
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMsgInfo("Erro ao tentar Cadastrar um Animal: "+ ex.getMessage());
		}
				
	}
	
	public void listarAnimais() {
		try {
			
			AnimalDAO dao = new AnimalDAO();
			lstAnimais = dao.listar();
			
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao carregar a lista de animais" + ex.getMessage());
		}
	}
	
	public void listarAnimalId() {
		try {
			
			if(idAnimal != null ) {
				AnimalDAO dao = new AnimalDAO();
				animalCadastrar = dao.buscarPorId(Long.valueOf(idAnimal));
			}
			
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao carregar animal" + ex.getMessage());
		}
	}
	
	public void excluir() {
			
			try {
				
				AnimalDAO dao = new AnimalDAO();
				dao.excluir(animalCadastrar);
	
				FacesUtil.adicionarMsgInfo("Animal excluido com sucesso");
				
				
			} catch (RuntimeException ex) {
				ex.printStackTrace();
				FacesUtil.adicionarMsgInfo("Erro ao tentar excluir o Animal: "+ ex.getMessage());
			}
					
		}
	public void editar() {
		
		try {
			
			AnimalDAO dao = new AnimalDAO();
			dao.editar(animalCadastrar);

			FacesUtil.adicionarMsgInfo("Animal Editar com sucesso");
			
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMsgInfo("Erro ao tentar editar o Animal: "+ ex.getMessage());
		}
				
	}

	
	
}
