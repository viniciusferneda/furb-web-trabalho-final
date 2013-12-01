package br.certics.view.bean.areacompetencia;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AreaCompetenciaFacade;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name = "consultaAreaCompetenciaBean")
public class ConsultaAreaCompetenciaBean {

	private final List<AreaCompetenciaEntity> lAreaCompetencia = new ArrayList<AreaCompetenciaEntity>();

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

	public List<AreaCompetenciaEntity> getComputadores() {
		return lAreaCompetencia;
	}

	public void filtrar() {
		AreaCompetenciaFacade facade = FacadeProvider.get().provide(AreaCompetenciaFacade.class);
		lAreaCompetencia.clear();
		lAreaCompetencia.addAll(facade.selectAll());
	}
	
}
