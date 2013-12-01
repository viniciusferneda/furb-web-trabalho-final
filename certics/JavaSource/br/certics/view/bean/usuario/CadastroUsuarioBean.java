package br.certics.view.bean.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.UsuarioFacade;
import br.certics.model.entity.UsuarioEntity;
import br.certics.model.enums.Sexo;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name = "cadastroUsuarioBean")
public class CadastroUsuarioBean {

	private String nome;
	private Sexo sexo = null;
	private String email;
	private String senhaAtual;
	private String senha;
	private String confirmacaoSenha;
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	@PostConstruct
	public void init() {
		limpar();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getMail() {
		return email;
	}

	public void setMail(String mail) {
		this.email = mail;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Sexo[] getSexoEnum() {
		return Sexo.values();
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void salvar() {
		UsuarioFacade facade = FacadeProvider.get().provide(UsuarioFacade.class);
		
		if (facade.isEmailUsado(email)) {
			MessageUtils.addWarningMessage("O e-mail informado já está cadastrado, favor informar outro e-mail.");
			return;
		}
		
		if (!senha.equals(confirmacaoSenha)) {
			MessageUtils.addWarningMessage("A senha e confirmação de senha informadas estão diferentes, favor verificar.");
			return;
		}
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		try {
			facade.salvar(usuario);
		} catch(Exception e) {
			MessageUtils.addErrorMessage("Erro ao salvar cadastro!", e.getLocalizedMessage());
			return;
		}
		limpar();
		MessageUtils.addInfoMessage("Cadastro realizado com sucesso!");
	}
	
	public void cancelar() {
		limpar();
	}

	private void limpar() {
		nome = null;
		sexo = null;
		email = null;
		senha = null;
		confirmacaoSenha = null;
	}
	
	public void alterarSenha() {
		UsuarioEntity usuario = applicationContext.getUsuarioLogado();
		
		if (!usuario.getSenha().equals(senhaAtual)) {
			MessageUtils.addErrorMessage("A senha atual informada não é válida!");
			return;
		}
		if (!senha.equals(confirmacaoSenha)) {
			MessageUtils.addWarningMessage("A senha e confirmação de senha informadas estão diferentes, favor verificar.");
			return;
		}
		UsuarioFacade facade = FacadeProvider.get().provide(UsuarioFacade.class);
		
		usuario.setSenha(senha);
		
		facade.salvar(usuario);
		MessageUtils.addInfoMessage("Senha alterada com sucesso!");
	}

}
