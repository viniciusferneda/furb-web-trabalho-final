package br.certics.view.bean.resultadoesperado;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.ResultadoEsperadoFacade;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="resultadoEsperadoBean")
public class ResultadoEsperadoBean {

	private String titulo;
	private String descricao;
	private AreaCompetenciaEntity areaCompetencia;

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public void salvar() {
		ResultadoEsperadoEntity resultadoEsperadoEntity = new ResultadoEsperadoEntity();
		
		ResultadoEsperadoFacade facade = FacadeProvider.get().provide(ResultadoEsperadoFacade.class);
		facade.salvar(resultadoEsperadoEntity);
		
		MessageUtils.addInfoMessage("Resultado esperado salvo com sucesso!");
		limpar();
	}
	
	public void limpar() {
		titulo = null;
		descricao = null;
		areaCompetencia = null;
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AreaCompetenciaEntity getAreaCompetencia() {
		return areaCompetencia;
	}

	public void setAreaCompetencia(AreaCompetenciaEntity areaCompetencia) {
		this.areaCompetencia = areaCompetencia;
	}
	
}
