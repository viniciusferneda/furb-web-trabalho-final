package br.certics.view.bean.resultadoesperado;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

	private final List<ResultadoEsperadoEntity> lResultadoEsperado = new ArrayList<ResultadoEsperadoEntity>();

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

	public List<ResultadoEsperadoEntity> getlResultadoEsperado() {
		return lResultadoEsperado;
	}

	public void filtrar() {
		ResultadoEsperadoFacade facade = FacadeProvider.get().provide(ResultadoEsperadoFacade.class);
		lResultadoEsperado.clear();
		lResultadoEsperado.addAll(facade.selectAll());
	}
	
}
