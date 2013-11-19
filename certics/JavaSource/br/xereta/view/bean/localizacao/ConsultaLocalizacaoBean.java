package br.xereta.view.bean.localizacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.finf.control.facade.FacadeProvider;
import br.finf.filter.QueryBuilder;
import br.xereta.controller.facade.ComputadorFacade;
import br.xereta.controller.facade.LocalizacaoFacade;
import br.xereta.model.entity.ComputadorEntity;
import br.xereta.model.entity.LocalizacaoEntity;
import br.xereta.view.bean.ApplicationContextBean;

@ViewScoped
@ManagedBean(name = "consultaLocalizacaoBean")
public class ConsultaLocalizacaoBean {

	private final List<LocalizacaoEntity> localizacoes = new ArrayList<LocalizacaoEntity>();
	
	private final List<ComputadorEntity> computadores = new ArrayList<ComputadorEntity>();
	
	private List<ComputadorEntity> computadoresSelecionados = new ArrayList<ComputadorEntity>();
	
	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;

	@PostConstruct
	public void init() {
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);
		computadores.addAll(facade.getAllComputadorByUsuarioId(applicationContext.getUsuarioLogado().getId()));
		computadoresSelecionados.addAll(computadores);
		filtrar();
	}

	public List<LocalizacaoEntity> getLocalizacoes() {
		return localizacoes;
	}
	
	public List<ComputadorEntity> getComputadores() {
		return computadores;
	}
	
	public List<ComputadorEntity> getComputadoresSelecionados() {
		return computadoresSelecionados;
	}
	
	public void setComputadoresSelecionados(List<ComputadorEntity> computadoresSelecionados) {
		this.computadoresSelecionados = computadoresSelecionados;
	}
	
	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void filtrar() {
		LocalizacaoFacade facade = FacadeProvider.get().provide(LocalizacaoFacade.class);
		
		QueryBuilder qb = new QueryBuilder(LocalizacaoEntity.class, "loc");

		localizacoes.clear();
		localizacoes.addAll(facade.executeQuery(qb));
	}
}
