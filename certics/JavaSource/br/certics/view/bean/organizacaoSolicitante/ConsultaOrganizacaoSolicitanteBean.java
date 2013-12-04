package br.certics.view.bean.organizacaoSolicitante;

import java.util.List;

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

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<OrganizacaoSolicitanteEntity> getAllOrganizacaoSolicitante() {
		OrganizacaoSolicitanteFacade facade = FacadeProvider.get().provide(OrganizacaoSolicitanteFacade.class);
		return facade.selectAll();
	}
	
}
