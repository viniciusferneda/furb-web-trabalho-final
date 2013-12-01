package br.certics.view.bean.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.certics.controller.facade.UsuarioFacade;
import br.certics.model.entity.UsuarioEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@SessionScoped
@ManagedBean(name = "sessaoBean")
public class SessaoBean {

	private String email = null;
	private String senha = null;
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String login() {
		UsuarioFacade facade = FacadeProvider.get().provide(UsuarioFacade.class);

		UsuarioEntity usuario = facade.usuarioByEmailESenha(email, senha);
		
		if (usuario != null) {
			applicationContext.setUsuarioLogado(usuario);
			email = null;
			senha = null;
			return "/pages/index.xhtml";
		}
		
		MessageUtils.addWarningMessage("Não conseguiu efetuar o login, favor verificar e-mail e senha.");
		return null;
	}

	public String logout() {
		applicationContext.setUsuarioLogado(null);
		email = null;
		senha = null;
		return "/pages/index.xhtml";
	}

	public boolean isLogged() {
		return applicationContext.getUsuarioLogado() != null;
	}
	
	public String getUserName() {
		if (isLogged()) {
			return applicationContext.getUsuarioLogado().getPessoaFisica().getNome(); 
		}
		return "";
	}
	
	public UsuarioEntity getUsuarioLogado() {
		return applicationContext.getUsuarioLogado();
	}

}