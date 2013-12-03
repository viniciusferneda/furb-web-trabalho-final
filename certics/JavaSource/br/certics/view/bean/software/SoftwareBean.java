package br.certics.view.bean.software;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.OrganizacaoSolicitanteFacade;
import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="softwareBean")
public class SoftwareBean {

	private SoftwareEntity software = new SoftwareEntity();
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public List<OrganizacaoSolicitanteEntity> getAllOrganizacaoSolicitante(){
		OrganizacaoSolicitanteFacade organizacaoSolicitanteFacade = FacadeProvider.get().provide(OrganizacaoSolicitanteFacade.class);
		return organizacaoSolicitanteFacade.selectAll();
	}
	
	public void salvar() {
		SoftwareFacade facade = FacadeProvider.get().provide(SoftwareFacade.class);
		facade.salvar(software);
		MessageUtils.addInfoMessage("Software salvo com sucesso!");
		limpar();
	}
	
	public void limpar() {
		software = new SoftwareEntity();
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public SoftwareEntity getSoftwareEntity() {
		return software;
	}

	public void setSoftwareEntity(SoftwareEntity software) {
		this.software = software;
	}

}
