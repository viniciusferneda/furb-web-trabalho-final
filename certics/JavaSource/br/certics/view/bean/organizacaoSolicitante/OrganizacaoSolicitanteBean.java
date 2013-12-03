package br.certics.view.bean.organizacaoSolicitante;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.OrganizacaoSolicitanteFacade;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="organizacaoSolicitanteBean")
public class OrganizacaoSolicitanteBean {

	private String nome;
	private String cnpj;

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public void salvar() {
		OrganizacaoSolicitanteEntity organizacaoSolicitante = new OrganizacaoSolicitanteEntity();
		organizacaoSolicitante.setNome(nome);
		organizacaoSolicitante.setCnpj(cnpj);
		OrganizacaoSolicitanteFacade facade = FacadeProvider.get().provide(OrganizacaoSolicitanteFacade.class);
		facade.salvar(organizacaoSolicitante);
		
		MessageUtils.addInfoMessage("Organização solicitante salva com sucesso!");
		limpar();
	}
	
	public void limpar() {
		nome = null;
		cnpj = null;
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
