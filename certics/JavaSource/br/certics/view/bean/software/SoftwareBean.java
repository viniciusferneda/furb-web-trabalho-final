package br.certics.view.bean.software;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="softwareBean")
public class SoftwareBean {

	private String nome;
	private OrganizacaoSolicitanteEntity organizacaoSolicitante;
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public void salvar() {
		SoftwareEntity softwareEntity = new SoftwareEntity();
		
		SoftwareFacade facade = FacadeProvider.get().provide(SoftwareFacade.class);
		facade.salvar(softwareEntity);
		
		MessageUtils.addInfoMessage("Software salvo com sucesso!");
		limpar();
	}
	
	public void limpar() {
		nome = null;
		organizacaoSolicitante = null;
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

	public OrganizacaoSolicitanteEntity getOrganizacaoSolicitante() {
		return organizacaoSolicitante;
	}

	public void setOrganizacaoSolicitante(OrganizacaoSolicitanteEntity organizacaoSolicitante) {
		this.organizacaoSolicitante = organizacaoSolicitante;
	}

}
