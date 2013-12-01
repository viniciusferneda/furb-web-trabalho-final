package br.certics.view.bean.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="consultaAvaliacaoBean")
public class ConsultaAvaliacaoBean {

	private final List<AvaliacaoEntity> lAvaliacao = new ArrayList<AvaliacaoEntity>();

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

	public List<AvaliacaoEntity> getlAvaliacao() {
		return lAvaliacao;
	}

	public void filtrar() {
		AvaliacaoFacade facade = FacadeProvider.get().provide(AvaliacaoFacade.class);
		lAvaliacao.clear();
		lAvaliacao.addAll(facade.selectAll());
	}
	
}
