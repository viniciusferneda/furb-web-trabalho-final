package br.certics.view.bean.avaliacao;

import java.util.List;

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

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<AvaliacaoEntity> getAllAvaliacao() {
		AvaliacaoFacade facade = FacadeProvider.get().provide(AvaliacaoFacade.class);
		return facade.selectAll();
	}
	
}
