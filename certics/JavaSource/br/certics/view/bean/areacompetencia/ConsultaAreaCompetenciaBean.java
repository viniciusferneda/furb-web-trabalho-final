package br.certics.view.bean.areacompetencia;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AreaCompetenciaFacade;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="consultaAreaCompetenciaBean")
public class ConsultaAreaCompetenciaBean {

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<AreaCompetenciaEntity> getAllAreaCompetencia(){
		AreaCompetenciaFacade areaCompetenciaFacade = FacadeProvider.get().provide(AreaCompetenciaFacade.class);
		return areaCompetenciaFacade.selectAll();
	}

}
