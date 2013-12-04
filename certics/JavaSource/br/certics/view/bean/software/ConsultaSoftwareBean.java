package br.certics.view.bean.software;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.SoftwareEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="consultaSoftwareBean")
public class ConsultaSoftwareBean {

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<SoftwareEntity> getAllSoftware() {
		SoftwareFacade facade = FacadeProvider.get().provide(SoftwareFacade.class);
		return facade.selectAll();
	}
	
}
