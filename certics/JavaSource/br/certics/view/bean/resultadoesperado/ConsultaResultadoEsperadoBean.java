package br.certics.view.bean.resultadoesperado;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.ResultadoEsperadoFacade;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="consultaResultadoEsperadoBean")
public class ConsultaResultadoEsperadoBean {

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<ResultadoEsperadoEntity> getAllResultadoEsperado() {
		ResultadoEsperadoFacade facade = FacadeProvider.get().provide(ResultadoEsperadoFacade.class);
		return facade.selectAll();
	}
	
}
