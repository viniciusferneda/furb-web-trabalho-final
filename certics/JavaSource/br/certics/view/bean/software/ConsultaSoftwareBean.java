package br.certics.view.bean.software;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

	private final List<SoftwareEntity> lSoftware = new ArrayList<SoftwareEntity>();

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

	public List<SoftwareEntity> getlSoftware() {
		return lSoftware;
	}

	public void filtrar() {
		SoftwareFacade facade = FacadeProvider.get().provide(SoftwareFacade.class);
		lSoftware.clear();
		lSoftware.addAll(facade.selectAll());
	}
	
}
