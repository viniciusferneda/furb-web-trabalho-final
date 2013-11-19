package br.xereta.view.bean.computador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.finf.control.facade.FacadeProvider;
import br.xereta.controller.facade.ComputadorFacade;
import br.xereta.model.entity.ComputadorEntity;
import br.xereta.view.bean.ApplicationContextBean;
import br.xereta.view.bean.MessageUtils;

@ViewScoped
@ManagedBean(name = "consultaComputadorBean")
public class ConsultaComputadorBean {

	private final List<ComputadorEntity> computadores = new ArrayList<ComputadorEntity>();

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

	public List<ComputadorEntity> getComputadores() {
		return computadores;
	}

	public void filtrar() {
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);

		computadores.clear();
		computadores.addAll(facade.getAllComputadorByUsuarioId(applicationContext.getUsuarioLogado().getId()));
	}
	
	public void habilitarNotificacao(Long id) {
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);
		facade.habilitarNotificacao(id);
		
		MessageUtils.addInfoMessage("Nofiticações do computador " + id + " habilitada com sucesso.");
	}
	
	public void desabilitarNotificacao(Long id) {
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);
		facade.desabilitarNotificacao(id);
		
		MessageUtils.addInfoMessage("Nofiticações do computador " + id + " desabilitada com sucesso.");
	}
}
