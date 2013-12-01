package br.certics.view.bean.organizacaoSolicitante;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.OrganizacaoSolicitanteFacade;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="consultaOrganizacaoSolicitanteBean")
public class ConsultaOrganizacaoSolicitanteBean {

	private final List<OrganizacaoSolicitanteEntity> lOrganizacaoSolicitante = new ArrayList<OrganizacaoSolicitanteEntity>();

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	@PostConstruct
	public void init() {
		filtrar();
	}

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<OrganizacaoSolicitanteEntity> getlOrganizacaoSolicitante() {
		return lOrganizacaoSolicitante;
	}

	public void filtrar() {
		OrganizacaoSolicitanteFacade facade = FacadeProvider.get().provide(OrganizacaoSolicitanteFacade.class);
		lOrganizacaoSolicitante.clear();
		lOrganizacaoSolicitante.addAll(facade.selectAll());
	}
	
}
