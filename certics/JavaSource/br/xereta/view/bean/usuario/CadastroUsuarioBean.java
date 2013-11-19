package br.xereta.view.bean.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.finf.control.facade.FacadeProvider;
import br.xereta.controller.facade.UsuarioFacade;
import br.xereta.model.entity.UsuarioEntity;
import br.xereta.model.enums.SexoEnum;
import br.xereta.view.bean.ApplicationContextBean;
import br.xereta.view.bean.MessageUtils;

@ViewScoped
@ManagedBean(name = "cadastroUsuarioBean")
public class CadastroUsuarioBean {

	private String nome;
	private SexoEnum sexo = null;
	private String mail;
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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public SexoEnum[] getSexoEnum() {
		return SexoEnum.values();
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void salvar() {
		UsuarioFacade facade = FacadeProvider.get().provide(UsuarioFacade.class);
		
		if (facade.isEmailUsado(mail)) {
			MessageUtils.addWarningMessage("O e-mail informado já está cadastrado, favor informar outro e-mail.");
			return;
		}
		
		if (!senha.equals(confirmacaoSenha)) {
			MessageUtils.addWarningMessage("A senha e confirmação de senha informadas estão diferentes, favor verificar.");
			return;
		}
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome(nome);
		usuario.setMail(mail);
		usuario.setSexo(sexo);
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
		mail = null;
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
