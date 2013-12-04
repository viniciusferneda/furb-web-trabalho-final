package br.certics.view.bean.resultadoesperado;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AreaCompetenciaFacade;
import br.certics.controller.facade.ResultadoEsperadoFacade;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="resultadoEsperadoBean")
public class ResultadoEsperadoBean {

	private ResultadoEsperadoEntity resultadoEsperado = new ResultadoEsperadoEntity();
	private AreaCompetenciaEntity areaCompetencia = new AreaCompetenciaEntity();
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public List<AreaCompetenciaEntity> getAllAreaCompetencia(){
		AreaCompetenciaFacade areaCompetenciaFacade = FacadeProvider.get().provide(AreaCompetenciaFacade.class);
		return areaCompetenciaFacade.selectAll();
	}
	
	public void salvar() {
		ResultadoEsperadoFacade facade = FacadeProvider.get().provide(ResultadoEsperadoFacade.class);
		resultadoEsperado.setAreaCompetencia(areaCompetencia);
		facade.salvar(resultadoEsperado);
		MessageUtils.addInfoMessage("Resultado esperado salvo com sucesso!");
		limpar();
	}
	
	public void limpar() {
		resultadoEsperado = new ResultadoEsperadoEntity();
		areaCompetencia = new AreaCompetenciaEntity();
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public ResultadoEsperadoEntity getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(ResultadoEsperadoEntity resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	public AreaCompetenciaEntity getAreaCompetencia() {
		return areaCompetencia;
	}

	public void setAreaCompetencia(AreaCompetenciaEntity areaCompetencia) {
		this.areaCompetencia = areaCompetencia;
	}

}
